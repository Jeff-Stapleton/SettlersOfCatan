package server.facade;

import org.apache.log4j.Logger;

import server.ServerLobby;
import server.comm.response.MessageResponse;
import shared.comm.serialization.CredentialsRequest;

/**
 * The Class UserFacade, This Facade implements the Login and Register commands
 */
public class UserFacade
{
	private static final Logger log = Logger.getLogger(UserFacade.class);
	
	/** The game lobby. */
	private ServerLobby serverLobby;
	

	public UserFacade(ServerLobby serverLobby)
	{
		this.serverLobby = serverLobby;
	}

	/**
	 * Registers a user
	 *
	 * Pre and post are handled in the request and response
	 */
	public void register(CredentialsRequest request)
	{
		
	}

	/**
	 * Logs a user in
	 *
	 * Pre and post are handled in the request and response
	 * @return 
	 */
	public MessageResponse login(CredentialsRequest request)
	{
		log.trace("logging in the user");
		MessageResponse response = null;
		boolean success = serverLobby.verifyUser(request.getUsername(), request.getPassword());
		
		if (success)
		{
			log.trace("Creating success message response");
			response = new MessageResponse(200, "Success");
		}
		else
		{
			log.trace("Creating failure message response");
			response = new MessageResponse(400, "Failed to login - bad username or password.");
		}
		
		return response;
	}
}
