package server.command;

import shared.CatanModel;
import shared.Player;

public class MonopolyCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Monopoly Dev Card", removes the dev card from the players hand.  Takes
	 * every resource, of a specified type, from all other players and adds them to the player
	 * hand
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play monopoly card
	 * 
	 * @param a PlayerIndex, a Resource
	 */
	@Override
	public CatanModel execute(CatanModel catanModel){//CatanModel model) {
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[catanModel.getTurnTracker().getCurrentTurn()];
		
		int count = 0;
		// collect all the resources from other players
		for(Player p : catanModel.getPlayers()){
			if (p.getPlayerID() != thisPlayer.getPlayerID()){
//				count += p.getResources().getResource(resource);
//				p.getResources().setResource(resource, 0);
			}
		}
		// add resources to thisPlayer
//		thisPlayer.getResources().setResource(resource, count);
		// remove card from player
		thisPlayer.getOldDevCards().setMonopoly(thisPlayer.getOldDevCards().getMonopoly() - 1);
		thisPlayer.setPlayedDevCard(true);
		return catanModel;
	}

}
