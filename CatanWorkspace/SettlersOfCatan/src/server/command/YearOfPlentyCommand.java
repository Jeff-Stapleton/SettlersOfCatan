package server.command;

import shared.CatanModel;

public class YearOfPlentyCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Year of Plenty Dev Card", removes the dev card from the players hand.  Awards 
	 * the player any two resources of his/her choosing
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play year of plenty card
	 * 
	 * @param a PlayerIndex, a ResourceOne, a ResourceTwo
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
