package server.command;

import shared.CatanModel;

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
		// TODO Auto-generated method stub
		return null;
	}

}
