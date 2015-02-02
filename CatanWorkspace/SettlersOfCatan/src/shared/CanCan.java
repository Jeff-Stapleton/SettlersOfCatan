package shared;

public class CanCan {

//  Things we are suppose to implement as given by the TA's in the message from learning suite.
//	CanDiscardCards, CanRollNumber, CanBuildRoad, CanBuildSettlement, CanBuildCity, CanOfferTrade, CanMaritimeTrade, 
//	CanFinishTurn,CanBuyDevCard, CanUseYearOfPlenty, CanUseRoadBuilder, CanUseSoldier, CanUseMonopoly, CanUseMonument, CanPlaceRobber
	
	/**
	 * Checks if a player can trade.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public boolean canTrade(Player player){		
		return false;
	}
	
	/**
	 * Can port trade.
	 *
	 * @param port the port
	 * @return true, if successful
	 */
	public boolean canPortTrade(Player player){
		return false;
	}
	
	/**
	 * Checks to see if a dev card can be bought by this player
	 * @return
	 */
	public boolean canBuyDevCard(Player player){
		if (player.getResources().getWool() >= 1 && player.getResources().getGrain() >= 1 && player.getResources().getOre() >= 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean canPlayDevCard(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}	
	
	/**
	 * Checks to see if a settlement can be bought by this player
	 * @return
	 */
	public boolean canBuySettlement(Player player){
		if (player.getSettlements() >= 1 && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1 
				&& player.getResources().getGrain() >= 1 && player.getResources().getWool() >= 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Checks to see if a city can be bought by this player
	 * @return
	 */
	public boolean canBuyCity(Player player){
		if (player.getCities() >= 1 && player.getResources().getGrain() >= 2 && player.getResources().getOre() >= 3){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Checks to see if a road can be bought by this player
	 * @return
	 */
	public boolean canBuyRoad(Player player){
		if (player.getRoads() >= 1 && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean canBuildSettlement(Player player){
		return false;
	}
	
	public boolean canBuildCity(Player player){
		return false;
	}
	
	public boolean canBuildRoad(Player player){
		return false;
	}
	
	public boolean canPlaceRobber(Hex location){
		return false;
	}
}
