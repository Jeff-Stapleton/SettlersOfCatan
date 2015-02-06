package comm.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

import shared.CatanModel;
import shared.ResourceList;
import shared.TradeOffer;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import comm.shared.serialization.AcceptTradeRequest;
import comm.shared.serialization.BuildCityRequest;
import comm.shared.serialization.BuildRoadRequest;
import comm.shared.serialization.BuildSettlementRequest;
import comm.shared.serialization.BuyDevCardRequest;
import comm.shared.serialization.ChangeLogLevelRequest;
import comm.shared.serialization.CreateGameRequest;
import comm.shared.serialization.CredentialsRequest;
import comm.shared.serialization.DiscardCardsRequest;
import comm.shared.serialization.FinishTurnRequest;
import comm.shared.serialization.GameResponse;
import comm.shared.serialization.JoinGameRequest;
import comm.shared.serialization.LoadGameRequest;
import comm.shared.serialization.MaritimeTradeRequest;
import comm.shared.serialization.MonopolyRequest;
import comm.shared.serialization.MonumentRequest;
import comm.shared.serialization.RoadBuildingRequest;
import comm.shared.serialization.RobPlayerRequest;
import comm.shared.serialization.RollNumberRequest;
import comm.shared.serialization.SaveGameRequest;
import comm.shared.serialization.SendChatRequest;
import comm.shared.serialization.SoldierRequest;
import comm.shared.serialization.YearOfPlentyRequest;

/**
 * A proxy for the http server that will call all the sever
 * command with coresponding names. This intermediate class
 * can be replaced with the FakeServerProxy for testing
 * purposes
 * @author Cory Beutler
 *
 */
public class ServerProxy extends AbstractServerProxy
{
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
	
	/**
	 * Log an existing user into the server
	 * This function will also set the cookie for the system
	 * @param user the username of the player
	 * @param password the password for the player
	 * @throws IOException
	 */
	@Override
	public void userLogin(String user, String password) throws IOException
	{
		String json = gson.toJson(new CredentialsRequest(user, password));
        
        HttpPost httpPost = new HttpPost(_server + "/user/login");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
        _httpClient.execute(httpPost, userHandler);
	}
	
	/**
	 * Register a new user with the server
	 * This function sets the cookie for the system
	 * @param user the player's unique username
	 * @param password the players password
	 * @throws IOException
	 */
	@Override
	public void userRegister(String user, String password) throws IOException
	{
		String json = gson.toJson(new CredentialsRequest(user, password));
        
        HttpPost httpPost = new HttpPost(_server + "/user/register");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
        _httpClient.execute(httpPost, userHandler);
	}
	
	/**
	 * List the current games on the server that the player can join
	 * @return an array of the games on the server
	 * @throws IOException
	 */
	@Override
	public GameResponse[] gamesList() throws IOException
	{
		ResponseHandler<GameResponse[]> gamesListHandler = new ResponseHandler<GameResponse[]>() {
	        public GameResponse[] handleResponse(final HttpResponse response) throws IOException
	        {
			    int status = response.getStatusLine().getStatusCode();
			    if (status == 200) {
			        HttpEntity entity = response.getEntity();
			        return entity != null ? gson.fromJson(EntityUtils.toString(entity), GameResponse[].class) : null;
			    } else {
			    	throwResponseError(response);
			    	return null;
			    }
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
	 * Create a new game. This game will contain only the player initially.
	 * @param name the name of the game
	 * @param randomTiles whether the game will have random tile position
	 * @param randomNumbers whether the numbers of each hex will be random
	 * @param randomPorts whether the port positions will be random
	 * @return the game object for the game created by the user
	 * @throws IOException
	 */
	@Override
	public GameResponse gamesCreate(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws IOException
	{
		ResponseHandler<GameResponse> gamesCreateHandler = new ResponseHandler<GameResponse>() {
	        public GameResponse handleResponse(final HttpResponse response) throws IOException
	        {
			    int status = response.getStatusLine().getStatusCode();
			    if (status == 200) {
			        HttpEntity entity = response.getEntity();
			        return entity != null ? gson.fromJson(EntityUtils.toString(entity), GameResponse.class) : null;
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
        return _httpClient.execute(httpPost, gamesCreateHandler);
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
		String jsonRequest = gson.toJson(new SaveGameRequest(id, name));
		
	}
	
	/**
	 * Load the game's state from the given location
	 * @param id the location of the game to load on the server
	 * @throws IOException
	 */
	@Override
	public void gamesLoad(String name) throws IOException
	{
		String jsonRequest = gson.toJson(new LoadGameRequest(name));
		
	}
	
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
	 * Retrieve the model of the game board regardless of version number
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	public CatanModel gameModel() throws IOException
	{
//		String jsonResponse = null;
		
		HttpGet httpGet = new HttpGet(_server + "/game/model");
		if (null != getCookie())
		{
			httpGet.addHeader("Cookie", getCookie());
		}
		
//		HttpResponse response = _httpClient.execute(httpGet);
//		
//		int status = response.getStatusLine().getStatusCode();
//		if (status == 200)
//		{
//			jsonResponse = EntityUtils.toString(response.getEntity());
//		}
//		else
//		{
//        	throwResponseError(response);
//        	return null;
//		}
//		
//		return gson.fromJson(jsonResponse, CatanModel.class);
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
		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}

		if (version >= 0)
		{
			headers.put("version", Integer.toString(version));
		}

		String jsonResponse = ""; //sendGet("/game/model", headers);
		
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Reset the game to the saved state or the initial setup
	 * @throws IOException
	 */
	@Override
	public void gameReset() throws IOException
	{
		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
	}
	
	/**
	 * Send a command to the server for debugging purposes
	 * @param command the command to send to the server
	 * @throws IOException
	 */
	@Override
	public void gameCommandsPost(String[] commands) throws IOException
	{
		String jsonRequest = gson.toJson(commands);
		
		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		
	}
	
	/**
	 * Fetch the list of commands that the server has executed for debug purposes
	 * @return the commands executed on the server
	 * @throws IOException
	 */
	@Override
	public String[] gameCommandsGet() throws IOException
	{
		String jsonResponse = null;
		
		HttpGet httpGet = new HttpGet(_server + "/game/commands");
		if (null != getCookie())
		{
			httpGet.addHeader("Cookie", getCookie());
		}
		
		HttpResponse response = _httpClient.execute(httpGet);
		
		int status = response.getStatusLine().getStatusCode();
		if (status == 200)
		{
			jsonResponse = EntityUtils.toString(response.getEntity());
		}
		else
		{
        	throwResponseError(response);
        	return null;
		}
		
		return gson.fromJson(jsonResponse, String[].class);
	}
	
	/**
	 * Add an AI player to the game
	 * @param aiType the type of AI player to add to the game
	 * @throws IOException
	 */
	@Override
	public void gameAddAI(String aiType) throws IOException
	{
//		String jsonRequest = gson.toJson(new AddAIRequest(aiType));
		
	}
	
	/**
	 * Retrieve a list of the AI players in the game
	 * @throws IOException
	 */
	@Override
	public void gameListAI() throws IOException
	{
//		String jsonResponse = null;
		
		HttpGet httpGet = new HttpGet(_server + "/game/listAI");
		if (null != getCookie())
		{
			httpGet.addHeader("Cookie", getCookie());
		}
		
		HttpResponse response = _httpClient.execute(httpGet);
		
		int status = response.getStatusLine().getStatusCode();
		if (status == 200)
		{
//			jsonResponse = EntityUtils.toString(response.getEntity());
		}
		else
		{
        	throwResponseError(response);
//			return null;
		}
		
//		return gson.fromJson(jsonResponse, String[].class);
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
		String jsonRequest = gson.toJson(new ChangeLogLevelRequest(logLevel));
		
	}

}
