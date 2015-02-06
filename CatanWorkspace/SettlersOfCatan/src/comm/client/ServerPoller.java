package comm.client;

import java.io.IOException;

import shared.CatanGame;
import shared.CatanModel;

/**
 * Server Poller polls the game model from the server
 * every few seconds and uses that to update the model
 * held locally.
 * @author Cory Beutler
 *
 */
public class ServerPoller extends Thread {
	IServerProxy _server;
	CatanGame _catanGame;
	
	boolean _gameRunning = true;
	
    ServerPoller() {
        
    }

    public void run() {
    	while(_gameRunning == true)
    	{
	    	try
	    	{
		    	sleep(1000);
	        	CatanModel model = _server.gameModel(-1/*_catanGame.getModel().getVesion()*/);
	        	if (null != model)
	        	{
	        		_catanGame.setModel(model);
	        	}
	        	
	    	}
	    	catch(IOException e)
	    	{
	    		System.err.println(e.getMessage());
	    		e.printStackTrace();
	    	}
	    	catch(InterruptedException e)
	    	{
	    		System.err.println(e.getMessage());
	    		e.printStackTrace();
	    		
	    		break;
	    	}
	    	
    	}
    }
    
    public void close()
    {
    	_gameRunning = false;
    }
}
