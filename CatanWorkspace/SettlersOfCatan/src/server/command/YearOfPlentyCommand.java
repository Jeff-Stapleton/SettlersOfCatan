package server.command;

import shared.CatanModel;
import shared.Player;

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
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[catanModel.getTurnTracker().getCurrentTurn()];
		
		// add resources to thisPlayer
//		thisPlayer.getResources().setResource(resource1, thisPlayer.getResources().getResource(resource1) + 1);
//		thisPlayer.getResources().setResource(resource2, thisPlayer.getResources().getResource(resource2) + 1);
		// remove resources from bank
//		bank.setResource(resource1, bank.getResource(resource1) - 1);
//		bank.setResource(resource2, bank.getResource(resource2) - 1);
		// remove card from player
		thisPlayer.getOldDevCards().setYearOfPlenty(thisPlayer.getOldDevCards().getYearOfPlenty() - 1);
		thisPlayer.setPlayedDevCard(true);
		return catanModel;
	}

}
