package server.command;

import shared.CatanModel;
import shared.Player;
import shared.comm.serialization.MonumentRequest;

public class MonumentCommand implements ICommand<CatanModel>
{

	private int owner;
	
	public MonumentCommand(MonumentRequest request) 
	{
		this.owner = request.getPlayerIndex();
	}

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
	public CatanModel execute(CatanModel catanModel) 
	{
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[owner];
		// add 1 to players VictoryPoints
		thisPlayer.setVictoryPoints(thisPlayer.getVictoryPoints()+1);
		MapChecks.checkForWinner(catanModel, owner);
		// remove card from player
		thisPlayer.getOldDevCards().setMonument(thisPlayer.getOldDevCards().getMonument() - 1);
		return catanModel;
	}

}
