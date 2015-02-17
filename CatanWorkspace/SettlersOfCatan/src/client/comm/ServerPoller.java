package client.comm;

import java.io.IOException;

import client.CatanGame;

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
	
    ServerPoller(IServerProxy proxy, CatanGame game) {
        _server = proxy;
        _catanGame = game;
    }

    public void run() {
    	while(_gameRunning == true)
    	{
	    	try
	    	{
		    	sleep(1000);
		    	
	        	CatanModel model = _server.gameModel(-1);
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
