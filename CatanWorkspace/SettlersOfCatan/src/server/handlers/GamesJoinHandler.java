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
import server.models.ServerUser;
import shared.comm.ServerException;
import shared.comm.cookie.ICookie;
import shared.comm.cookie.PlayerCookie;
import shared.comm.serialization.CreateGameRequest;
import shared.comm.serialization.JoinGameRequest;

public class GamesJoinHandler extends JsonRequestHandler<JoinGameRequest>
{
	private static final Logger log = Logger.getLogger(GamesJoinHandler.class);
	
	private Server server = null;

	public GamesJoinHandler(Server server)
	{
		super(JoinGameRequest.class);
		this.server = server;
	}
	
	protected void executeRequest(JoinGameRequest request, Map<String, ICookie> cookies) throws ServerException
	{
		log.trace("Executing join request");
		PlayerCookie userCookie = (PlayerCookie)cookies.get("catan.user");
		if (userCookie == null)
		{
			throw new ServerException("Login before you join a game");
		}
		if (!server.getServerLobby().verifyUser(userCookie.getName(), userCookie.getPassword()))
		{
			throw new ServerException("Unable to verify user information");
		}
		
		ServerUser user = server.getServerLobby().getUser(userCookie.getPlayerId());
		GameInfo game = server.getServerLobby().getGamesFacade().join(user, request);
		log.trace("Created game #" + game.getId() + "\"" + game.getTitle() + "\"");
		
        Type gameInfoType = new TypeToken<GameInfo>(){}.getType();
		JsonResponse response = new JsonResponse(200);
		response.setJsonBody(game, gameInfoType);
		log.trace("Setting response");
		setResponse(response);
	}

}
