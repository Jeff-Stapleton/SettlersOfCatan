package client;

import java.io.IOException;
import java.util.Observable;

import org.apache.log4j.Logger;

import shared.CatanModel;
import shared.Player;
import shared.comm.cookie.PlayerCookie;
import shared.definitions.CatanColor;
import client.comm.ServerProxy;
import client.comm.LobbyPoller;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;

public class CatanLobby extends Observable
{
	private static final Logger log = Logger.getLogger(CatanLobby.class.getName());
	
	private ServerProxy serverProxy;
	private CatanGame catanGame;
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
		catanGame.setPlayerCookie(serverProxy.userLogin(user, password));
//		catanGame.setPlayerId(serverProxy.userLogin(user, password).getPlayerIndex());
		loggedIn = true;
	}
	
	public void userRegister(String user, String password) throws IOException
	{
		assert user != null;
		assert password != null;
		catanGame.setPlayerCookie(serverProxy.userRegister(user, password));
//		catanGame.setPlayerId(serverProxy.userRegister(user, password).getPlayerIndex());
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
		log.trace("Updating game and player info");
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
		
//		// Fill in the user indexes
//		CatanModel model = serverProxy.gameModel();
//		for (Player mPlayer : model.getPlayers())
//		{
//			if (mPlayer != null)
//			{
//				for (PlayerInfo player : catanGame.getGameInfo().getPlayers())
//				{
//					if (player != null)
//					{
//						if (mPlayer.getPlayerID() == player.getId() &&
//							mPlayer.getPlayerIndex() != player.getPlayerIndex())
//						{
//							player.setId(mPlayer.getPlayerID());
//							player.setPlayerIndex(mPlayer.getPlayerIndex());
//							player.setColor(mPlayer.getColor());
//							player.setName(mPlayer.getName());
//
//							if (player.getId() == catanGame.getPlayerInfo().getId())
//							{
//								catanGame.setPlayerInfo(player);
//								updated = true;
//							}
//							
//							updated = true;
//						}
//					}
//				}
//			}
//		}
		
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

	public PlayerCookie getPlayerCookie()
	{
		return catanGame.getPlayerCookie();
	}
	
}
