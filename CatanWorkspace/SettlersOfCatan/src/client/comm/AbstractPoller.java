package client.comm;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Server Poller polls the game model from the server
 * every few seconds and uses that to update the model
 * held locally.
 * @author Cory Beutler
 *
 */
public abstract class AbstractPoller extends Thread implements IServerPoller
{
	private boolean _running = true;
	private int _delay;
	
	protected final Lock lock = new ReentrantLock();
	
    public AbstractPoller(int delay)
    {
        _delay = delay;
    }
    
    protected abstract void poll() throws IOException;

    @Override
    public void run()
    {
    	while(_running == true)
    	{
	    	try
	    	{
	    		lock.lock();
	    		
	    		synchronized (lock) {
	    			lock.wait(_delay);
		    		poll();
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
