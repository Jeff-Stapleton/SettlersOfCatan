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
	 * Port trade.
	 *
	 * @param player the player
	 */
	@SuppressWarnings("unused")
	private void portTrade(Player player, Port port, ResourceList trade)
	{
		// is the players sending over positive/negative number in his trade offer?
		bank.setBrick(bank.getBrick() + trade.getBrick());
		bank.setWood(bank.getWood() + trade.getWood());
		bank.setGrain(bank.getGrain() + trade.getGrain());
		bank.setWool(bank.getWool() + trade.getWool());
		bank.setOre(bank.getOre() + trade.getOre());
		
		// give the player the trade
		PortType type = port.getType();
		ResourceList tradeReturn = new ResourceList();
		if (type.toString() == "WOOD")
		{
			tradeReturn.setWood(port.getRatio());
			bank.moveResources(player, tradeReturn);
		}
		else if (type.toString() == "BRICK")
		{
			tradeReturn.setBrick(port.getRatio());
			bank.moveResources(player, tradeReturn);
		}
		else if (type.toString() == "SHEEP")
		{
			tradeReturn.setWool(port.getRatio());
			bank.moveResources(player, tradeReturn);
		}
		else if (type.toString() == "WHEAT")
		{
			tradeReturn.setGrain(port.getRatio());
			bank.moveResources(player, tradeReturn);
		}
		else
		{
			tradeReturn.setOre(port.getRatio());
			bank.moveResources(player, tradeReturn);
		}
	}
	
	/**
	 * Buy dev card.
	 *
	 * @param player the player
	 */
	@SuppressWarnings("unused")
	private void buyDevCard(Player player, ResourceList cost)
	{
		bank.setBrick(bank.getBrick() + cost.getBrick());
		bank.setWood(bank.getWood() + cost.getWood());
		bank.setGrain(bank.getGrain() + cost.getGrain());
		bank.setWool(bank.getWool() + cost.getWool());
		bank.setOre(bank.getOre() + cost.getOre());
		
		DevCardList devCard = new DevCardList();

		Random rand = new Random();
		int card = rand.nextInt(5);
		
		if (card == 0)
		{
			devCard.setMonopoly(1);
		}
		else if (card == 1)
		{
			devCard.setMonument(1);
		}
		else if (card == 2)
		{
			devCard.setRoadBuilding(1);
		}
		else if (card == 3)
		{
			devCard.setSoldier(1);
		}
		else
		{
			devCard.setYearOfPlenty(1);
		}
		player.setNewDevCards(devCard);
	}
	
	/**
	 * Play dev card.
	 *
	 * @param player the player
	 */
	@SuppressWarnings("unused")
	private void playDevCard(Player player, DevCardList card)
	{	
		if (card.getMonopoly() > 0)
		{
			player.getOldDevCards().setMonopoly(-1);
		}
		else if (card.getMonument() > 0)
		{
			player.getOldDevCards().setMonument(-1);
		}
		else if (card.getRoadBuilding() > 0)
		{
			player.getOldDevCards().setRoadBuilding(-1);
		}
		else if (card.getSoldier() > 0)
		{
			player.getOldDevCards().setSoldier(-1);
		}
		else
		{
			player.getOldDevCards().setYearOfPlenty(-1);
		}
	}
	
	public void trade()
	{
		tradeOffer.getOffer().moveResources(Players[tradeOffer.getReceiver()], tradeOffer.getOffer());
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
