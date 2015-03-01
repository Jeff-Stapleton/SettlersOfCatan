package client;

import java.io.IOException;
import java.util.Observable;

import shared.CatanModel;
import shared.Player;
import shared.definitions.CatanColor;
import client.comm.ServerProxy;
import client.comm.LobbyPoller;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;

public class CatanLobby extends Observable {
	ServerProxy serverProxy;
	CatanGame catanGame;
	private LobbyPoller waitPoller = null;
	
	boolean loggedIn = false;
	boolean joinedGame = false;
	
	public CatanLobby(ServerProxy server) {
		serverProxy = server;
		catanGame = new CatanGame(server);
	}
	
	public void userLogin(String user, String password) throws IOException
	{
		assert user != null;
		assert password != null;
		catanGame.setPlayerInfo(serverProxy.userLogin(user, password));
		loggedIn = true;
	}
	
	public void userRegister(String user, String password) throws IOException
	{
		assert user != null;
		assert password != null;
		catanGame.setPlayerInfo(serverProxy.userRegister(user, password));
		loggedIn = true;
	}
	
	public void gamesJoin(CatanColor color, int id) throws IOException
	{
		assert color != null;
		// Join the game
		serverProxy.gamesJoin(color, id);
		_updateInfo(id);
		joinedGame = true;
	}

	public ServerProxy getProxy()
	{
		return serverProxy;
	}
	
	public PlayerInfo getPlayerInfo()
	{
		return catanGame.getPlayerInfo();
	}
	
	public void updateInfo() throws IOException
	{
		if (_updateInfo(catanGame.getGameInfo().getId()))
		{
			setChanged();
			notifyObservers();
		}
	}
	
	private boolean _updateInfo(int gameId) throws IOException
	{
		boolean updated = false;
		
		// Get the game's info
		GameInfo[] games = serverProxy.gamesList();
		for (GameInfo game : games)
		{
			if (game.getId() == gameId)
			{
				if (!game.equals(catanGame.getGameInfo()))
				{
					catanGame.setGameInfo(game);
					updated = true;
				}
			}
		}
		
		// Fill in the user indexes
		CatanModel model = serverProxy.gameModel();
		for (Player mPlayer : model.getPlayers())
		{
			if (mPlayer != null)
			{
				for (PlayerInfo player : catanGame.getGameInfo().getPlayers())
				{
					if (player != null)
					{
						if (mPlayer.getPlayerID() == player.getId() &&
							mPlayer.getPlayerIndex() != player.getPlayerIndex())
						{
							player.setPlayerIndex(mPlayer.getPlayerIndex());
							updated = true;
						}
					}
				}
			}
		}
		
		// Get the player's info from the game
		for (PlayerInfo player : catanGame.getGameInfo().getPlayers())
		{
			if (player.getId() == catanGame.getPlayerInfo().getId())
			{
				if (!player.equals(catanGame.getPlayerInfo()))
				{
					catanGame.setPlayerInfo(player);
					updated = true;
				}
			}
		}
		
		return updated;
	}
	
	public CatanGame getGame()
	{
		return catanGame;
	}
	
	public boolean isLoggedIn()
	{
		return loggedIn;
	}
	
	public boolean hasJoinedGame()
	{
		return joinedGame;
	}

	
	public void startLobbyPoller()
	{
		stopLobbyPoller();
		
		waitPoller = new LobbyPoller(this);
		waitPoller.start();
	}
	
	public void stopLobbyPoller()
	{
		if (waitPoller != null)
		{
			waitPoller.close();
			waitPoller = null;
		}
	}
	
}
