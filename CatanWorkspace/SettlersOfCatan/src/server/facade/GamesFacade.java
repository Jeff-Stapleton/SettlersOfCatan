package server.facade;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import client.view.data.GameInfo;
import client.view.data.PlayerInfo;
import server.ServerGame;
import server.ServerLobby;
import server.comm.response.JsonResponse.GameInfoArraySerializer;
import server.comm.response.JsonResponse.GameInfoSerializer;
import server.comm.response.JsonResponse.HexSerializer;
import server.comm.response.JsonResponse.PortSerializer;
import server.models.ServerUser;
import shared.CatanModel;
import shared.Hex;
import shared.Port;
import shared.comm.ServerException;
import shared.comm.serialization.CreateGameRequest;
import shared.comm.serialization.JoinGameRequest;
import shared.comm.serialization.LoadGameRequest;
import shared.comm.serialization.SaveGameRequest;
import shared.definitions.CatanColor;
import shared.definitions.HexType;

/**
 * The Class GamesFacade implements the list, create, join, save, and load commands
 */
public class GamesFacade
{
	private static final Logger log = Logger.getLogger(GamesFacade.class);
	
	/** The game lobby. */
	private ServerLobby serverLobby;
	
	
//	public static final Type GAME_INFO_TYPE = new TypeToken<GameInfo>(){}.getType();
//    public static final Type GAME_INFO_ARRAY_TYPE = new TypeToken<GameInfo[]>(){}.getType();
	
	private Gson gson = new Gson();
//									.registerTypeAdapter(GameInfo.class, new GameInfoSerializer())
//									.registerTypeAdapter(GameInfo[].class, new GameInfoArraySerializer())
//									.registerTypeAdapter(Hex.class, new HexSerializer())
//									.registerTypeAdapter(Port.class, new PortSerializer())
//									.setPrettyPrinting().create();
		
		
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
		log.trace("Joining game " + request.getId() + " as user " + user.getID());
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
		File dir = new File("./saves");
		dir.mkdir();
		File file = new File("./saves/" + request.getName() + ".txt");
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
			out.write(gson.toJson(game, ServerGame.class));
		}	
		return game.getInfo();
	}
	
	/**
	 * Loads the game (for testing purposes)
	 */
	public GameInfo load(LoadGameRequest request) throws FileNotFoundException, IOException{
		File file = new File("./saves/" + request.getName() + ".txt");
		
		if (file.isDirectory())
			throw new ServerException("Could not load game");
		
		ServerGame game;
		try (FileReader json = new FileReader(file)) 
		{
			game = gson.fromJson(json, ServerGame.class);
			
			if (game == null)
				throw new ServerException("Could not load game");
			else{	
				serverLobby.addGame(game);
			}
		}
		return game.getInfo();
	}
	
//
//	public class PortSerializer implements JsonSerializer<Port>
//	{
//
//		@Override
//		public JsonElement serialize(Port src, Type typeOfSrc, JsonSerializationContext context)
//		{
//			JsonObject object = new JsonObject();
//			
//			object.addProperty("ratio", src.getRatio());
//			if (src.getRatio() == 2)
//			{
//				object.add("resource", context.serialize(src.getType()));
//			}
//			object.add("direction", context.serialize(src.getDirection()));
//			object.add("location", context.serialize(src.getLocation()));
//			
//			return object;
//		}
//		
//	}
//	
//	public class HexSerializer implements JsonSerializer<Hex>
//	{
//
//		@Override
//		public JsonElement serialize(Hex src, Type typeOfSrc, JsonSerializationContext context)
//		{
//			JsonObject object = new JsonObject();
//			
//			if (src.getResource() != HexType.DESERT)
//			{
//				object.addProperty("resource", src.getResource().toString());
//			}
//			object.add("location", context.serialize(src.getLocation()));
//			if (src.getResource() != HexType.DESERT)
//			{
//				object.addProperty("number", src.getNumber());
//			}
//			
//			return object;
//		}
//		
//	}
//	
//	public class GameInfoSerializer implements JsonSerializer<GameInfo>
//	{
//
//		@Override
//		public JsonElement serialize(GameInfo src, Type typeOfSrc, JsonSerializationContext context)
//		{
//	        // object (for which this serializer is registered)
//	        JsonObject object = new JsonObject();
//
//	        object.addProperty("title", src.getTitle());
//	        object.addProperty("id", src.getId());
//	        
//	        JsonArray playerArray = new JsonArray();
//	        for (PlayerInfo player : src.getPlayers().toArray(new PlayerInfo[4]))
//	        {
//	        	if (player == null)
//	        	{
//	        		playerArray.add(new JsonObject());
//	        	}
//	        	else
//	        	{
//	        		playerArray.add(context.serialize(player));
//	        	}
//	        }
//	        
//	        object.add("players", playerArray);
//	        return object;
//	        
//	        //  Returned in the form:
//			//  {
//			//      "title": "Supergame",
//			//      "id": 3,
//			//      "players": [
//			//          {},
//			//          {},
//			//          {},
//			//          {}
//			//      ]
//			//  }
//		}
//		
//	}
//
//	public class GameInfoArraySerializer implements JsonSerializer<GameInfo[]>
//	{
//
//		@Override
//		public JsonElement serialize(GameInfo[] src, Type typeOfSrc, JsonSerializationContext context)
//		{
//	        // object (for which this serializer is registered)
//	        JsonArray object = new JsonArray();
//	        
//	        for (GameInfo game : src)
//	        {
//	        	object.add(context.serialize(game, GAME_INFO_TYPE));
//	        }
//	        
//	        return object;
//		}
//		
//	}
}
