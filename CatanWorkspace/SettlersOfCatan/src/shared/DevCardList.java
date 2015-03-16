package shared;

import java.util.Observable;
import java.util.Random;

public class DevCardList extends Observable {

	private int monopoly = 0;
	private int monument = 0;
	private int roadBuilding = 0;
	private int soldier = 0;
	private int yearOfPlenty = 0;
	
	public DevCardList(){}

	public DevCardList(int monopoly, int monument, int roadBuilding, int soldier, int yearOfPlenty){
		setMonopoly(monopoly);
		setMonument(monument);
		setRoadBuilding(roadBuilding);
		setSoldier(soldier);
		setYearOfPlenty(yearOfPlenty);
	}
	
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
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("yearOfPlenty : ").append(yearOfPlenty).append(",\n");
		string.append("monopoly : ").append(monopoly).append(",\n");
		string.append("soldier : ").append(soldier).append(",\n");
		string.append("roadBuilding : ").append(roadBuilding).append(",\n");
		string.append("monument : ").append(monument).append("\n");
		
		string.append("}");
		
		return string.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + monopoly;
		result = prime * result + monument;
		result = prime * result + roadBuilding;
		result = prime * result + soldier;
		result = prime * result + yearOfPlenty;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DevCardList other = (DevCardList) obj;
		if (monopoly != other.monopoly)
			return false;
		if (monument != other.monument)
			return false;
		if (roadBuilding != other.roadBuilding)
			return false;
		if (soldier != other.soldier)
			return false;
		if (yearOfPlenty != other.yearOfPlenty)
			return false;
		return true;
	}

	public boolean updateFrom(DevCardList dcl)
	{
		boolean updated = false;
		
		if (monopoly != dcl.monopoly)
		{
			monopoly = dcl.monopoly;
			updated = true;
		}
		if (monument != dcl.monument)
		{
			monument = dcl.monument;
			updated = true;
		}
		if (roadBuilding != dcl.roadBuilding)
		{
			roadBuilding = dcl.roadBuilding;
			updated = true;
		}
		if (soldier != dcl.soldier)
		{
			soldier = dcl.soldier;
			updated = true;
		}
		if (yearOfPlenty != dcl.yearOfPlenty)
		{
			yearOfPlenty = dcl.yearOfPlenty;
			updated = true;
		}
		
		if (updated)
		{
			setChanged();
			notifyObservers();
		}
		
		return updated;
	}

}
