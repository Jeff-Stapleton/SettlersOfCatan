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
import shared.comm.serialization.BuyDevCardRequest;

public class GameResetHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(GameModelHandler.class);
	
	private Server server = null;

	public GameResetHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try{
			AbstractResponse response = null;
			
			log.debug("/game/reset begun");
			
			log.trace("Verifying user credentials");
			try
			{
				verifyUser(exchange, server);
					
				ServerGame game = getGame(exchange, server);
				
				game.getGameFacade().reset();
				
				response = new JsonResponse(200);
				((JsonResponse)response).setJsonBody(game.getModel());
			}
			catch (ServerException e)
			{
				response = new MessageResponse(400, e.getMessage());
			}
	
			log.trace("Adding response headers and cookies");
			addResponseHeaders(exchange, response);
			
			log.trace("Sending response");
			sendResponse(exchange, response);
			
			log.trace("/game/reset finished");
		}
		catch (Exception e){
			e.printStackTrace();
			throw(e);
		}
	}

}
