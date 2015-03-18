package server.command;

import shared.CatanModel;
import shared.Player;

public class SoldierCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Soldier Dev Card", removes the dev card from the players hand.  Awards the player 
	 * allowing him or her to move the robber and rob a player
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play soldier card
	 * 
	 * @param a PlayerIndex, a VictimIndex, a Location
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[catanModel.getTurnTracker().getCurrentTurn()];
		/*
		// Apparently this is server code
		// remove card from player
		thisPlayer.getOldDevCards().setSoldier(thisPlayer.getOldDevCards().getSoldier() - 1);
		*/
		return catanModel;
	}

}
