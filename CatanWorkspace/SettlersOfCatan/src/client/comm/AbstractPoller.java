package client.comm;

import java.io.IOException;

/**
 * Server Poller polls the game model from the server
 * every few seconds and uses that to update the model
 * held locally.
 * @author Cory Beutler
 *
 */
public abstract class AbstractPoller extends Thread implements IServerPoller
{
	boolean _running = true;
	int _delay;
	
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
		    	sleep(_delay);
		    	poll();
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
