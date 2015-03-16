package server.facade;

import server.ServerLobby;
import server.comm.request.UserLoginRequest;
import server.comm.request.UserRegisterRequest;

/**
 * The Class UserFacade, This Facade implements the Login and Register commands
 */
public class UserFacade {
	
	/** The game lobby. */
	private ServerLobby serverLobby;
	

	public UserFacade(ServerLobby serverLobby) {
		this.serverLobby = serverLobby;
	}

	/**
	 * Registers a user
	 *
	 * Pre and post are handled in the request and response
	 */
	public void register(UserRegisterRequest request){
		
	}

	/**
	 * Logs a user in
	 *
	 * Pre and post are handled in the request and response
	 */
	public void login(UserLoginRequest request){
		
	}
}
