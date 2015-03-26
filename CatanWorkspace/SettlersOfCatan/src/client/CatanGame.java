package client;

import java.util.Observable;

import org.apache.log4j.Logger;

import client.comm.ServerPoller;
import client.comm.ServerProxy;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;
import shared.CatanModel;
import shared.Player;
import shared.comm.cookie.PlayerCookie;

public class CatanGame extends Observable
{
	private static final Logger log = Logger.getLogger(CatanGame.class.getName());
	
	private ServerProxy server;
	private CatanModel catanModel = new CatanModel();
	private ServerPoller serverPoller = null;
	
	private PlayerCookie playerCookie = null;
	private GameInfo gameInfo = null;
	
	/**
	 * Instantiates a new Catan Game.
	 */
	public CatanGame(ServerProxy gameServer) 
	{
		server = gameServer;
		assert catanModel != null;
	}
	
	public CatanModel getModel() 
	{
		return catanModel;
	}
	
	public PlayerInfo getPlayerInfo()
	{
		return getGameInfo().getPlayerWithId(playerCookie.getPlayerId());
	}
	
	public Player getCurrentPlayer()
	{
		int index = getPlayerInfo().getPlayerIndex();
		return getModel().getPlayers()[index];
	}
	
	public GameInfo getGameInfo()
	{
		return gameInfo;
	}

	public void setGameInfo(GameInfo game) {
		gameInfo = game;
	}

	public ServerProxy getProxy() {
		return server;
	}
	
	public void updateModel(CatanModel model)
	{
		serverPoller.setLatestModel(model);
//		log.trace("Updating model");
//		assert model != null;
//		if (catanModel.updateFrom(model))
//		{
//			setChanged();
//			notifyObservers();
//		}
//		log.trace("Model updated");
	}
	
	public void updateFrom(CatanModel model)
	{
		synchronized (this) {
			log.trace("Updating model");
			assert model != null;
			if (catanModel.updateFrom(model))
			{
				setChanged();
				notifyObservers();
			}
			log.trace("Model updated");
		}
	}
	
	public void startServerPoller()
	{
		stopServerPoller();
		
		serverPoller = new ServerPoller(this);
		serverPoller.start();
	}
	
	public void stopServerPoller()
	{
		if (serverPoller != null)
		{
			serverPoller.close();
			serverPoller = null;
		}
	}

	public void setPlayerCookie(PlayerCookie user)
	{
		playerCookie = user;		
	}

	public PlayerCookie getPlayerCookie()
	{
		return playerCookie;
	}
}
