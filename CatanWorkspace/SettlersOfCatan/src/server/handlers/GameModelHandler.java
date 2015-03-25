package server.handlers;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;

import server.Server;
import server.ServerGame;
import server.comm.response.AbstractResponse;
import server.comm.response.JsonResponse;
import server.comm.response.MessageResponse;
import shared.comm.ServerException;

public class GameModelHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(GameModelHandler.class);
	
	private Server server = null;

	public GameModelHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		AbstractResponse response = null;
		
		log.debug("/games/join begun");
		
		log.trace("Verifying user credentials");
		try
		{
			verifyUser(exchange, server);
			log.trace("User is valid");

			Integer version = null;
			String query = exchange.getRequestURI().getQuery();
			if (query != null)
			{
				log.trace("Query exists. Parsing \"" + query + "\"");
				String key = query.split("=")[0];
				String value = query.substring(key.length());
				if (!key.equals("version"))
					throw new ServerException("Invalid get request format");
				try {
					version = Integer.valueOf(value);
				} catch (NumberFormatException e)
				{
					throw new ServerException("Invalid get request format");
				}
			}
			
			ServerGame game = getGame(exchange, server);
			
			if (version == null || game.getModel().getVersion() > version)
			{
				log.trace("Newer version exists. Client(" + version + ") Server(" + game.getModel().getVersion() + ")");
				response = new JsonResponse(200);
				((JsonResponse)response).setJsonBody(game.getModel());
			}
			else
			{
				log.trace("No new version for client. Client(" + version + ") Server(" + game.getModel().getVersion() + ")");
				response = new MessageResponse(200, "\"true\"");
			}
		}
		catch (ServerException e)
		{
			response = new MessageResponse(400, e.getMessage());
		}

		log.trace("Adding response headers and cookies");
		addResponseHeaders(exchange, response);
		
		log.trace("Sending response");
		sendResponse(exchange, response);
		
		log.trace("/games/join finished");
	}

}
