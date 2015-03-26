package client.comm;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import client.view.data.GameInfo;

import com.google.gson.Gson;

import shared.CatanModel;
import shared.ResourceList;
import shared.TradeOffer;
import shared.comm.cookie.PlayerCookie;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

/**
 * Abstract Server Proxy that will contain common functionality needed by all
 * the Server Proxies
 * @author Cory Beutler
 */
public abstract class AbstractServerProxy implements IServerProxy
{
	private static final Logger log = Logger.getLogger(AbstractServerProxy.class);
	
	protected Gson gson = new Gson();
	
	private String _playerCookie = null;
	private String _gameCookie = null;

	/**
	 * Set the cookie for the player logging in to the server
	 * @param cookie
	 */
	protected void setPlayerCookie(String cookie)
	{
		log.trace("Set the player cookie");
		StringTokenizer st = new StringTokenizer(cookie, ";");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (token.contains("catan.user="))
			{
				_playerCookie = token;
				return;
			}
		}
	}
	
	/**
	 * Get the cookie for the player's login
	 * @return the string cookie to be used
	 */
	protected String getPlayerCookie()
	{
		return _playerCookie;
	}
	
	/**
	 * Set the cookie for the game
	 * @param cookie
	 */
	protected void setGameCookie(String cookie)
	{
		StringTokenizer st = new StringTokenizer(cookie, ";");
		while (st.hasMoreTokens()) {
			String token = st.nextToken().trim();
			if (token.contains("catan.game="))
			{
				log.trace("Set the game cookie to \"" + token + "\"");
				_gameCookie = token;
				return;
			}
		}
	}
	
	/**
	 * Get the cookie for the game
	 * @return the string cookie to be used
	 */
	protected String getGameCookie()
	{
		log.trace("Getting game cookie: " + _gameCookie);
		return _gameCookie;
	}
	
	protected String getCookie()
	{
		StringBuilder cookie = new StringBuilder();
		if (getPlayerCookie() != null)
		{
			cookie.append(getPlayerCookie());
			
			if (getGameCookie() != null)
			{
				cookie.append("; ").append(getGameCookie());
			}
			log.trace("getCookie(): \"" + cookie.toString() + "\"");
			return cookie.toString();
		}
		else
		{
			log.trace("getCookie(): null");
			return null;
		}
		
	}
	
	/**
	 * Log an existing user into the server
	 * This function will also set the cookie for the system
	 * @param user the username of the player
	 * @param password the password for the player
	 * @throws IOException 
	 */
	@Override
	public abstract PlayerCookie userLogin(String user, String password) throws IOException;
	
	/**
	 * Register a new user with the server
	 * This function sets the cookie for the system
	 * @param user the player's unique username
	 * @param password the players password
	 * @throws IOException
	 */
	@Override
	public abstract PlayerCookie userRegister(String user, String password) throws IOException;
	
	/**
	 * List the current games on the server that the player can join
	 * @return an array of the games on the server
	 * @throws IOException 
	 */
	@Override
	public abstract GameInfo[] gamesList() throws IOException;
	
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
	public abstract GameInfo gamesCreate(String name, boolean randomTiles, boolean randomNumbers, boolean randomPorts) throws IOException;
	
	/**
	 * Join a currently open game on the server
	 * @param color the color the player wants to be
	 * @param id the id of the player
	 * @throws IOException
	 */
	@Override
	public abstract void gamesJoin(CatanColor color, int id) throws IOException;
	
	/**
	 * Save the game's current state to the given location
	 * @param id the id of the game you wish to save
	 * @param name the location that the game will be saved at
	 * @throws IOException
	 */
	@Override
	public abstract void gamesSave(int id, String name) throws IOException;
	
	/**
	 * Load the game's state from the given location
	 * @param id the location of the game to load on the server
	 * @throws IOException
	 */
	@Override
	public abstract void gamesLoad(String name) throws IOException;
	
	/**
	 * Retrieve the model of the current game board state.
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel gameModel(int version) throws IOException;
	
	/**
	 * Reset the game to the saved state or the initial setup
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel gameReset() throws IOException;
	
	/**
	 * Send a command to the server for debugging purposes
	 * @param command the command to send to the server
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel gameCommandsPost(String[] commands) throws IOException;
	
	/**
	 * Fetch the list of commands that the server has executed for debug purposes
	 * @return the commands executed on the server
	 * @throws IOException
	 */
	@Override
	public abstract String[] gameCommandsGet() throws IOException;
	
	/**
	 * Add an AI player to the game
	 * @param aiType the type of AI player to add to the game
	 * @throws IOException
	 */
	@Override
	public abstract void gameAddAI(String aiType) throws IOException;
	
	/**
	 * Retrieve a list of the AI players in the game
	 * @throws IOException
	 */
	@Override
	public abstract String[] gameListAI() throws IOException;
	
	/**
	 * Send a chat to the current game chat list
	 * @param playerIndex the index of the player sending the chat message
	 * @param content the content of the message the player wants to send
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesSendChat(int playerIndex, String content) throws IOException;
	
	/**
	 * Roll a number on the dice and send it to the server
	 * @param playerIndex the index of the player rolling the dice
	 * @param number the number the player rolled (This is stupid)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesRollNumber(int playerIndex, int number) throws IOException;
	
	/**
	 * Execute the robbing phase of a players turn if they rolled a 7
	 * @param playerIndex the player doing the robbing
	 * @param victimIndex the player being robbed from
	 * @param location the location to move the robber to
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesRobPlayer(int playerIndex, int victimIndex, HexLocation location) throws IOException;
	
	/**
	 * Finish the turn of a player
	 * @param playerIndex the index of the player ending their turn
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesFinishTurn(int playerIndex) throws IOException;
	
	/**
	 * Buy a dev card for the player
	 * @param playerIndex the player buying the dev card
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesBuyDevCard(int playerIndex) throws IOException;
	
	/**
	 * Play a Year of Plenty dev card for the player
	 * @param playerIndex the player playing the dev card
	 * @param resource1 the resource the player wants
	 * @param resource2 the resource the player wants
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) throws IOException;
	
	/**
	 * Play a Road Building dev card to give the player 2 roads
	 * @param playerIndex the index of the player building the road
	 * @param spot1 the location for a road being built
	 * @param spot2 the location for the second road being built
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) throws IOException;
	
	/**
	 * Play a Soldier dev card to steal resources from another player
	 * @param playerIndex the player playing the dev card
	 * @param victimIndex the victim of the dev card
	 * @param location the robber location
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesSoldier(int playerIndex, int victimIndex, HexLocation location) throws IOException;
	
	/**
	 * Play a Monopoly dev card to allow the player to steal resources
	 * @param playerIndex the player playing the Monopoly card
	 * @param resource the resource the player wishes to steal
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesMonopoly(int playerIndex, ResourceType resource) throws IOException;
	
	/**
	 * Play a Monument dev card for the specified player
	 * @param playerIndex the player playing the dev card
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesMonument(int playerIndex) throws IOException;
	
	/**
	 * Build a road for the player in the given location
	 * @param playerIndex the player building the road
	 * @param location the location the player is building the road
	 * @param free whether this road is free or not (This is stupid)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesBuildRoad(int playerIndex, EdgeLocation location, boolean free) throws IOException;
	
	/**
	 * Build a settlement for the player at the given location
	 * @param playerIndex the index of the player building the settlement
	 * @param location the location of the settlement
	 * @param free whether the settlement is free or not (Why?)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesBuildSettlement(int playerIndex, VertexLocation location, boolean free) throws IOException;
	
	/**
	 * Build a city at the given location
	 * @param playerIndex the player building the city
	 * @param location the location of the city bing built
	 * @param free whether the city will be free (I still don't get this...)
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesBuildCity(int playerIndex, VertexLocation location, boolean free) throws IOException;
	
	/**
	 * Offer a trade with another player
	 * @param offer the offer of trade
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesOfferTrade(TradeOffer offer) throws IOException;
	
	/**
	 * Accept a trade proposed to you
	 * @param playerIndex the index of the player accepting the trade
	 * @param willAccept whether the player accepts or rejects the offer
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesAcceptTrade(int playerIndex, boolean willAccept) throws IOException;
	
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
	public abstract CatanModel movesMaritimeTrade(int playerIndex, int ratio, ResourceType inputResource, ResourceType outputResource) throws IOException;
	
	/**
	 * Discard cards from the player's hand
	 * @param playerIndex the player discarding the card
	 * @param cards the cards the player is discarding
	 * @return the CatanModel representing the new game state after this move was processed
	 * @throws IOException
	 */
	@Override
	public abstract CatanModel movesDiscardCards(int playerIndex, ResourceList cards) throws IOException;
	
	/**
	 * Change the logging level for the server
	 * @param logLevel the desired logging level for the server
	 * @throws IOException
	 */
	@Override
	public abstract void utilChangeLogLevel(String logLevel) throws IOException;
	
}
