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
import shared.comm.serialization.DiscardCardsRequest;

public class MovesDiscardCardsHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(MovesDiscardCardsHandler.class);
	
	private Server server = null;

	public MovesDiscardCardsHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try{
			AbstractResponse response = null;
			
			log.trace("/moves/discardCards begun");
			
			log.trace("Verifying user credentials");
			try
			{
				verifyUser(exchange, server);
				log.trace("User is valid");
	
				log.trace("creating request body object");
				DiscardCardsRequest request = getRequest(exchange, DiscardCardsRequest.class);
				log.trace("getting Game from the server");
				ServerGame game = getGame(exchange, server);
				log.trace("discard cards!!!!!");
				game.getMovesFacade().discardCards(request);
				log.trace("Chat message added to model");
				response = new JsonResponse(200);
				((JsonResponse)response).setJsonBody(game.getModel());
			}
			catch (ServerException e)
			{
				log.trace("Could not discard: " + e.getMessage());
				response = new MessageResponse(400, e.getMessage());
			}
	
			log.trace("Adding response headers and cookies");
			addResponseHeaders(exchange, response);
			
			log.trace("Sending response");
			sendResponse(exchange, response);
			
			log.trace("/moves/discardCards finished");
		}
		catch (Exception e){
			e.printStackTrace();
			throw(e);
		}
	}

}
