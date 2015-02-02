package shared;

import shared.definitions.HexType;

public class CanCan {

//  Things we are suppose to implement as given by the TA's in the message from learning suite.
//	CanDiscardCards, CanRollNumber
//	CanFinishTurn
	
	/**
	 * Checks if a player can trade.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public static boolean canOfferTrade(Player player){		
		return false;
	}
	
	/**
	 * Can port trade.
	 *
	 * @param port the port
	 * @return true, if successful
	 */
	public static boolean canMaritimeTrade(Player player){
		return false;
	}
	
	/**
	 * Checks to see if a dev card can be bought by this player
	 * @return
	 */
	public static boolean canBuyDevCard(Player player){
		if (player.getResources().getWool() >= 1 && player.getResources().getGrain() >= 1 && player.getResources().getOre() >= 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean canUseYearOfPlenty(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean canUseRoadBuilder(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean canUseSoldier(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean canUseMonopoly(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean canUseMonument(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	public static boolean canBuildSettlement(Player player){
		if (player.getSettlements() >= 1 && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1 && player.getResources().getGrain() >= 1 && player.getResources().getWool() >= 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean canBuildCity(Player player){
		if (player.getCities() >= 1 && player.getResources().getGrain() >= 2 && player.getResources().getOre() >= 3){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean canBuildRoad(Player player){
		if (player.getRoads() >= 1 && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public static boolean canPlaceRobber(Hex location, Robber robber){
		if (robber.getLocation() == location.getLocation()){
			// Robber must be moved from its location
			return false;
		}
		if (location.getType() == HexType.DESERT){
			// Robber cannot be placed on the Desert
			return false;
		}
		if (location.getType() == HexType.WATER){
			// Robber cannot be placed on water
			return false;
		}
		return true;		
	}
}
