package client;

import java.io.IOException;
import java.util.Observable;

import client.comm.ServerProxy;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;
import shared.CatanModel;
import shared.Player;
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
	
	public void userLogin(String user, String password) throws IOException
	{
		assert user != null;
		assert password != null;
		playerInfo = server.userLogin(user, password);
	}
	
	public void userRegister(String user, String password) throws IOException
	{
		assert user != null;
		assert password != null;
		playerInfo = server.userRegister(user, password);
	}
	
	public void gamesJoin(CatanColor color, int id) throws IOException
	{
		assert color != null;
		// Join the game
		server.gamesJoin(color, id);
		
		// Get the game's info
		GameInfo[] games = server.gamesList();
		for (GameInfo game : games)
		{
			if (game.getId() == id)
			{
				gameInfo = game;
			}
		}
		
		// Update the user indexes
		CatanModel model = server.gameModel();
		for (Player mPlayer : model.getPlayers())
		{
			for (PlayerInfo player : gameInfo.getPlayers())
			{
				if (mPlayer.getPlayerID() == player.getId())
				{
					player.setPlayerIndex(mPlayer.getPlayerIndex());
				}
			}
		}
		
		// Get the player's info from the game
		for (PlayerInfo player : gameInfo.getPlayers())
		{
			if (player.getId() == playerInfo.getId())
			{
				playerInfo = player;
			}
		}
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
