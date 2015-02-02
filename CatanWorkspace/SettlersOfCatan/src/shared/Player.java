package shared;

public class Player {

	private int cities;
	private String color;
	private boolean discarded;
	private int monuments;
	private String name;
	private DevCardList newDevCards;
	private DevCardList oldDevCards;
	private int playerIndex;
	private boolean playedDevCard;
	private int playerID;
	private ResourceList resources;
	private int roads;
	private int settlements;
	private int soldiers;
	private int victoryPoints;
	
	private transient Player bank;
	private transient CanCan cancan;
	

	
	/**
	 * Buys settlement 
	 */
	public boolean buySettlement(){
		if (cancan.canBuySettlement(this)){
			settlements--;
			victoryPoints++;
			// send resources to the bank
			resources.moveResources(bank, new ResourceList(1,1,1,1,0));//player 4 is the bank
			return true;
		}
		else{
			// error
			return false;
		}
	}
	
	/**
	 * Buys city
	 */
	public boolean buyCity(){
		if (cancan.canBuyCity(this)){
			cities--;
			victoryPoints++;
			// send resources to the bank
			resources.moveResources(bank, new ResourceList(0,0,2,0,3));//player 4 is the bank
			return true;
		}
		else{
			// error
			return false;
		}		
	}
	
	/**
	 * Buys road
	 */
	public boolean buyRoad(){
		if (cancan.canBuyRoad(this)){
			roads--;
			// send resources to the bank
			resources.moveResources(bank, new ResourceList(1,1,0,0,0));//player 4 is the bank
			return true;
		}
		else{
			// error
			return false;
		}			
	}
	
	/**
	 * Buys dev card
	 */
	public boolean buyDevCard(){
		if (cancan.canBuyDevCard(this)){
			// send resources to the bank
			resources.moveResources(bank, new ResourceList(0,0,1,1,1));//player 4 is the bank
//			newDevCards.addCard(bank.getOldDevCards().getRandomCard());
			// remove a random dev card from bank.oldDevCards and add it to newDevCards
			return true;
		}
		else{
			// error
			return false;
		}			
	}
	
	/**
	 * Checks to see if the players needs to discard a card
	 * 
	 * @return discarded boolean
	 */
	public boolean needsToDiscard(){
		if (resources.totalCount() > 7){
			discard();
		}
		return false;
	}
	
	/**
	 * Removes a card from the players hand
	 * 
	 * @return void
	 */
	public void discard(){
		// send cards from hand to the bank
	}
	
	/**
	 * Gets number of cities player can still build
	 * @return
	 */
	public int getCities() {
		return cities;
	}

	/**
	 * Sets number of cities player can still build
	 * @param cities
	 */
	public void setCities(int cities) {
		this.cities = cities;
	}

	/**
	 * Gets player color
	 * @return
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Sets player color
	 * @param color
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Checks to see if player has discarded already
	 * @return
	 */
	public boolean hasDiscarded() {
		return discarded;
	}

	/**
	 * Sets whether or not player has discarded this turn
	 * @param discarded
	 */
	public void setDiscarded(boolean discarded) {
		this.discarded = discarded;
	}

	/**
	 * Gets number of monuments owned by the player
	 * @return
	 */
	public int getMonuments() {
		return monuments;
	}

	/**
	 * Sets number of monuments owned by the player
	 * @param monuments
	 */
	public void setMonuments(int monuments) {
		this.monuments = monuments;
	}

	/**
	 * Gets player name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets player name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets newDevCardList for the player
	 * This is a list of cards that cannot be played this turn
	 * @return
	 */
	public DevCardList getNewDevCards() {
		return newDevCards;
	}

	/**
	 * Sets newDevCardList for the player
	 * This is a list of cards that cannot be played this turn
	 * @param newDevCards
	 */
	public void setNewDevCards(DevCardList newDevCards) {
		this.newDevCards = newDevCards;
	}

	/**
	 * Gets oldDevCardList for the player
	 * This is a list of cards that the player can play this turn
	 * @return
	 */
	public DevCardList getOldDevCards() {
		return oldDevCards;
	}

	/**
	 * Sets oldDevCardList for the player
	 * This is a list of cards that the player can play this turn
	 * @param oldDevCards
	 */
	public void setOldDevCards(DevCardList oldDevCards) {
		this.oldDevCards = oldDevCards;
	}

	/**
	 * Gets player index
	 * @return
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * Sets player index
	 * @param playerIndex
	 */
	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	/**
	 * Checks to see if player has played a dev card this turn
	 * @return
	 */
	public boolean hasPlayedDevCard() {
		return playedDevCard;
	}

	/**
	 * Sets whether or not the player has played a dev card this turn
	 * @param playedDevCard
	 */
	public void setPlayedDevCard(boolean playedDevCard) {
		this.playedDevCard = playedDevCard;
	}

	/**
	 * Set player ID
	 * @return
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * Get player ID
	 * @param playerID
	 */
	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	/**
	 * Get player ResourceList
	 * @return
	 */
	public ResourceList getResources() {
		return resources;
	}

	/**
	 * Set player ResourceList
	 * @param resources
	 */
	public void setResources(ResourceList resources) {
		this.resources = resources;
	}

	/**
	 * Get number of roads the player can still build
	 * @return
	 */
	public int getRoads() {
		return roads;
	}

	/**
	 * Set number of roads the player has available to build
	 * @param roads
	 */
	public void setRoads(int roads) {
		this.roads = roads;
	}

	/**
	 * Get number of settlements the player can still build
	 * @return
	 */
	public int getSettlements() {
		return settlements;
	}

	/**
	 * Set number of settlements the play can still build
	 * @param settlements
	 */
	public void setSettlements(int settlements) {
		this.settlements = settlements;
	}

	/**
	 * Get number of soldiers the player has played
	 * @return
	 */
	public int getSoldiers() {
		return soldiers;
	}

	/**
	 * Set the number of soldiers the player has played
	 * @param soldiers
	 */
	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}

	/**
	 * Get victory points
	 * @return
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	/**
	 * Set victory points
	 * @param victoryPoints
	 */
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	
}
