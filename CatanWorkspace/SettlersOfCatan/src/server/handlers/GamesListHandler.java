package server.handlers;

import java.io.IOException;
import org.apache.log4j.Logger;

import client.view.data.GameInfo;

import com.sun.net.httpserver.HttpExchange;

import server.Server;
import server.comm.response.JsonResponse;

public class GamesListHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(GamesListHandler.class);
	
	private Server server = null;

	public GamesListHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try
		{
			log.trace("/games/list begun");
			
			log.trace("Getting list of games");
			GameInfo[] games = server.getServerLobby().getGamesFacade().list();
			
			log.trace("Converting list to json");
			JsonResponse response = new JsonResponse(200);
			((JsonResponse)response).setJsonBody(games, JsonResponse.GAME_INFO_ARRAY_TYPE);
	
			log.trace("Adding response headers and cookies");
			addResponseHeaders(exchange, response);
			
			log.trace("Sending response");
			sendResponse(exchange, response);
			
			log.trace("/games/list finished");
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
