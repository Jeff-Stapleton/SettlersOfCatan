package server.command;

import shared.CatanModel;
import shared.DevCardList;
import shared.Player;
import shared.TurnType;

public class FinishTurnCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Finish Turn", ends the players turn and allows the next player to 
	 * perform his or her turn.
	 *
	 * @pre Its is indeed the players turn and no other actions ie "trading" are currently pending
	 * @post completes the finish turn
	 * 
	 * @param a PlayerIndex
	 */
	
	@Override
	public CatanModel execute(CatanModel catanModel) 
	{
		int owner=catanModel.getTurnTracker().getCurrentTurn();
		Player player = catanModel.getPlayers()[owner];
		
		DevCardList newCards=player.getNewDevCards();
		DevCardList oldCards=player.getOldDevCards();
		oldCards.setMonopoly(oldCards.getMonopoly()+newCards.getMonopoly());

		oldCards.setRoadBuilding(oldCards.getRoadBuilding()+newCards.getRoadBuilding());
		oldCards.setSoldier(oldCards.getSoldier()+newCards.getSoldier());
		oldCards.setYearOfPlenty(oldCards.getYearOfPlenty()+newCards.getYearOfPlenty());
		newCards = new DevCardList();
		player.setPlayedDevCard(false);
		
		catanModel.getTurnTracker().nextTurn();
		catanModel.setVersion(catanModel.getVersion() + 1);
		
		if ((catanModel.getTurnTracker().getStatus().equals(TurnType.SECOND_ROUND) && catanModel.getTurnTracker().getCurrentTurn() == 0) || 
				!catanModel.getTurnTracker().getStatus().equals(TurnType.FIRST_ROUND) && !catanModel.getTurnTracker().getStatus().equals(TurnType.SECOND_ROUND)) {
			catanModel.getTurnTracker().setStatus(TurnType.ROLLING);
		}
	
		return catanModel;
	}

}
