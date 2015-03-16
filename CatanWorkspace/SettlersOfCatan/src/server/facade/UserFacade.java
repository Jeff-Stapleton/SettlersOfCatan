package server.facade;

import server.comm.request.UserLoginRequest;
import server.comm.request.UserRegisterRequest;
import client.CatanLobby;

/**
 * The Class UserFacade, This Facade implements the Login and Register commands
 */
public class UserFacade {
	
	/** The game lobby. */
	private CatanLobby gameLobby;
	

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
