package shared;

import java.util.Random;

public class DevCardList {

	private int monopoly;
	private int monument;
	private int roadBuilding;
	private int soldier;
	private int yearOfPlenty;
	
	
	/**
	 * @return
	 */
	public static DevCardList getRandomCard(){
		Random rand = new Random();
		int card = rand.nextInt(5);
		DevCardList devCard = new DevCardList();
		
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
		return devCard;
	}


	public static void moveCard(DevCardList newDevCards, DevCardList bankDevCards, DevCardList card) {
		
	}
	
	public int getTotal(){
		int total = 0;
		total += getMonopoly();
		total += getMonument();
		total += getRoadBuilding();
		total += getSoldier();
		total += getYearOfPlenty();
		return total;
	}
	
	/**
	 * Get the number of monopoly cards
	 * @return
	 */
	public int getMonopoly() {
		return monopoly;
	}
	/**
	 * Set the number of monopoly cards
	 * @param monopoly
	 */
	public void setMonopoly(int monopoly) {
		this.monopoly = monopoly;
	}
	/**
	 * Get the number of monument cards
	 * @return
	 */
	public int getMonument() {
		return monument;
	}
	/**
	 * Set the number of monument cards
	 * @param monument
	 */
	public void setMonument(int monument) {
		this.monument = monument;
	}
	/**
	 * Get the number of RoadBuilding cards
	 * @return
	 */
	public int getRoadBuilding() {
		return roadBuilding;
	}
	/**
	 * Set the number of RoadBuilding cards
	 * @param roadBuilding
	 */
	public void setRoadBuilding(int roadBuilding) {
		this.roadBuilding = roadBuilding;
	}
	/**
	 * Get the number of soldier cards
	 * @return
	 */
	public int getSoldier() {
		return soldier;
	}
	/**
	 * Set the number of soldier cards
	 * @param soldier
	 */
	public void setSoldier(int soldier) {
		this.soldier = soldier;
	}
	/**
	 * Get the number of YearOfPlenty cards
	 * @return
	 */
	public int getYearOfPlenty() {
		return yearOfPlenty;
	}
	/**
	 * Set the number of YearOfPlenty cards
	 * @param yearOfPlenty
	 */
	public void setYearOfPlenty(int yearOfPlenty) {
		this.yearOfPlenty = yearOfPlenty;
	}	

}
