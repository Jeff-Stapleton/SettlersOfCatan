package server.command;

import shared.CatanModel;

public class MonumentCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Monument Dev Card", removes the dev card from the players hand.  Awards the player 
	 * one victory point.
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play monument card
	 * 
	 * @param a PlayerIndex
	 */
	@Override
	public CatanModel execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
