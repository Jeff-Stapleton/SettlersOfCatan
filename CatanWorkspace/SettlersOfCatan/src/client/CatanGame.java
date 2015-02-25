package client;

import java.io.IOException;
import java.util.Observable;

import client.comm.ServerProxy;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;
import shared.CatanModel;
import shared.definitions.CatanColor;

public class CatanGame extends Observable {
	
	/** The log. */
	private ServerProxy server;
	private CatanModel model;
	
	private PlayerInfo playerInfo = null;
	private GameInfo gameInfo = null;
	
	/**
	 * Instantiates a new Catan Game.
	 */
	public CatanGame(ServerProxy gameServer, CatanModel initialModel) 
	{
		server = gameServer;
		model = initialModel;
	}
	
	public CatanModel getModel() 
	{
		return model;
	}

	public void setModel(CatanModel model) 
	{
		this.model = model;
		setChanged();
		notifyObservers();
	}
	
	public PlayerInfo getPlayerInfo()
	{
		// TODO: Broken design here
		// I want to remove the current-player-logged-in info from the server
		// proxy and move it into here, but that will mean all logins and joins must
		// happen through catan game instead of the proxy
		if (playerInfo == null)
		{
			return new PlayerInfo(server.getPlayerId(), -1, server.getPlayerName(), CatanColor.WHITE);
		}
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
	
	public void updateModel() throws IOException {
		setModel(server.gameModel());
	}
}
