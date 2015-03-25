package server.handlers;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;

import server.Server;
import server.comm.response.MessageResponse;

public class GameListAIHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(GameListAIHandler.class);

	public GameListAIHandler(Server server)
	{
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try
		{
			log.trace("/game/listAI begun");
			
			log.trace("Converting list to json");
			MessageResponse response = new MessageResponse(200, "[\"NONE\"]");
			response.setContentType("application/json");
	
			log.trace("Adding response headers and cookies");
			addResponseHeaders(exchange, response);
			
			log.trace("Sending response");
			sendResponse(exchange, response);
			
			log.trace("/game/listAI finished");
		}
		catch(Exception e)
		{
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
