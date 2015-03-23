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
		Player player = catanModel.getPlayers()[owner];
		
		player.setVictoryPoints(player.getVictoryPoints()+1);
		player.getOldDevCards().setMonument(player.getOldDevCards().getMonument() - 1);

		MapChecks.checkForWinner(catanModel, owner);
		
		return catanModel;
	}

}
