package server.command;

import shared.CatanModel;
import shared.Player;

public class RoadBuildingCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Road Building Dev Card", removes the dev card from the players hand.  Awards the 
	 * player with the ability to place 2 free roads
	 *
	 * @pre The player does in deed have the card and it the correct turn/state and has enough roads
	 * @post completes the play road building card
	 * 
	 * @param a PlayerIndex, a LocationOne, a LocationTwo
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		// should be receiving a player index and two road edgeLocations here
		
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[catanModel.getTurnTracker().getCurrentTurn()];
//		Player thisPlayer = catanModel.getPlayers()[playerIndex];
		
		// subtract 2 roads from the player
		thisPlayer.setRoads(thisPlayer.getRoads() - 2);
		
		// place roads into correct locations on the map
//		catanModel.getMap().buildRoad(thisPlayer.getPlayerIndex(), firstRoadLoc);
//		catanModel.getMap().buildRoad(thisPlayer.getPlayerIndex(), secondRoadLoc);
		
		// remove card from player
		thisPlayer.getOldDevCards().setRoadBuilding(thisPlayer.getOldDevCards().getRoadBuilding() - 1);
		thisPlayer.setPlayedDevCard(true);
		return catanModel;
	}

}
