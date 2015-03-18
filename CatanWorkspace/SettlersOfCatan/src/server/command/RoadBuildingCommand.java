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
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[catanModel.getTurnTracker().getCurrentTurn()];
		/*
		// Apparently this is server code
		// remove card from player
		thisPlayer.getOldDevCards().setRoadBuilding(thisPlayer.getOldDevCards().getRoadBuilding() - 1);
		*/
		return catanModel;
	}

}
