package client.comm;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.LinkedList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import client.view.data.GameInfo;
import client.view.data.PlayerInfo;
import shared.CatanModel;
import shared.ResourceList;
import shared.TradeOffer;
import shared.comm.cookie.PlayerCookie;
import shared.comm.serialization.AcceptTradeRequest;
import shared.comm.serialization.AddAIRequest;
import shared.comm.serialization.BuildCityRequest;
import shared.comm.serialization.BuildRoadRequest;
import shared.comm.serialization.BuildSettlementRequest;
import shared.comm.serialization.BuyDevCardRequest;
import shared.comm.serialization.ChangeLogLevelRequest;
import shared.comm.serialization.CreateGameRequest;
import shared.comm.serialization.CredentialsRequest;
import shared.comm.serialization.DiscardCardsRequest;
import shared.comm.serialization.FinishTurnRequest;
import shared.comm.serialization.JoinGameRequest;
import shared.comm.serialization.LoadGameRequest;
import shared.comm.serialization.MaritimeTradeRequest;
import shared.comm.serialization.MonopolyRequest;
import shared.comm.serialization.MonumentRequest;
import shared.comm.serialization.RoadBuildingRequest;
import shared.comm.serialization.RobPlayerRequest;
import shared.comm.serialization.RollNumberRequest;
import shared.comm.serialization.SaveGameRequest;
import shared.comm.serialization.SendChatRequest;
import shared.comm.serialization.SoldierRequest;
import shared.comm.serialization.YearOfPlentyRequest;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * A proxy for the http server that will call all the sever
 * command with corresponding names. This intermediate class
 * can be replaced with the FakeServerProxy for testing
 * purposes
 * @author Cory Beutler
 *
 */
public class ServerProxy extends AbstractServerProxy
{
	private static final Logger log = Logger.getLogger(ServerProxy.class);
	
	String _server;
	CloseableHttpClient _httpClient;
	
	public ServerProxy(String serverURL)
	{
		_server = serverURL;
		_httpClient = HttpClients.createDefault();
	}
	
	public static void throwResponseError(HttpResponse response) throws IOException
	{
        HttpEntity entity = response.getEntity();
        throw new ClientProtocolException("Unexpected response status: " + response.getStatusLine().getStatusCode() +
        		((entity != null) ? " With response body \"" + EntityUtils.toString(entity) + "\"" : ""));
	}
	
	ResponseHandler<String> stringHandler = new ResponseHandler<String>() {
        public String handleResponse(final HttpResponse response) throws IOException
        {
		    int status = response.getStatusLine().getStatusCode();
		    if (status == 200) {
		        HttpEntity entity = response.getEntity();
		        return entity != null ? EntityUtils.toString(entity) : null;
		    } else {
		    	throwResponseError(response);
		    	return null;
		    }
        }
	};
	
	ResponseHandler<String[]> stringArrayHandler = new ResponseHandler<String[]>()
	{
        public String[] handleResponse(final HttpResponse response) throws IOException
        {
		    int status = response.getStatusLine().getStatusCode();
		    if (status == 200) {
		        HttpEntity entity = response.getEntity();
		        return entity != null ? gson.fromJson(EntityUtils.toString(entity), String[].class) : null;
		    } else {
		    	throwResponseError(response);
		    	return null;
		    }
        }
	};
	
	ResponseHandler<String> userHandler = new ResponseHandler<String>() {
        public String handleResponse(final HttpResponse response) throws IOException
        {
		    int status = response.getStatusLine().getStatusCode();
		    if (status == 200) {
		    	for (Header header : response.getAllHeaders())
		    	{
		    		if (header.getName().equals("Set-cookie"))
		    		{
		    			setPlayerCookie(header.getValue());
		    			
		    			// Get the user's information from the cookie
		    			String userJson = URLDecoder.decode(getPlayerCookie().split("=")[1], "UTF-8");
		    			return userJson;
		    			
		    		}
		    	}
		    	
		        HttpEntity entity = response.getEntity();
		        return entity != null ? EntityUtils.toString(entity) : null;
		    } else {
		    	throwResponseError(response);
		    	return null;
		    }
        }
	};
	
	ResponseHandler<CatanModel> gameModelHandler = new ResponseHandler<CatanModel>()
	{
        public CatanModel handleResponse(final HttpResponse response) throws IOException
        {
		    int status = response.getStatusLine().getStatusCode();
		    if (status == 200) {
		        HttpEntity entity = response.getEntity();
		        return entity != null ? gson.fromJson(EntityUtils.toString(entity), CatanModel.class) : null;
		    } else {
		    	throwResponseError(response);
		    	return null;
		    }
        }
	};
	
	/**
	 * Log an existing user into the server
	 * This function will also set the cookie for the system
	 * @param user the username of the player
	 * @param password the password for the player
	 * @throws IOException
	 */
	@Override
	public PlayerCookie userLogin(String user, String password) throws IOException
	{
		String json = gson.toJson(new CredentialsRequest(user, password));
        
        HttpPost httpPost = new HttpPost(_server + "/user/login");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
        PlayerCookie cookie = gson.fromJson(_httpClient.execute(httpPost, userHandler), PlayerCookie.class);
        // Login success! get user info.
        return cookie;
	}
	
	/**
	 * Register a new user with the server
	 * This function sets the cookie for the system
	 * @param user the player's unique username
	 * @param password the players password
	 * @throws IOException
	 */
	@Override
	public PlayerCookie userRegister(String user, String password) throws IOException
	{
		String json = gson.toJson(new CredentialsRequest(user, password));
        
        HttpPost httpPost = new HttpPost(_server + "/user/register");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
        PlayerCookie cookie = gson.fromJson(_httpClient.execute(httpPost, userHandler), PlayerCookie.class);
        return cookie;
	}
	
	/**
	 * List the current games on the server that the player can join
	 * @return an array of the games on the server
	 * @throws IOException
	 */
	@Override
	public GameInfo[] gamesList() throws IOException
	{
		ResponseHandler<GameInfo[]> gamesListHandler = new ResponseHandler<GameInfo[]>() {
	        public GameInfo[] handleResponse(final HttpResponse response) throws IOException
	        {
	    		GameInfo[] games = null;
			    int status = response.getStatusLine().getStatusCode();
			    if (status == 200) {
			        HttpEntity entity = response.getEntity();
			        if (entity != null) {
			        	String jsonString = EntityUtils.toString(entity);
			        	games = gson.fromJson(jsonString, GameInfo[].class);
			        	
			        	// This is dumb, but remove all players that came back empty
			        	for (GameInfo game : games)
			        	{
			        		for (int i = 0; i < game.getPlayers().size(); i++)
			        		{
			        			if (game.getPlayers().get(i).getPlayerIndex() == -1)
			        			{
			        				game.getPlayers().get(i).setPlayerIndex(i);
			        			}
			        		}
			        		
			        		LinkedList<PlayerInfo> players = new LinkedList<PlayerInfo>(game.getPlayers());
			        		for (int i = players.size() - 1; i >= 0; i--)
			        		{
			        			if (players.get(i).getId() == -1)
			        			{
			        				players.remove(i);
			        			}
			        		}
			        		game.setPlayers(players);
			        	}
			        }
			    } else {
			    	throwResponseError(response);
			    }
		        return games;
	        }
		};
		
		
		HttpGet httpGet = new HttpGet(_server + "/games/list");
		if (null != getCookie())
		{
			httpGet.addHeader("Cookie", getCookie());
		}
		
		return _httpClient.execute(httpGet, gamesListHandler);
	}
	
	/**
	 * Create a new game. This game will contain no player yet.
	 * @param name the name of the game
	 * @param randomTiles whether the game will have random tile position
	 * @param randomNumbers whether the numbers of each hex will be random
	 * @param randomPorts whether the port positions will be random
	 * @return the game object for the game created by the user
	 * @throws IOException
	 */
	@Override
	public GameInfo gamesCreate(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws IOException
	{
		ResponseHandler<GameInfo> gamesCreateHandler = new ResponseHandler<GameInfo>() {
	        public GameInfo handleResponse(final HttpResponse response) throws IOException
	        {
			    int status = response.getStatusLine().getStatusCode();
			    if (status == 200) {
			        HttpEntity entity = response.getEntity();
			        return entity != null ? gson.fromJson(EntityUtils.toString(entity), GameInfo.class) : null;
			    } else {
			    	throwResponseError(response);
			    	return null;
			    }
	        }
		};

		String json = gson.toJson(new CreateGameRequest(name, randomTiles, randomNumbers, randomPorts));
		
        HttpPost httpPost = new HttpPost(_server + "/games/create");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
		GameInfo game = _httpClient.execute(httpPost, gamesCreateHandler);
        return game;
	}
	
	/**
	 * Join a currently open game on the server
	 * @param color the color the player wants to be
	 * @param id the id of the player
	 * @throws IOException
	 */
	@Override
	public void gamesJoin(CatanColor color, int id) throws IOException
	{
		ResponseHandler<String> gamesJoinHandler = new ResponseHandler<String>() {
	        public String handleResponse(final HttpResponse response) throws IOException
	        {
			    int status = response.getStatusLine().getStatusCode();
			    if (status == 200) {
			    	for (Header header : response.getAllHeaders())
			    	{
			    		if (header.getName().equals("Set-cookie"))
			    		{
			    			log.trace("Setting game cookie");
			    			setGameCookie(header.getValue());
			    		}
			    	}
			    	
			        HttpEntity entity = response.getEntity();
			        return entity != null ? EntityUtils.toString(entity) : null;
			    } else {
			    	throwResponseError(response);
			    	return null;
			    }
	        }
		};
		
		String json = gson.toJson(new JoinGameRequest(id, color.toString()));
		
        HttpPost httpPost = new HttpPost(_server + "/games/join");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        _httpClient.execute(httpPost, gamesJoinHandler);
	}
	
	/**
	 * Save the game's current state to the given location
	 * @param id the id of the game you wish to save
	 * @param name the location that the game will be saved at
	 * @throws IOException
	 */
	@Override
	public void gamesSave(int id, String name) throws IOException
	{
		String json = gson.toJson(new SaveGameRequest(id, name));
		
        HttpPost httpPost = new HttpPost(_server + "/games/save");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        _httpClient.execute(httpPost, stringHandler);
	}
	
	/**
	 * Load the game's state from the given location
	 * @param id the location of the game to load on the server
	 * @throws IOException
	 */
	@Override
	public void gamesLoad(String name) throws IOException
	{
		String json = gson.toJson(new LoadGameRequest(name));
		
        HttpPost httpPost = new HttpPost(_server + "/games/load");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        _httpClient.execute(httpPost, stringHandler);
		
	}
	
	/**
	 * Retrieve the model of the game board regardless of version number
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	public CatanModel gameModel() throws IOException
	{
		HttpGet httpGet = new HttpGet(_server + "/game/model");
		if (getPlayerCookie() == null || getGameCookie() == null)
		{
			throw new NullPointerException("Null cookie in ServerProxy gameModel()");
		}
		String cookie = getPlayerCookie() + "; " + getGameCookie();
		httpGet.addHeader("Cookie", cookie);
		
		return _httpClient.execute(httpGet, gameModelHandler);
	}
	
	/**
	 * Retrieve the model of the current game board state.
	 * @param version the last version of the game model received
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel gameModel(int version) throws IOException
	{
		HttpGet httpGet = new HttpGet(_server + "/game/model" + (version > 0 ? "?version=" + version : ""));
		if (null != getCookie())
		{
			httpGet.addHeader("Cookie", getCookie());
		}
		
		return _httpClient.execute(httpGet, gameModelHandler);
	}
	
	/**
	 * Reset the game to the saved state or the initial setup
	 * @throws IOException
	 */
	@Override
	public CatanModel gameReset() throws IOException
	{
		HttpGet httpGet = new HttpGet(_server + "/game/reset");
		if (null != getCookie())
		{
			httpGet.addHeader("Cookie", getCookie());
		}
		
		return _httpClient.execute(httpGet, gameModelHandler);
	}
	
	/**
	 * Send a command to the server for debugging purposes
	 * @param command the command to send to the server
	 * @throws IOException
	 */
	@Override
	public CatanModel gameCommandsPost(String[] commands) throws IOException
	{
		String json = gson.toJson(commands);
		HttpPost httpPost = new HttpPost(_server + "/game/commands");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Fetch the list of commands that the server has executed for debug purposes
	 * @return the commands executed on the server
	 * @throws IOException
	 */
	@Override
	public String[] gameCommandsGet() throws IOException
	{
		HttpGet httpGet = new HttpGet(_server + "/game/commands");
		if (null != getCookie())
		{
			httpGet.addHeader("Cookie", getCookie());
		}
		
		return _httpClient.execute(httpGet, stringArrayHandler);
	}
	
	/**
	 * Add an AI player to the game
	 * @param aiType the type of AI player to add to the game
	 * @throws IOException
	 */
	@Override
	public void gameAddAI(String aiType) throws IOException
	{
		String json = gson.toJson(new AddAIRequest(aiType));
		HttpPost httpPost = new HttpPost(_server + "/game/addAI");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		
		_httpClient.execute(httpPost, stringHandler);
	}
	
	/**
	 * Retrieve a list of the AI players in the game
	 * @throws IOException
	 */
	@Override
	public String[] gameListAI() throws IOException
	{
		HttpGet httpGet = new HttpGet(_server + "/game/listAI");
		return _httpClient.execute(httpGet, stringArrayHandler);
	}

	/**
	 * Send a chat to the current game chat list
	 * @param playerIndex the index of the player sending the chat message
	 * @param content the content of the message the player wants to send
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesSendChat(int playerIndex, String content) throws IOException
	{
		String json = gson.toJson(new SendChatRequest(playerIndex, content));
		
        HttpPost httpPost = new HttpPost(_server + "/moves/sendChat");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	
	/**
	 * Roll a number on the dice and send it to the server
	 * @param playerIndex the index of the player rolling the dice
	 * @param number the number the player rolled (This is stupid)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesRollNumber(int playerIndex, int number) throws IOException
	{
		String json = gson.toJson(new RollNumberRequest(playerIndex, number));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/rollNumber");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Execute the robbing phase of a players turn if they rolled a 7
	 * @param playerIndex the player doing the robbing
	 * @param victimIndex the player being robbed from
	 * @param location the location to move the robber to
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesRobPlayer(int playerIndex, int victimIndex, HexLocation location) throws IOException
	{
		String json = gson.toJson(new RobPlayerRequest(playerIndex, victimIndex, location));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/robPlayer");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}

	/**
	 * Finish the turn of a player
	 * @param playerIndex the index of the player ending their turn
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesFinishTurn(int playerIndex) throws IOException
	{
		String json = gson.toJson(new FinishTurnRequest(playerIndex));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/finishTurn");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Buy a dev card for the player
	 * @param playerIndex the player buying the dev card
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesBuyDevCard(int playerIndex) throws IOException
	{
		String json = gson.toJson(new BuyDevCardRequest(playerIndex));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/buyDevCard");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Play a Year of Plenty dev card for the player
	 * @param playerIndex the player playing the dev card
	 * @param resource1 the resource the player wants
	 * @param resource2 the resource the player wants
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) throws IOException
	{
		String json = gson.toJson(new YearOfPlentyRequest(playerIndex, resource1.toString(), resource2.toString()));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/Year_of_Plenty");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Play a Road Building dev card to give the player 2 roads
	 * @param playerIndex the index of the player building the road
	 * @param spot1 the location for a road being built
	 * @param spot2 the location for the second road being built
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) throws IOException
	{
		String json = gson.toJson(new RoadBuildingRequest(playerIndex, spot1, spot2));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/Road_Building");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Play a Soldier dev card to steal resources from another player
	 * @param playerIndex the player playing the dev card
	 * @param victimIndex the victim of the dev card
	 * @param location the robber location
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesSoldier(int playerIndex, int victimIndex, HexLocation location) throws IOException
	{
		String json = gson.toJson(new SoldierRequest(playerIndex, victimIndex, location));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/Soldier");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Play a Monopoly dev card to allow the player to steal resources
	 * @param playerIndex the player playing the Monopoly card
	 * @param resource the resource the player wishes to steal
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesMonopoly(int playerIndex, ResourceType resource) throws IOException
	{
		String json = gson.toJson(new MonopolyRequest(playerIndex, resource.toString()));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/Monopoly");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Play a Monument dev card for the specified player
	 * @param playerIndex the player playing the dev card
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesMonument(int playerIndex) throws IOException
	{
		String json = gson.toJson(new MonumentRequest(playerIndex));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/Monument");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Build a road for the player in the given location
	 * @param playerIndex the player building the road
	 * @param location the location the player is building the road
	 * @param free whether this road is free or not (This is stupid)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesBuildRoad(int playerIndex, EdgeLocation location, boolean free) throws IOException
	{
		String json = gson.toJson(new BuildRoadRequest(playerIndex, location, free));

        HttpPost httpPost = new HttpPost(_server + "/moves/buildRoad");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Build a settlement for the player at the given location
	 * @param playerIndex the index of the player building the settlement
	 * @param location the location of the settlement
	 * @param free whether the settlement is free or not (Why?)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesBuildSettlement(int playerIndex, VertexLocation location, boolean free) throws IOException
	{
		String json = gson.toJson(new BuildSettlementRequest(playerIndex, location, free));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/buildSettlement");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Build a city at the given location
	 * @param playerIndex the player building the city
	 * @param location the location of the city bing built
	 * @param free whether the city will be free (I still don't get this...)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesBuildCity(int playerIndex, VertexLocation location, boolean free) throws IOException
	{
		String json = gson.toJson(new BuildCityRequest(playerIndex, location, free));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/buildCity");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Offer a trade with another player
	 * @param offer the offer of trade
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesOfferTrade(TradeOffer offer) throws IOException
	{
		String json = gson.toJson(offer);
		
        HttpPost httpPost = new HttpPost(_server + "/moves/offerTrade");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Accept a trade proposed to you
	 * @param playerIndex the index of the player accepting the trade
	 * @param willAccept whether the player accepts or rejects the offer
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesAcceptTrade(int playerIndex, boolean willAccept) throws IOException
	{
		String json = gson.toJson(new AcceptTradeRequest(playerIndex, willAccept));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/acceptTrade");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Process a port trade for the given player with the given ratio and resource types
	 * @param playerIndex the player doing the maritime trade
	 * @param ratio the ratio to trade the resources at
	 * @param inputResource the input resource type
	 * @param outputResource the output resource type
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesMaritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource) throws IOException
	{
		String json = gson.toJson(new MaritimeTradeRequest(playerIndex, ratio, inputResource.toString(), outputResource.toString()));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/maritimeTrade");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}

	/**
	 * Discard cards from the player's hand
	 * @param playerIndex the player discarding the card
	 * @param cards the cards the player is discarding
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public CatanModel movesDiscardCards(int playerIndex, ResourceList cards) throws IOException
	{
		String json = gson.toJson(new DiscardCardsRequest(playerIndex, cards));
        
        HttpPost httpPost = new HttpPost(_server + "/moves/discardCards");
		if (null != getCookie())
		{
			httpPost.addHeader("Cookie", getCookie());
		}
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		return _httpClient.execute(httpPost, gameModelHandler);
	}
	
	/**
	 * Change the logging level for the server
	 * @param logLevel the desired logging level for the server
	 * @throws IOException
	 */
	@Override
	public void utilChangeLogLevel(String logLevel) throws IOException
	{
		String json = gson.toJson(new ChangeLogLevelRequest(logLevel));

        HttpPost httpPost = new HttpPost(_server + "/util/changeLogLevel");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
		_httpClient.execute(httpPost, stringHandler);
	}

}
