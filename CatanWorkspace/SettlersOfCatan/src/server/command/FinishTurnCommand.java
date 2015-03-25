package server.command;

import org.apache.log4j.Logger;

import server.comm.response.JsonResponse;
import shared.CatanModel;
import shared.DevCardList;
import shared.Player;
import shared.TurnType;
import shared.comm.serialization.FinishTurnRequest;

public class FinishTurnCommand implements ICommand<CatanModel>
{
	private static final Logger log = Logger.getLogger(FinishTurnCommand.class);
	
	private int owner;

	public FinishTurnCommand(FinishTurnRequest request) 
	{
		this.owner = request.getPlayerIndex();
		// TODO Auto-generated constructor stub
	}

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
		boolean firstTurnEnd = true;
		boolean secondTurnEnd = true;
		
		Player player = catanModel.getPlayers()[owner];
		
		DevCardList newCards = player.getNewDevCards();
		DevCardList oldCards = player.getOldDevCards();
		oldCards.setMonopoly(oldCards.getMonopoly() + newCards.getMonopoly());

		oldCards.setRoadBuilding(oldCards.getRoadBuilding() + newCards.getRoadBuilding());
		oldCards.setSoldier(oldCards.getSoldier() + newCards.getSoldier());
		oldCards.setYearOfPlenty(oldCards.getYearOfPlenty() + newCards.getYearOfPlenty());
		newCards = new DevCardList();
		player.setPlayedDevCard(false);
		
		catanModel.getTurnTracker().nextTurn();
		catanModel.setVersion(catanModel.getVersion() + 1);
		
		if ((catanModel.getTurnTracker().getStatus().equals(TurnType.SECOND_ROUND) && catanModel.getTurnTracker().getCurrentTurn() == 0) || 
				!catanModel.getTurnTracker().getStatus().equals(TurnType.FIRST_ROUND) && !catanModel.getTurnTracker().getStatus().equals(TurnType.SECOND_ROUND)) 
		{
			catanModel.getTurnTracker().setStatus(TurnType.ROLLING);
		}
		
		log.trace(catanModel.getTurnTracker().getStatus().toString());
		
		if (catanModel.getTurnTracker().equals(TurnType.FIRST_ROUND))
		{
			  for (int i = 0; i < 4; i++)
			  {
				  log.trace("First Turn End: " + firstTurnEnd);
				  if(catanModel.getPlayers()[i].getRoads() != 14 && catanModel.getPlayers()[i].getSettlements() != 4)
				  {
					  firstTurnEnd = false;
				  }
			  }
			  
			  if (firstTurnEnd)
			  {
				  catanModel.getTurnTracker().setStatus(TurnType.SECOND_ROUND);
			  }
		}
		  
		if (catanModel.getTurnTracker().equals(TurnType.SECOND_ROUND))
		{
			  for (int i = 0; i < 4; i++)
				  if(catanModel.getPlayers()[i].getRoads() != 13 && catanModel.getPlayers()[i].getSettlements() != 3)
					  secondTurnEnd = false;
			  
			  if (secondTurnEnd)
				  catanModel.getTurnTracker().setStatus(TurnType.PLAYING);
		}
	
		return catanModel;
	}

}
