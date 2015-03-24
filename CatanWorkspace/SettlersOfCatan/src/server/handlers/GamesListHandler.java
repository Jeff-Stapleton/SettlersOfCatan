package server.handlers;

import org.apache.log4j.Logger;

import client.view.data.GameInfo;

import com.sun.net.httpserver.HttpExchange;

import server.Server;
import server.comm.response.IResponse;
import server.comm.response.JsonResponse;

public class GamesListHandler extends GamesHandler
{
	private static final Logger log = Logger.getLogger(GamesListHandler.class);

	public GamesListHandler(Server server)
	{
		super(server);
	}

	@Override
	protected void initFromExchange(HttpExchange exchange)
	{
		// Get request. No body to retrieve
	}

	@Override
	protected IResponse handleRequest()
	{
		GameInfo[] games = server.getServerLobby().getGamesFacade().list();
		JsonResponse response = new JsonResponse(200, gson.toJson(games));
		return response;
	}

//	@Override
//	protected MessageResponse handleCredentials(CredentialsRequest request)
//	{
//		log.trace("Verifying user credentials");
//		MessageResponse response = server.getServerLobby().getUserFacade().login(request);
//		log.trace("User validation result : " + (response != null));
//		return response;
//	}

}
