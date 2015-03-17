package server.command;

import shared.CatanModel;

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
		// TODO Auto-generated method stub
		return null;
	}

}
