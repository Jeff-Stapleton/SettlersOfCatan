package server.facade;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import client.view.data.GameInfo;
import server.ServerGame;
import server.ServerLobby;
import server.models.ServerUser;
import shared.comm.ServerException;
import shared.comm.serialization.CreateGameRequest;
import shared.comm.serialization.JoinGameRequest;
import shared.comm.serialization.LoadGameRequest;
import shared.comm.serialization.SaveGameRequest;
import shared.definitions.CatanColor;

/**
 * The Class GamesFacade implements the list, create, join, save, and load commands
 */
public class GamesFacade
{
	private static final Logger log = Logger.getLogger(GamesFacade.class);
	
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
	public GameInfo create(CreateGameRequest request) throws ServerException
	{
		return serverLobby.createGame(request.getName(), request.isRandomTiles(), request.isRandomNumbers(), request.isRandomPorts());
	}
	
	/**
	 * Joins a game
	 * @param user 
	 *
	 * @param request the request
	 * @return 
	 */
	public GameInfo join(ServerUser user, JoinGameRequest request) throws ServerException
	{
		ServerGame game = serverLobby.getGame(request.getId());
		if (game.getInfo().getPlayerWithId(user.getID()) != null)
		{
			log.trace("Already a part of that game. Return game.");
			return game.getInfo();
		}
		if (game.getInfo().getPlayers().size() >= 4)
		{
			throw new ServerException("Game is full");
		}
		game.addPlayer(user, CatanColor.fromString(request.getColor()));
		
		return game.getInfo();
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
