package server.facade;

import java.util.ArrayList;

import client.view.data.GameInfo;
import server.ServerGame;
import server.ServerLobby;
import shared.comm.serialization.CreateGameRequest;
import shared.comm.serialization.JoinGameRequest;
import shared.comm.serialization.LoadGameRequest;
import shared.comm.serialization.SaveGameRequest;

/**
 * The Class GamesFacade implements the list, create, join, save, and load commands
 */
public class GamesFacade {
	
	/** The game lobby. */
	private ServerLobby serverLobby;
	
	public GamesFacade(ServerLobby serverLobby) {
		this.serverLobby = serverLobby;
	}

	/**
	 * Returns a list of the games
	 * @return 
	 *
	 */
	public GameInfo[] list()
	{
		ArrayList<GameInfo> gameList = new ArrayList<GameInfo>();
		
		for (ServerGame game : serverLobby.getGames())
		{
			gameList.add(game.getInfo());
		}
		
		return gameList.toArray(new GameInfo[0]);
	}
	
	/**
	 * Creates a game
	 *
	 * @param request the request
	 */
	public void create(CreateGameRequest request){
		
	}
	
	/**
	 * Joins a game
	 *
	 * @param request the request
	 */
	public void join(JoinGameRequest request){
		
	}
	
	/**
	 * Saves the game (for testing purposes)
	 */
	public void save(SaveGameRequest request){
		
	}
	
	/**
	 * Loads the game (for testing purposes)
	 */
	public void load(LoadGameRequest request){
		
	}
}
