package server.handlers;

import org.apache.log4j.Logger;

import server.Server;
import server.comm.response.MessageResponse;
import shared.comm.serialization.CredentialsRequest;

public class UserRegisterHandler extends UserHandler
{
	private static final Logger log = Logger.getLogger(UserRegisterHandler.class);

	public UserRegisterHandler(Server server)
	{
		super(server);
	}

	@Override
	protected MessageResponse handleCredentials(CredentialsRequest request)
	{
		log.trace("Registering credentials");
		MessageResponse response = server.getServerLobby().getUserFacade().register(request);
		log.trace("User register result : " + (response != null ? response.getResponseCode() : 999));
		return response;
	}

}
