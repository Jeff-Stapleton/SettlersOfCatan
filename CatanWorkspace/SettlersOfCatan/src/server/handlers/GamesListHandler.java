package server.handlers;

import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import client.view.data.GameInfo;

import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpExchange;

import server.Server;
import server.comm.cookie.ICookie;
import server.comm.response.CatanModelResponse;
import server.comm.response.IResponse;
import server.comm.response.JsonResponse;
import server.comm.response.MessageResponse;
import shared.comm.serialization.CredentialsRequest;

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
		JsonResponse response = new CatanModelResponse(gson.toJson(games));
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
