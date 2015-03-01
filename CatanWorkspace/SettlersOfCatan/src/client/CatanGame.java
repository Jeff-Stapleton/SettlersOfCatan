package client;

import java.io.IOException;
import java.util.Observable;

import org.apache.log4j.Logger;

import client.comm.ServerPoller;
import client.comm.ServerProxy;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;
import shared.CatanModel;
import shared.comm.ServerException;

public class CatanGame extends Observable
{
	private static final Logger log = Logger.getLogger(CatanGame.class.getName());
	
	private ServerProxy server;
	private CatanModel catanModel = new CatanModel();
	private ServerPoller serverPoller = null;
	
	private PlayerInfo playerInfo = null;
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

	/**
	 * Set the model to the model passed in
	 * @deprecated please use updateModel(model) instead
	 * @param model the model to set the model to
	 */
	@Deprecated
	public void setModel(CatanModel model) 
	{
		if (catanModel.updateFrom(model))
		{
			setChanged();
			notifyObservers();
		}
	}
	
	public PlayerInfo getPlayerInfo()
	{
		return playerInfo;
	}
	
	public void setPlayerInfo(PlayerInfo info)
	{
		playerInfo = info;
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
	
	public void updateModel() throws IOException
	{
		log.trace("Fetching a new model");
		CatanModel model = server.gameModel();
		
		if (model != null)
		{
			updateModel(model);
		}
		else
		{
			throw new ServerException("Recieved null model from the server");
		}
	}
	
	public void updateModel(CatanModel model)
	{
		log.trace("Updating model");
		assert model != null;
		if (catanModel.updateFrom(model))
		{
			setChanged();
			notifyObservers();
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
}
