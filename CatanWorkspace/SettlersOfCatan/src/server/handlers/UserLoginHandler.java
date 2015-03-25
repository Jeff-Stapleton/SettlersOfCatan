package server.handlers;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;

import server.Server;
import server.comm.response.MessageResponse;
import shared.comm.serialization.CredentialsRequest;

public class UserLoginHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(UserLoginHandler.class);
	
	private Server server = null;

	public UserLoginHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try{
			log.debug("/user/login begun");
			log.trace("creating request body object");
			CredentialsRequest request = getRequest(exchange, CredentialsRequest.class);
			
			log.trace("Verifying user credentials");
			MessageResponse response = server.getServerLobby().getUserFacade().login(request);
	
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
