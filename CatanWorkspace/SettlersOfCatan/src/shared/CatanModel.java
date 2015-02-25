package shared;

import java.util.Random;

import shared.definitions.DevCardType;
import shared.definitions.PortType;

/**
 * The main client side controller of the game.
 *
 * @author JJ
 */
public class CatanModel {
	
	/** The deck. */
	private DevCardList deck;
	
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
	private Player[] players;
	
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
	public CatanModel() 
	{
		deck = new DevCardList();
		bank = new ResourceList();
		chat = new MessageList();
		map = new Map();
		turnTracker = new TurnTracker();
	}
	
	/**
	 * Gets the deck.
	 * 
	 * @return the deck
	 */
	public DevCardList getDeck() {
		return deck;
	}
	
	/**
	 * Sets the deck.
	 * 
	 * @param deck the new deck
	 */
	public void setDeck(DevCardList deck) {
		this.deck = deck;
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
		return players;
	}

	/**
	 * Sets the players.
	 *
	 * @param players the new players
	 */
	public void setPlayers(Player[] players) {
		this.players = players;
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
	
	/**
	 * Gets the version number.
	 *
	 * @param version the current version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Sets the version number.
	 *
	 * @param version the new version
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	
	/**
	 * Gets the winner of the game.
	 *
	 * @param winner of the game
	 */
	public int getWinner() {
		return winner;
	}

	/**
	 * Sets the winner of the game.
	 *
	 * @param winner of the game
	 */
	public void setWinner(int winner) {
		this.winner = winner;
	}
	
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("deck : ").append(deck.toString()).append(",\n");
		string.append("map : ").append(map.toString()).append(",\n");
		
		string.append("players : [\n");
		for (Player p : players) {
			string.append(p.toString()).append(",\n");
		}
		string.append("],\n");
		
		string.append("log : ").append(log.toString()).append(",\n");
		string.append("chat : ").append(chat.toString()).append(",\n");
		string.append("bank : ").append(bank.toString()).append(",\n");
		string.append("turnTracker : ").append(turnTracker.toString()).append(",\n");
		string.append("winner : ").append(winner).append(",\n");
		string.append("version : ").append(version).append("\n");
		
		string.append("}");
		
		return string.toString();
	}
	
}
