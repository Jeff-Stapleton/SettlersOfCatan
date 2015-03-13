package server.command;

import shared.CatanModel;

public class BuyDevCardCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Buy Dev Card", subtracts the resources from the instigator,
	 * adds a dev card to the players hand, and decrease from the bank of dev cards
	 *
	 * @pre The player has enough resources, enough card and is the proper phase/turn
	 * @post completes the buy dev card
	 * 
	 * @param BuyDevCard
	 */
	@Override
	public CatanModel execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
