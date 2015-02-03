package comm.client;

import shared.CatanModel;
import shared.ResourceList;
import shared.TradeOffer;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;
import comm.shared.ServerException;
import comm.shared.serialization.GameResponse;

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
	/**
	 * Log an existing user into the server
	 * This function will also set the cookie for the system
	 * @param user the username of the player
	 * @param password the password for the player
	 * @throws ServerException
	 */
	@Override
	public void userLogin(String user, String password) throws ServerException
	{
		
	}
	
	/**
	 * Register a new user with the server
	 * This function sets the cookie for the system
	 * @param user the player's unique username
	 * @param password the players password
	 * @throws ServerException
	 */
	@Override
	public void userRegister(String user, String password) throws ServerException
	{
		
	}
	
	/**
	 * List the current games on the server that the player can join
	 * @return an array of the games on the server
	 * @throws ServerException
	 */
	@Override
	public GameResponse[] gamesList() throws ServerException
	{
		
		return null; //TODO: Replace with real data
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

		return null; //TODO: Replace with real data
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
		
	}
	
	/**
	 * Retrieve the model of the current game board state.
	 * @throws ServerException
	 */
	@Override
	public CatanModel gameModel() throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Reset the game to the saved state or the initial setup
	 * @throws ServerException
	 */
	@Override
	public void gameReset() throws ServerException
	{
		
	}
	
	/**
	 * Send a command to the server for debugging purposes
	 * @param command the command to send to the server
	 * @throws ServerException
	 */
	@Override
	public void gamesCommandsSend(String command) throws ServerException
	{
		
	}
	
	/**
	 * Fetch the list of commands that the server has executed for debug purposes
	 * @return the commands executed on the server
	 * @throws ServerException
	 */
	@Override
	public String[] gamesCommandsFetch() throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Send a chat to the current game chat list
	 * @param playerIndex the index of the player sending the chat message
	 * @param content the content of the message the player wants to send
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesSendChat(int playerIndex, String content) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Roll a number on the dice and send it to the server
	 * @param playerIndex the index of the player rolling the dice
	 * @param number the number the player rolled (This is stupid)
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesRollNumber(int playerIndex, int number) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Finish the turn of a player
	 * @param playerIndex the index of the player ending their turn
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesFinishTurn(int playerIndex) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Buy a dev card for the player
	 * @param playerIndex the player buying the dev card
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesBuyDevCard(int playerIndex) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Play a Year of Plenty dev card for the player
	 * @param playerIndex the player playing the dev card
	 * @param resource1 the resource the player wants
	 * @param resource2 the resource the player wants
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesYearOfPlenty(int playerIndex, ResourceType resource1, ResourceType resource2) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Play a Road Building dev card to give the player 2 roads
	 * @param playerIndex the index of the player building the road
	 * @param spot1 the location for a road being built
	 * @param spot2 the location for the second road being built
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesRoadBuilding(int playerIndex, EdgeLocation spot1, EdgeLocation spot2) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Play a Soldier dev card to steal resources from another player
	 * @param playerIndex the player playing the dev card
	 * @param victimIndex the victim of the dev card
	 * @param location the robber location
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesSoldier(int playerIndex, int victimIndex, HexLocation location) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Play a Monopoly dev card to allow the player to steal resources
	 * @param playerIndex the player playing the Monopoly card
	 * @param resource the resource the player wishes to steal
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesMonopoly(int playerIndex, ResourceType resource) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Play a Monument dev card for the specified player
	 * @param playerIndex the player playing the dev card
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesMonument(int playerIndex) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Build a road for the player in the given location
	 * @param playerIndex the player building the road
	 * @param location the location the player is building the road
	 * @param free whether this road is free or not (This is stupid)
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesBuildRoad(int playerIndex, EdgeLocation location, boolean free) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Build a settlement for the player at the given location
	 * @param playerIndex the index of the player building the settlement
	 * @param location the location of the settlement
	 * @param free whether the settlement is free or not (Why?)
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesBuildSettlement(int playerIndex, VertexLocation location, boolean free) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Build a city at the given location
	 * @param playerIndex the player building the city
	 * @param location the location of the city bing built
	 * @param free whether the city will be free (I still don't get this...)
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesBuildCity(int playerIndex, VertexLocation location, boolean free) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Offer a trade with another player
	 * @param offer the offer of trade
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesOfferTrade(TradeOffer offer) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Accept a trade proposed to you
	 * @param playerIndex the index of the player accepting the trade
	 * @param willAccept whether the player accepts or rejects the offer
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesAcceptTrade(int playerIndex, boolean willAccept) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
	/**
	 * Discard cards from the player's hand
	 * @param playerIndex the player discarding the card
	 * @param cards the cards the player is discarding
	 * @throws ServerException
	 */
	@Override
	public CatanModel movesDiscardCards(int playerIndex, ResourceList cards) throws ServerException
	{

		return null; //TODO: Replace with real data
	}
	
}
