package server.facade;

import org.apache.log4j.Logger;

import server.ServerLobby;
import server.comm.response.MessageResponse;
import server.models.ServerUser;
import shared.comm.cookie.PlayerCookie;
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
	 * @return 
	 */
	public MessageResponse register(CredentialsRequest request)
	{
		log.trace("Registering the user");
		MessageResponse response = null;
		ServerUser user = serverLobby.getUser(request.getUsername());
		if(request.getUsername().length()<3||request.getUsername().length()>7)
		{
			log.trace("Invalid username. Bad length.");
			response = new MessageResponse(400, "Failed to register user - The username must be between three and seven characters: letters, digits, _ , and -");
			
		}
		else if(!request.getUsername().matches("[0-9]*[A-z]*[_]*[-]*"))
		{
			log.trace("Invalid username. Bad characters.");
			response = new MessageResponse(400, "Failed to register user - The username must be between three and seven characters: letters, digits, _ , and -");
		}
		else if(!request.getPassword().matches("[a-zA-Z0-9_-]+"))
		{
			log.trace("Invalid password. Bad characters");
			response = new MessageResponse(400, "Failed to register user - The password must consist of the following characters: letters, digits, _ , and -");
		}
		else if(request.getPassword().length() < 5)
		{
			log.trace("password length rejected for being less than 4 chars");
			response = new MessageResponse(400, "Failed to register user - Password must be five or more characters");
		}
		else if (user != null)
		{
			log.trace("User already exists");
			response = new MessageResponse(400, "Failed to register user - name is taken");
		}
		else
		{
			log.trace("registering user credentials");
			
			if (serverLobby.addUser(request.getUsername(), request.getPassword()))
			{
				log.trace("user \"" + request.getUsername() + "\" registered");
				response = new MessageResponse(200, "Success");
			}
			else
			{
				log.trace("Unknown user registration error");
				response = new MessageResponse(500, "Unknown server error");
			}
		}
		
		return response;
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
			response.addCookie(new PlayerCookie(request.getUsername(), request.getPassword(), serverLobby.getUserID(request.getUsername())));
		}
		else
		{
			log.trace("Creating failure message response");
			response = new MessageResponse(400, "Failed to login - bad username or password.");
		}
		
		return response;
	}
}
