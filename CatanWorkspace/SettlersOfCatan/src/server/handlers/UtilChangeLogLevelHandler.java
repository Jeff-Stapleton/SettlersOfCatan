package server.handlers;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;

import server.Server;
import server.comm.response.MessageResponse;
import shared.comm.serialization.ChangeLogLevelRequest;

public class UtilChangeLogLevelHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(UtilChangeLogLevelHandler.class);
	
	private Server server = null;

	public UtilChangeLogLevelHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try{
			log.debug("/user/login begun");
			log.trace("creating request body object");
			ChangeLogLevelRequest request = getRequest(exchange, ChangeLogLevelRequest.class);
			
			log.trace("Verifying user credentials");
			MessageResponse response = server.getServerLobby().getUtilFacade().changeLogLevel(request.getLogLevel());
	
			log.trace("Adding response headers and cookies");
			addResponseHeaders(exchange, response);
			
			log.trace("Sending response");
			sendResponse(exchange, response);
			
			log.trace("/user/login finished");
		}
		catch (Exception e){
			e.printStackTrace();
			throw(e);
		}
	}

}
