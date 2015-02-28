package client.comm;

import java.io.IOException;

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
	CatanGame _catanGame;
	
	boolean _gameRunning = true;
	
    public ServerPoller(CatanGame game)
    {
        _catanGame = game;
    }

    @Override
    public void run()
    {
    	while(_gameRunning == true)
    	{
	    	try
	    	{
		    	sleep(5000);
		    	
		    	_catanGame.updateModel();
	        	
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
    
    @Override
    public void close()
    {
    	_gameRunning = false;
    }
}
