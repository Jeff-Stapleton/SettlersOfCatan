package server.handlers;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;

import server.Server;
import server.comm.response.MessageResponse;
import shared.comm.serialization.CredentialsRequest;

public class UserRegisterHandler extends SimpleHandler
{
	private static final Logger log = Logger.getLogger(UserRegisterHandler.class);

	private Server server = null;
	
	public UserRegisterHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try{
			log.debug("/user/register begun");
			log.trace("creating request body object");
			CredentialsRequest request = getRequest(exchange, CredentialsRequest.class);
			
			log.trace("Register user credentials");
			MessageResponse response = server.getServerLobby().getUserFacade().register(request);
	
			log.trace("Adding response headers and cookies");
			addResponseHeaders(exchange, response);
			
			log.trace("Sending response");
			sendResponse(exchange, response);
			
			log.trace("/user/register finished");
		}
		catch (Exception e){
			e.printStackTrace();
			throw(e);
		}
	}

}
