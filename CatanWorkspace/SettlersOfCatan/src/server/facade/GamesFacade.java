package server.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import client.view.data.GameInfo;
import client.view.data.PlayerInfo;
import server.ServerGame;
import server.ServerLobby;
import server.models.ServerUser;
import shared.CatanModel;
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
		CatanColor desiredColor = CatanColor.fromString(request.getColor());
		if (desiredColor == null)
			throw new ServerException("COLOR DOESN'T EXIST. PUCE DOES. PICK PUCE!");
		
		ServerGame game = serverLobby.getGame(request.getId());
		if (game.getInfo().getPlayerWithId(user.getID()) != null)
		{
			log.trace("Already a part of that game");
			log.trace("Check color uniqueness");
			for (PlayerInfo player : game.getInfo().getPlayers())
			{
				if (desiredColor == null)
					throw new ServerException("COLOR DOESN'T EXIST. PUCE DOES. PICK PUCE!");
				if (player != null && player.getId() != user.getID() && desiredColor.equals(player.getColor()))
				{
					throw new ServerException("Failed to join game - The desired color is already taken");
				}
			}
			game.getInfo().getPlayerWithId(user.getID()).setColor(desiredColor);
			game.getModel().getPlayers()[game.getInfo().getPlayerWithId(user.getID()).getPlayerIndex()].setColor(desiredColor);
			return game.getInfo();
		}
		if (game.getInfo().getPlayers().size() >= 4)
		{
			throw new ServerException("Game is full");
		}
		
		log.trace("Check color uniqueness");
		for (PlayerInfo player : game.getInfo().getPlayers())
		{
			if (desiredColor == null)
				throw new ServerException("COLOR DOESN'T EXIST. PUCE DOES. PICK PUCE!");
			if (player != null && player.getId() != user.getID() && desiredColor.equals(player.getColor()))
			{
				throw new ServerException("Failed to join game - The desired color is already taken");
			}
		}
		game.addPlayer(user, desiredColor);
		
		return game.getInfo();
	}
	
	/**
	 * Saves the game (for testing purposes)
	 * @return 
	 */
	public GameInfo save(SaveGameRequest request) throws ServerException, FileNotFoundException{
		File file = new File("saves/" + request.getName());
		ServerGame game = serverLobby.getGame(request.getId());
		if (file.isDirectory() || game == null)
			throw new ServerException("Could not save game");
		
		if (file.exists())
			file.delete();
		
		try 
		{
			file.createNewFile();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			throw new ServerException("Could not create file");
		}
		
		try (PrintWriter out = new PrintWriter(file)) {
			out.write(new Gson().toJson(game, ServerGame.class));
		}	
		return game.getInfo();
	}
	
	/**
	 * Loads the game (for testing purposes)
	 */
	public GameInfo load(LoadGameRequest request) throws FileNotFoundException, IOException{
		File file = new File("saves/" + request.getName());
		
		if (file.isDirectory())
			throw new ServerException("Could not load game");
		
		ServerGame game;
		try (FileReader json = new FileReader(file)) 
		{
			game = new Gson().fromJson(json, ServerGame.class);
			
			if (game == null)
				throw new ServerException("Could not load game");
			else{	
				serverLobby.addGame(game);
			}
		}
		return game.getInfo(); // REMOVE THIS
	}
}
