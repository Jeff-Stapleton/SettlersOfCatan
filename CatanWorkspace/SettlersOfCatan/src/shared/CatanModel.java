package shared;

import org.apache.log4j.Logger;

/**
 * The main client side controller of the game.
 *
 * @author JJ
 */
public class CatanModel extends InvisObservable
{
	private static final Logger logger = Logger.getLogger(CatanModel.class);
	
	/** The deck. */
	private DevCardList deck = new DevCardList();
	
	/** The map. */
	private Map map = new Map();
	
	/** The Players. */
	private Player[] players = new Player[4];
	{
		for (int i = 0; i < players.length; i++)
			players[i] = new Player();
	}
	
	/** The log. */
	private MessageList log = new MessageList();
	
	/** The chat. */
	private MessageList chat = new MessageList();
	
	/** The bank. */
	private ResourceList bank = new ResourceList();
	
	/** The trade offer. */
	private TradeOffer tradeOffer = null;
	
	/** The turn tracker. */
	private TurnTracker turnTracker = new TurnTracker();

	/** The winner. */
	private int winner = -1;
	
	/** The version. */
	private int version = 0;
	
	/**
	 * Instantiates a new catan model.
	 */
	public CatanModel() {
		deck.setMonopoly(2);
		deck.setMonument(5);
		deck.setRoadBuilding(2);
		deck.setSoldier(14);
		deck.setYearOfPlenty(2);
		
		bank.setBrick(19);
		bank.setOre(19);
		bank.setSheep(19);
		bank.setWheat(19);
		bank.setWood(19);
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
		string.append("tradeOffer : ").append(tradeOffer == null? "null" : tradeOffer.toString()).append(",\n");
		
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

	/**
	 * Update the model from the model passed in and notify observers
	 * if anything has changed.
	 * @param model the model to update from
	 * @return true if the model changed, false otherwise
	 */
	public boolean updateFrom(CatanModel model)
	{
		logger.trace("Updating catan model");
		boolean updated = false;
		
		updated = updated | deck.updateFrom(model.getDeck());
		updated = updated | bank.updateFrom(model.getBank());
		updated = updated | chat.updateFrom(model.getChat());
		updated = updated | log.updateFrom(model.getLog());
		updated = updated | turnTracker.updateFrom(model.getTurnTracker());
		updated = updated | map.updateFrom(model.getMap());
		
		// Update the trade offer
		if (tradeOffer == null && model.getTradeOffer() != null)
		{
			tradeOffer = model.getTradeOffer();
			updated = true;
		}
		else if (tradeOffer != null)
		{
			if (!tradeOffer.equals(model.getTradeOffer()))
			{
				tradeOffer = model.getTradeOffer();
				updated = true;
			}
		}
		
		// Update the players
		assert players.length == model.getPlayers().length;
		for (int i = 0; i < players.length; i++)
		{
			updated = updated | players[i].updateFrom(model.getPlayers()[i]);
		}
		
		// Update the version
		if (version != model.getVersion())
		{
			version = model.getVersion();
			updated = true;
		}
		
		// Update the winner
		if (winner != model.getWinner())
		{
			winner = model.getWinner();
			updated = true;
		}
		
		if (updated)
		{
			logger.trace("Notifying observers");
			setChanged();
			notifyObservers();
		}
		
		return updated;
	}
	
	public void reset(){
		deck.setMonopoly(2);
		deck.setMonument(5);
		deck.setRoadBuilding(2);
		deck.setSoldier(14);
		deck.setYearOfPlenty(2);
		
		bank.setBrick(19);
		bank.setOre(19);
		bank.setSheep(19);
		bank.setWheat(19);
		bank.setWood(19);
		
		chat.reset();
		log.reset();
		map.reset();
		if (tradeOffer != null){
			tradeOffer.reset();
		}
		
		for (int i = 0; i < players.length; i++){
			players[i].reset();
		}
		turnTracker.reset();
		
//		version = -1;
		winner = -1;
	}
	
}
