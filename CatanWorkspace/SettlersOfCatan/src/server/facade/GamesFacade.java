package server.facade;

import shared.comm.serialization.CreateGameRequest;
import shared.comm.serialization.JoinGameRequest;
import shared.comm.serialization.LoadGameRequest;
import shared.comm.serialization.SaveGameRequest;
import client.CatanGame;
import client.CatanLobby;

// TODO: Auto-generated Javadoc
/**
 * The Class GamesFacade implements the list, create, join, save, and load commands
 */
public class GamesFacade {
	
	/** The game lobby. */
	private CatanLobby gameLobby;
	

	/** The catan game. */
	private CatanGame catanGame;
	
	/**
	 * Returns a list of the games
	 *
	 */
	public void list(){
		
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
