package server.command;

import shared.CanCan;
import shared.CatanModel;
import shared.DevCardList;
import shared.Player;

public class BuyDevCardCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Buy Dev Card", subtracts the resources from the instigator,
	 * adds a dev card to the players hand, and decrease from the bank of dev cards
	 *
	 * @pre The player has enough resources, enough card and is the proper phase/turn
	 * @post completes the buy dev card
	 * 
	 * @param a PlayerIndex
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[catanModel.getTurnTracker().getCurrentTurn()];
		if (CanCan.canBuyDevCard(thisPlayer, catanModel.getDeck(), catanModel.getTurnTracker())){
			thisPlayer.buyDevCard(catanModel.getBank(), catanModel.getDeck());			
		}
		return null;
	}

}
