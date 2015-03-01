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
public class ServerPoller extends AbstractPoller
{
	CatanGame _catanGame;
	
	boolean _gameRunning = true;
	
    public ServerPoller(CatanGame game)
    {
    	super(1000);
        _catanGame = game;
    }

    @Override
    public void poll() throws IOException
    {
    	// THIS IS THE TEST THAT WON'T LET THE POLLER RUN IF WE'RE WORKING. 
    	if (!_catanGame.getModel().getMap().getIsBuilding())
    		_catanGame.updateModel();
    }
}
