package shared;

import shared.definitions.DevCardType;

/**
 * The main client side controller of the game.
 *
 * @author JJ
 */
public class CatanModel {
	
	/** The bank. */
	private ResourceList bank;
	
	/** The chat. */
	private MessageList chat;
	
	/** The log. */
	private MessageList log;
	
	/** The map. */
	private Map map;
	
	/** The trade offer. */
	private TradeOffer tradeOffer;
	
	/** The Players. */
	private Player[] Players;
	
	/** The turn tracker. */
	private TurnTracker turnTracker;
	
	/** The version. */
	@SuppressWarnings("unused")
	private int version = -1;
	
	/** The winner. */
	@SuppressWarnings("unused")
	private int winner = -1;
	
	/**
	 * Instantiates a new catan model.
	 */
	private CatanModel() 
	{
		bank = new ResourceList();
		chat = new MessageList();
		map = new Map();
		turnTracker = new TurnTracker();
	}
	
	/**
	 * Update from server.
	 */
	@SuppressWarnings("unused")
	private void updateFromServer()
	{
		//get update from poller
	}
	
	/**
	 * Checks if a player can trade.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	@SuppressWarnings("unused")
	private boolean canTrade(Player player)
	{
		return player.
	}
	
	/**
	 * Can port trade.
	 *
	 * @param port the port
	 * @return true, if successful
	 */
	@SuppressWarnings("unused")
	private boolean canPortTrade(Port port)
	{
		//port.List of players built there
		return false;
	}
	
	/**
	 * Port trade.
	 *
	 * @param player the player
	 */
	@SuppressWarnings("unused")
	private void portTrade(Player player)
	{
		//player.how to exchange resources?
	}
	
	/**
	 * Can buy dev card.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	@SuppressWarnings("unused")
	private boolean canBuyDevCard(Player player)
	{
		return player.canBuyDevCard();
	}
	
	/**
	 * Buy dev card.
	 *
	 * @param player the player
	 */
	@SuppressWarnings("unused")
	private void buyDevCard(Player player)
	{
		player.buyDevCard();
	}
	
	/**
	 * Can play dev card.
	 *
	 * @param player the player
	 * @param cardType the card type
	 * @return true, if successful
	 */
	@SuppressWarnings("unused")
	private boolean canPlayDevCard(Player player, DevCardType cardType)
	{
		
		return false;
	}
	
	/**
	 * Play dev card.
	 *
	 * @param player the player
	 */
	@SuppressWarnings("unused")
	private void playDevCard(Player player)
	{
		
	}

	/**
	 * Gets the bank.
	 *
	 * @return the bank
	 */
	public ResourceList getBank() {
		return bank;
	}

	/**
	 * Sets the bank.
	 *
	 * @param bank the new bank
	 */
	public void setBank(ResourceList bank) {
		this.bank = bank;
	}

	/**
	 * Gets the chat.
	 *
	 * @return the chat
	 */
	public MessageList getChat() {
		return chat;
	}

	/**
	 * Sets the chat.
	 *
	 * @param chat the new chat
	 */
	public void setChat(MessageList chat) {
		this.chat = chat;
	}

	/**
	 * Gets the log.
	 *
	 * @return the log
	 */
	public MessageList getLog() {
		return log;
	}

	/**
	 * Sets the log.
	 *
	 * @param log the new log
	 */
	public void setLog(MessageList log) {
		this.log = log;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Gets the trade offer.
	 *
	 * @return the trade offer
	 */
	public TradeOffer getTradeOffer() {
		return tradeOffer;
	}

	/**
	 * Sets the trade offer.
	 *
	 * @param tradeOffer the new trade offer
	 */
	public void setTradeOffer(TradeOffer tradeOffer) {
		this.tradeOffer = tradeOffer;
	}

	/**
	 * Gets the players.
	 *
	 * @return the players
	 */
	public Player[] getPlayers() {
		return Players;
	}

	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(Player[] players) {
		Players = players;
	}

	/**
	 * Gets the turn tracker.
	 *
	 * @return the turn tracker
	 */
	public TurnTracker getTurnTracker() {
		return turnTracker;
	}

	/**
	 * Sets the turn tracker.
	 *
	 * @param turnTracker the new turn tracker
	 */
	public void setTurnTracker(TurnTracker turnTracker) {
		this.turnTracker = turnTracker;
	}
}
