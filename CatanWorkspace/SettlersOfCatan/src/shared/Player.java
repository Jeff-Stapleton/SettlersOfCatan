package shared;

import java.util.Observable;

import shared.definitions.CatanColor;

public class Player extends Observable {

	private int playerID = 0;
	private int playerIndex = 0;
	private String name = "";
	private CatanColor color = CatanColor.WHITE;
	private int cities = 4;
	private int settlements = 5;
	private int roads = 15;
	private int monuments = 0;
	private int soldiers = 0;
	private int victoryPoints = 0;
	private DevCardList newDevCards = new DevCardList();
	private DevCardList oldDevCards = new DevCardList();
	private ResourceList resources = new ResourceList();
	private boolean playedDevCard = false;
	private boolean discarded = false;

	private transient boolean isRoadBuilding = false;
	private transient boolean isPlayingSoldier = false;
	
	/**
	 * Buys settlement 
	 */
	public void buySettlement(ResourceList bank){
		settlements--;
		victoryPoints++;
		// send resources to the bank
		ResourceList.moveResources(resources, bank, new ResourceList(1,1,1,1,0));
	}
	
	/**
	 * Buys city
	 */
	public void buyCity(ResourceList bank){
		cities--;
		victoryPoints++;
		// send resources to the bank
		ResourceList.moveResources(resources, bank, new ResourceList(0,0,2,0,3));	
	}
	
	/**
	 * Buys road
	 */
	public void buyRoad(ResourceList bank){
		roads--;
		// send resources to the bank
		ResourceList.moveResources(resources, bank, new ResourceList(1,1,0,0,0));//player 4 is the bank		
	}
	
	/**
	 * Moves the resources needed to buy the card from the players resources to the bank and then chooses a random devCard remaining in the deck and moves it from the deck to the players hand
	 * 
	 * @param bankResources the bank resources
	 * @param bankDevCards the deck of remaining dev cards available to be bought
	 */
	public void buyDevCard(ResourceList bankResources, DevCardList bankDevCards){
		// send resources to the bank
		ResourceList.moveResources(resources, bankResources, new ResourceList(0,0,1,1,1));		
		DevCardList.moveCard(newDevCards, bankDevCards, bankDevCards.getRandomCard());		
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
	public CatanColor getColor() {
		return color;
	}

	/**
	 * Sets player color
	 * @param color
	 */
	public void setColor(CatanColor color) {
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
	
	public boolean getIsRoadBuilding(){
		return isRoadBuilding;
	}
	
	public void setIsRoadBuilding(boolean isRoadBuilding){
//		System.out.println("Player.setIsRoadBuilding = " + isRoadBuilding);
		this.isRoadBuilding = isRoadBuilding;
//		System.out.println("Player.getIsRoadBuilding = " + getIsRoadBuilding());
	}
	
	public boolean getIsPlayingSoldier(){
		return isPlayingSoldier;
	}
	
	public void setIsPlayingSoldier(boolean isPlayingSoldier){
		this.isPlayingSoldier = isPlayingSoldier;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("resources : ").append(resources.toString()).append(",\n");
		string.append("oldDevCards : ").append(oldDevCards.toString()).append(",\n");
		string.append("newDevCards : ").append(newDevCards.toString()).append(",\n");
		string.append("roads : ").append(roads).append(",\n");
		string.append("cities : ").append(cities).append(",\n");
		string.append("settlements : ").append(settlements).append(",\n");
		string.append("soldiers : ").append(soldiers).append(",\n");
		string.append("victoryPoints : ").append(victoryPoints).append(",\n");
		string.append("monuments : ").append(monuments).append(",\n");
		string.append("playedDevCard : ").append(playedDevCard).append(",\n");
		string.append("discarded : ").append(discarded).append(",\n");
		string.append("playerID : ").append(playerID).append(",\n");
		string.append("playerIndex : ").append(playerIndex).append(",\n");
		string.append("color : ").append(color).append(",\n");
		string.append("name : ").append(name).append("\n");
		
		string.append("}");
		
		return string.toString();
	}

	public boolean updateFrom(Player rhs)
	{
		boolean updated = false;
		
		if (cities != rhs.cities)
		{
			cities = rhs.cities;
			updated = true;
		}
		if (color != rhs.getColor())
		{
			color = rhs.getColor();
			updated = true;
		}
		if (discarded != rhs.discarded)
		{
			discarded = rhs.discarded;
			updated = true;
		}
		if (monuments != rhs.monuments)
		{
			monuments = rhs.monuments;
			updated = true;
		}
		if (!name.equals(rhs.name))
		{
			name = rhs.name;
			updated = true;
		}
		if (playerIndex != rhs.playerIndex)
		{
			playerIndex = rhs.playerIndex;
			updated = true;
		}
		if (playedDevCard != rhs.playedDevCard)
		{
			playedDevCard = rhs.playedDevCard;
			updated = true;
		}
		if (playerID != rhs.playerID)
		{
			playerID = rhs.playerID;
			updated = true;
		}
		if (roads != rhs.roads)
		{
			roads = rhs.roads;
			updated = true;
		}
		if (settlements != rhs.settlements)
		{
			settlements = rhs.settlements;
			updated = true;
		}
		if (soldiers != rhs.soldiers)
		{
			soldiers = rhs.soldiers;
			updated = true;
		}
		if (victoryPoints != rhs.victoryPoints)
		{
			victoryPoints = rhs.victoryPoints;
			updated = true;
		}
		
		updated = updated | newDevCards.updateFrom(rhs.newDevCards);
		updated = updated | oldDevCards.updateFrom(rhs.oldDevCards);
		updated = updated | resources.updateFrom(rhs.resources);
		
		if (updated)
		{
			setChanged();
			notifyObservers();
		}
		
		return updated;
	}
	
	
}
