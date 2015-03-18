package server.command;

import shared.CatanModel;
import shared.Player;

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
	public CatanModel execute(CatanModel catanModel) {
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[catanModel.getTurnTracker().getCurrentTurn()];
		// add 1 to players VictoryPoints
		thisPlayer.setVictoryPoints(thisPlayer.getVictoryPoints()+1);
		// remove card from player
		thisPlayer.getOldDevCards().setMonument(thisPlayer.getOldDevCards().getMonument() - 1);
		return catanModel;
	}

}
