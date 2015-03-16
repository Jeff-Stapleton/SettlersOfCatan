package client.comm;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import shared.CatanModel;
import client.CatanGame;

/**
 * Server Poller polls the game model from the server
 * every few seconds and uses that to update the model
 * held locally.
 * @author Cory Beutler
 *
 */
public class ServerPoller extends Thread implements IServerPoller
{
	private static final Logger log = Logger.getLogger(ServerPoller.class.getName());

	private boolean _running = true;
	private int _delay =  1000;
	
	private CatanGame _catanGame;
	
	private CatanModel latestModel = null;
	private final Semaphore modelLock = new Semaphore(1);
	private final Semaphore modelSet = new Semaphore(0);
	protected final Lock lock = new ReentrantLock();
	
    public ServerPoller(CatanGame game)
    {
        _catanGame = game;
    }
    
    public void setLatestModel(CatanModel model)
    {

    	try
    	{
    		modelLock.acquire();
        	latestModel = model;
    		modelLock.release();
    		modelSet.release();
    		
    		synchronized (lock) {
	    		lock.notify();//this.notify();
    		}
    	}
    	catch (InterruptedException e)
    	{
    		System.err.println("Interrupted thread. Shutting down");
    		System.exit(1);
    	}
    }

    @Override
    public void run()
    {
    	while(_running == true)
    	{
	    	try
	    	{
	    		lock.lock();
	    		
	    		synchronized (lock) {
	    			boolean modelWaiting = modelSet.tryAcquire();
	    			
	    			if (!modelWaiting) lock.wait(_delay);
	    			
	    			
	    			// ===== Polling the server ======
    	    		modelLock.acquire();
    	    		CatanModel temp = latestModel;
    	    		latestModel = null;
    	    		modelLock.release();
    	        	
    	        	if (temp == null)
    	        	{
    	        		log.trace("Fetching a new model");
    	        		_catanGame.updateFrom(_catanGame.getProxy().gameModel());
    	        	}
    	        	else
    	        	{
    	        		log.trace("Updating with existing model");
    	        		_catanGame.updateFrom(temp);
    	        	}
    	        	
    	        	log.trace("Model updated.");
    	        	// ==============================
	    			
	    			
	    		}
		    	lock.unlock();
	    	}
	    	catch(IOException e)
	    	{
	    		System.err.println(e.getMessage());
	    		e.printStackTrace();
	    		break;
	    	}
	    	catch(InterruptedException e)
	    	{
	    		System.err.println(e.getMessage());
	    		e.printStackTrace();
	    		break;
	    	}
	    	
    	}
    }
    
    @Override
    public void close()
    {
    	_running = false;
    }
}
