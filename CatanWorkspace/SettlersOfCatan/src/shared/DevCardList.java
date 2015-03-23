package shared;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	 * Returns a random card from the DevCardList by add all remaining cards to a list, randomizing the list, and choosing the first one
	 * @return
	 */
	public DevCardList getRandomCard(){
		DevCardList devCard = new DevCardList();
		List<String> list = toList();
		Collections.shuffle(list);
	    String nameOfRandomCard = list.get(0); 
	    devCard.addCardByName(nameOfRandomCard);
		return devCard;
	}
	
	public void addCardByName(String name){
		switch (name){
		case "monopoly":
			setMonopoly(getMonopoly() + 1);
			break;
		case "monument":
			setMonument(getMonument() + 1);
			break;
		case "roadBuilding":
			setRoadBuilding(getRoadBuilding() + 1);
			break;
		case "soldier":
			setSoldier(getSoldier() + 1);
			break;
		case "yearOfPlenty":
			setYearOfPlenty(getYearOfPlenty() + 1);
			break;
		}
	}
	
	public void removeCardByName(String name){
		switch (name){
		case "monopoly":
			setMonopoly(getMonopoly() - 1);
			break;
		case "monument":
			setMonument(getMonument() - 1);
			break;
		case "roadBuilding":
			setRoadBuilding(getRoadBuilding() - 1);
			break;
		case "soldier":
			setSoldier(getSoldier() - 1);
			break;
		case "yearOfPlenty":
			setYearOfPlenty(getYearOfPlenty() - 1);
			break;
		}
	}

	private List<String> toList(){
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < getMonopoly(); i++)
		{
			list.add("monopoly");
		}
		for (int i = 0; i < getMonument(); i++)
		{
			list.add("monumnet");
		}
		for (int i = 0; i < getRoadBuilding(); i++)
		{
			list.add("roadBuilding");
		}
		for (int i = 0; i < getSoldier(); i++)
		{
			list.add("soldier");
		}
		for (int i = 0; i < getYearOfPlenty(); i++)
		{
			list.add("yearOfPlenty");
		}
		return list;
	}
	
	public static void moveCard(DevCardList newDevCards, DevCardList bankDevCards, DevCardList card) {
		String cardType = card.toList().get(0);
		bankDevCards.removeCardByName(cardType);
		newDevCards.addCardByName(cardType);		
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
	
	public void reset(){
		monopoly = 0;
		monument = 0;
		roadBuilding = 0;
		soldier = 0;
		yearOfPlenty = 0;		
	}

}
