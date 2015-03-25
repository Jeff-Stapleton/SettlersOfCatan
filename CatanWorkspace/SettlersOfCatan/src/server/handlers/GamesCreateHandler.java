package server.handlers;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;

import client.view.data.GameInfo;
import server.Server;
import server.comm.response.AbstractResponse;
import server.comm.response.JsonResponse;
import server.comm.response.MessageResponse;
import shared.comm.ServerException;
import shared.comm.cookie.ICookie;
import shared.comm.serialization.CreateGameRequest;

public class GamesCreateHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(GamesCreateHandler.class);
	
	private Server server = null;

	public GamesCreateHandler(Server server)
	{
		super();
		this.server = server;
	}
	
	protected void executeRequest(CreateGameRequest request, Map<String, ICookie> cookies) throws ServerException
	{
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		AbstractResponse response = null;
		CreateGameRequest request = null;
		
		log.debug("/games/create begun");
		
		try
		{
			log.trace("creating request body object");
			request = getRequest(exchange, CreateGameRequest.class);
			
			log.trace("Executing request");
			GameInfo game = server.getServerLobby().getGamesFacade().create(request);
			log.trace("Created game #" + game.getId() + "\"" + game.getTitle() + "\"");
			
			response = new JsonResponse(200);
			((JsonResponse)response).setJsonBody(game, JsonResponse.GAME_INFO_TYPE);
		}
		catch (ServerException e)
		{
			response = new MessageResponse(400, e.getMessage());
		}

		log.trace("Adding response headers and cookies");
		addResponseHeaders(exchange, response);
		
		log.trace("Sending response");
		sendResponse(exchange, response);
		
		log.trace("/games/create finished");
	}

}
