package client.comm;

import java.io.IOException;

import client.CatanLobby;

/**
 * Server Poller polls the game model from the server
 * every few seconds and uses that to update the model
 * held locally.
 * @author Cory Beutler
 *
 */
public class LobbyPoller extends AbstractPoller
{
	CatanLobby _catanLobby;
	
	boolean _gameRunning = true;
	
    public LobbyPoller(CatanLobby catanLobby)
    {
    	super(1000);
    	_catanLobby = catanLobby;
    		
    }

    @Override
    public void poll() throws IOException
    {
    	if (_catanLobby.isLoggedIn() && _catanLobby.hasJoinedGame())
    	{
    		_catanLobby.updateInfo();
    	}
    }
	        	
}
