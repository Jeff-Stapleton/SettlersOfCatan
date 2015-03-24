package server.handlers;

import java.lang.reflect.Type;
import java.util.Map;

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
import server.Server;
import server.comm.response.JsonResponse;
import shared.comm.ServerException;
import shared.comm.cookie.ICookie;
import shared.comm.serialization.CreateGameRequest;

public class GamesCreateHandler extends JsonRequestHandler<CreateGameRequest>
{
	private static final Logger log = Logger.getLogger(GamesCreateHandler.class);
	
	private Server server = null;
	private Gson gson = new GsonBuilder().registerTypeAdapter(GameInfo.class, new GameInfoSerializer()).setPrettyPrinting().create();

	public GamesCreateHandler(Server server)
	{
		super(CreateGameRequest.class);
		this.server = server;
	}
	
	protected void executeRequest(CreateGameRequest request, Map<String, ICookie> cookies) throws ServerException
	{
		log.trace("Executing request");
		GameInfo game = server.getServerLobby().getGamesFacade().create(request);
		log.trace("Created game #" + game.getId() + "\"" + game.getTitle() + "\"");
		
        Type gameInfoType = new TypeToken<GameInfo>(){}.getType();
		JsonResponse response = new JsonResponse(200);
		response.setJsonBody(game, gameInfoType);
		log.trace("Setting response");
		setResponse(response);
	}
	
	public class GameInfoSerializer implements JsonSerializer<GameInfo>
	{

		@Override
		public JsonElement serialize(GameInfo src, Type typeOfSrc, JsonSerializationContext context)
		{
	        // object (for which this serializer is registered)
	        JsonObject object = new JsonObject();

	        object.addProperty("title", src.getTitle());
	        object.addProperty("id", src.getId());
	        
	        JsonArray playerArray = new JsonArray();
	        for (PlayerInfo player : src.getPlayers().toArray(new PlayerInfo[4]))
	        {
	        	if (player == null)
	        	{
	        		playerArray.add(new JsonObject());
	        	}
	        	else
	        	{
	        		playerArray.add(context.serialize(player));
	        	}
	        }
	        
	        object.add("players", playerArray);
	        return object;
	        
	        //  Returned in the form:
			//  {
			//      "title": "Supergame",
			//      "id": 3,
			//      "players": [
			//          {},
			//          {},
			//          {},
			//          {}
			//      ]
			//  }
		}
		
	}

}
