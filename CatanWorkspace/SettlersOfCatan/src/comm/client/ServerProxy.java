package comm.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
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
import comm.shared.RestCom;
import comm.shared.ServerException;
import comm.shared.resty.PostRequest;
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
	RestCom con = new RestCom();
	
	String _server;
	
	public ServerProxy(String serverURL)
	{
		_server = serverURL;
	}

	/**
	 * Send a get request to the server with the specified headers
	 * @param serverPath the path to send the get request to. This is appended to the server url
	 * @param headers the headers to add to the get request
	 * @throws IOException
	 */
	private String sendGet(String serverPath, Map<String,String> headers)
	{
		HttpsURLConnection con = null;
		try
		{
			String urlString = _server + serverPath;
			URL url = new URL(urlString);
			con = (HttpsURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			
			if (null != headers)
			{
				for (Map.Entry<String, String> header : headers.entrySet())
				{
					con.setRequestProperty(header.getKey(), header.getValue());
				}
			}
			
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + urlString);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			StringBuffer response = new StringBuffer();
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println(response.toString());
			
			return response.toString();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (null != con)
			{
				con.disconnect();
				con = null;
			}
		}
	}
	
//	private String sendPost(String serverPath, String requestBody, Map<String, String> headers) throws IOException
//	{
//		HttpsURLConnection con = null;
//		try
//		{
//			String urlString = _server + serverPath;
//			URL url = new URL(urlString);
//			con = (HttpsURLConnection) url.openConnection();
//	 
//			con.setRequestMethod("POST");
//			
//			if (null != headers)
//			{
//				for (Map.Entry<String, String> header : headers.entrySet())
//				{
//					con.setRequestProperty(header.getKey(), header.getValue());
//				}
//			}
//	 
//			// Send post request
//			con.setDoOutput(true);
//			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//			wr.writeBytes(requestBody);
//			wr.flush();
//			wr.close();
//	 
//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'POST' request to URL : " + urlString);
//			System.out.println("Post parameters : " + requestBody);
//			System.out.println("Response Code : " + responseCode);
//	 
//			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//			
//			StringBuffer response = new StringBuffer();
//			String inputLine;
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//	 
//			//print result
//			System.out.println(response.toString());
//			
//			return response.toString();
//		}
//		catch(IOException e)
//		{
//			e.printStackTrace();
//			return null;
//		}
//		finally
//		{
//			if (null != con)
//			{
//				con.disconnect();
//				con = null;
//			}
//		}
//	}
	
	/**
	 * Log an existing user into the server
	 * This function will also set the cookie for the system
	 * @param user the username of the player
	 * @param password the password for the player
	 * @throws ServerException
	 */
	@Override
	public void userLogin(String user, String password) throws IOException
	{
        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    HttpEntity entity = response.getEntity();
                    throw new ClientProtocolException("Unexpected response status: " + status +
                    		((entity != null) ? " With response body \"" + EntityUtils.toString(entity) + "\"" : ""));
                }
            }

        };
        
		String json = gson.toJson(new CredentialsRequest(user, password));
		System.out.println(json);
        
        
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8081/user/login");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
        //CloseableHttpResponse response;
        /*String responseBody = */httpClient.execute(httpPost, responseHandler);


        
        
		
		
//		PostRequest request = con.createPostRequest("/user/login");
//		request.setJsonBody(new CredentialsRequest(user, password));
//		
//		Response response = request.getResponse();
//		
//		if (response.getStatusCode() != 200) {
//			throw new ServerException(response.getStatusCode() + response.getResponseBody());
//		}
	}
	
	/**
	 * Register a new user with the server
	 * This function sets the cookie for the system
	 * @param user the player's unique username
	 * @param password the players password
	 * @throws ServerException
	 */
	@Override
	public void userRegister(String user, String password) throws IOException
	{

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(
                    final HttpResponse response) throws IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    HttpEntity entity = response.getEntity();
                    throw new ClientProtocolException("Unexpected response status: " + status +
                    		((entity != null) ? " With response body \"" + EntityUtils.toString(entity) + "\"" : ""));
                }
            }

        };

		String json = gson.toJson(new CredentialsRequest(user, password));
		System.out.println(json);
        
        
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8081/user/register");
        httpPost.setEntity(EntityBuilder.create().setText(json).setContentType(ContentType.APPLICATION_JSON).build());
        //CloseableHttpResponse response;
        /*String responseBody = */httpClient.execute(httpPost, responseHandler);
	}
	
	/**
	 * List the current games on the server that the player can join
	 * @return an array of the games on the server
	 * @throws ServerException
	 */
	@Override
	public GameResponse[] gamesList() throws ServerException
	{
		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}

		String jsonResponse = sendGet("/games/list", headers);
		
		return gson.fromJson(jsonResponse, GameResponse[].class);
	}
	
	/**
	 * Create a new game. This game will contain only the player initially.
	 * @param name the name of the game
	 * @param randomTiles whether the game will have random tile position
	 * @param randomNumbers whether the numbers of each hex will be random
	 * @param randomPorts whether the port positions will be random
	 * @return the game object for the game created by the user
	 * @throws ServerException
	 */
	@Override
	public GameResponse gamesCreate(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws ServerException
	{
		String jsonRequest = gson.toJson(new CreateGameRequest(name, randomTiles, randomNumbers, randomPorts));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, GameResponse.class);
	}
	
	/**
	 * Join a currently open game on the server
	 * @param color the color the player wants to be
	 * @param id the id of the player
	 * @throws ServerException
	 */
	@Override
	public void gamesJoin(CatanColor color, int id) throws ServerException
	{
		String jsonRequest = gson.toJson(new JoinGameRequest(id, color.toString()));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
	}
	
	/**
	 * Save the game's current state to the given location
	 * @param id the id of the game you wish to save
	 * @param name the location that the game will be saved at
	 * @throws ServerException
	 */
	@Override
	public void gamesSave(int id, String name) throws ServerException
	{
		String jsonRequest = gson.toJson(new SaveGameRequest(id, name));
		
	}
	
	/**
	 * Load the game's state from the given location
	 * @param id the location of the game to load on the server
	 * @throws ServerException
	 */
	@Override
	public void gamesLoad(String name) throws ServerException
	{
		String jsonRequest = gson.toJson(new LoadGameRequest(name));
		
	}
	
	/**
	 * Retrieve the model of the game board regardless of version number
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	public CatanModel gameModel() throws ServerException
	{
		return gameModel(-1);
	}
	
	/**
	 * Retrieve the model of the current game board state.
	 * @param version the last version of the game model received
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel gameModel(int version) throws ServerException
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

		String jsonResponse = sendGet("/game/model", headers);
		
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Reset the game to the saved state or the initial setup
	 * @throws ServerException
	 */
	@Override
	public void gameReset() throws ServerException
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
	 * @throws ServerException
	 */
	@Override
	public void gameCommandsPost(String[] commands) throws ServerException
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
	 * @throws ServerException
	 */
	@Override
	public String[] gameCommandsGet() throws ServerException
	{
		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}

		String jsonResponse = sendGet("/game/commands", headers);
		
		return gson.fromJson(jsonResponse, String[].class);
	}
	
	/**
	 * Add an AI player to the game
	 * @param aiType the type of AI player to add to the game
	 * @throws ServerException
	 */
	@Override
	public void gameAddAI(String aiType) throws ServerException
	{
//		String jsonRequest = gson.toJson(new AddAIRequest(aiType));
		
	}
	
	/**
	 * Retrieve a list of the AI players in the game
	 * @throws ServerException
	 */
	@Override
	public void gameListAI() throws ServerException
	{
		
	}

	/**
	 * Send a chat to the current game chat list
	 * @param playerIndex the index of the player sending the chat message
	 * @param content the content of the message the player wants to send
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesSendChat(int playerIndex, String content) throws ServerException
	{
		String jsonRequest = gson.toJson(new SendChatRequest(playerIndex, content));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Roll a number on the dice and send it to the server
	 * @param playerIndex the index of the player rolling the dice
	 * @param number the number the player rolled (This is stupid)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesRollNumber(int playerIndex, int number) throws ServerException
	{
		String jsonRequest = gson.toJson(new RollNumberRequest(playerIndex, number));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Execute the robbing phase of a players turn if they rolled a 7
	 * @param playerIndex the player doing the robbing
	 * @param victimIndex the player being robbed from
	 * @param location the location to move the robber to
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesRobPlayer(int playerIndex, int victimIndex, HexLocation location) throws ServerException
	{
		String jsonRequest = gson.toJson(new RobPlayerRequest(playerIndex, victimIndex, location));
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}

	/**
	 * Finish the turn of a player
	 * @param playerIndex the index of the player ending their turn
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesFinishTurn(int playerIndex) throws ServerException
	{
		String jsonRequest = gson.toJson(new FinishTurnRequest(playerIndex));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Buy a dev card for the player
	 * @param playerIndex the player buying the dev card
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesBuyDevCard(int playerIndex) throws ServerException
	{
		String jsonRequest = gson.toJson(new BuyDevCardRequest(playerIndex));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Play a Year of Plenty dev card for the player
	 * @param playerIndex the player playing the dev card
	 * @param resource1 the resource the player wants
	 * @param resource2 the resource the player wants
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) throws ServerException
	{
		String jsonRequest = gson.toJson(new YearOfPlentyRequest(playerIndex, resource1.toString(), resource2.toString()));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Play a Road Building dev card to give the player 2 roads
	 * @param playerIndex the index of the player building the road
	 * @param spot1 the location for a road being built
	 * @param spot2 the location for the second road being built
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) throws ServerException
	{
		String jsonRequest = gson.toJson(new RoadBuildingRequest(playerIndex, spot1, spot2));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Play a Soldier dev card to steal resources from another player
	 * @param playerIndex the player playing the dev card
	 * @param victimIndex the victim of the dev card
	 * @param location the robber location
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesSoldier(int playerIndex, int victimIndex, HexLocation location) throws ServerException
	{
		String jsonRequest = gson.toJson(new SoldierRequest(playerIndex, victimIndex, location));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Play a Monopoly dev card to allow the player to steal resources
	 * @param playerIndex the player playing the Monopoly card
	 * @param resource the resource the player wishes to steal
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesMonopoly(int playerIndex, ResourceType resource) throws ServerException
	{
		String jsonRequest = gson.toJson(new MonopolyRequest(playerIndex, resource.toString()));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Play a Monument dev card for the specified player
	 * @param playerIndex the player playing the dev card
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesMonument(int playerIndex) throws ServerException
	{
		String jsonRequest = gson.toJson(new MonumentRequest(playerIndex));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Build a road for the player in the given location
	 * @param playerIndex the player building the road
	 * @param location the location the player is building the road
	 * @param free whether this road is free or not (This is stupid)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesBuildRoad(int playerIndex, EdgeLocation location, boolean free) throws ServerException
	{
		String jsonRequest = gson.toJson(new BuildRoadRequest(playerIndex, location, free));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Build a settlement for the player at the given location
	 * @param playerIndex the index of the player building the settlement
	 * @param location the location of the settlement
	 * @param free whether the settlement is free or not (Why?)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesBuildSettlement(int playerIndex, VertexLocation location, boolean free) throws ServerException
	{
		String jsonRequest = gson.toJson(new BuildSettlementRequest(playerIndex, location, free));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Build a city at the given location
	 * @param playerIndex the player building the city
	 * @param location the location of the city bing built
	 * @param free whether the city will be free (I still don't get this...)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesBuildCity(int playerIndex, VertexLocation location, boolean free) throws ServerException
	{
		String jsonRequest = gson.toJson(new BuildCityRequest(playerIndex, location, free));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Offer a trade with another player
	 * @param offer the offer of trade
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesOfferTrade(TradeOffer offer) throws ServerException
	{
		String jsonRequest = gson.toJson(offer);

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Accept a trade proposed to you
	 * @param playerIndex the index of the player accepting the trade
	 * @param willAccept whether the player accepts or rejects the offer
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesAcceptTrade(int playerIndex, boolean willAccept) throws ServerException
	{
		String jsonRequest = gson.toJson(new AcceptTradeRequest(playerIndex, willAccept));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Process a port trade for the given player with the given ratio and resource types
	 * @param playerIndex the player doing the maritime trade
	 * @param ratio the ratio to trade the resources at
	 * @param inputResource the input resource type
	 * @param outputResource the output resource type
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesMaritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource) throws ServerException
	{
		String jsonRequest = gson.toJson(new MaritimeTradeRequest(playerIndex, ratio, inputResource.toString(), outputResource.toString()));

		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}

	/**
	 * Discard cards from the player's hand
	 * @param playerIndex the player discarding the card
	 * @param cards the cards the player is discarding
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesDiscardCards(int playerIndex, ResourceList cards) throws ServerException
	{
		String jsonRequest = gson.toJson(new DiscardCardsRequest(playerIndex, cards));

		Map<String, String> headers = new HashMap<String, String>();
		String cookie = getCookie();
		if (null != cookie)
		{
			headers.put("Cookie", cookie);
		}
		
		String jsonResponse = "";
		return gson.fromJson(jsonResponse, CatanModel.class);
	}
	
	/**
	 * Change the logging level for the server
	 * @param logLevel the desired logging level for the server
	 * @throws ServerException
	 */
	@Override
	public void utilChangeLogLevel(String logLevel) throws ServerException
	{
		String jsonRequest = gson.toJson(new ChangeLogLevelRequest(logLevel));
		
	}

}
