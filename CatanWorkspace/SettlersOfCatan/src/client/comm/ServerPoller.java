package client.comm;

import java.io.IOException;

import org.apache.log4j.Logger;

import client.CatanGame;

/**
 * Server Poller polls the game model from the server
 * every few seconds and uses that to update the model
 * held locally.
 * @author Cory Beutler
 *
 */
public class ServerPoller extends AbstractPoller
{
	private static final Logger log = Logger.getLogger(ServerPoller.class.getName());
	
	private CatanGame _catanGame;
	
    public ServerPoller(CatanGame game)
    {
    	super(1000);
        _catanGame = game;
    }

    @Override
    public void poll() throws IOException
    {
    	log.trace("Polling server");
    	_catanGame.updateModel();
    }
}
