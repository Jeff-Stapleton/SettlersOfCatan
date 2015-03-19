package server.command;

import shared.CatanModel;
import shared.Player;

public class MapChecks 
{
	public static void checkForLongestRoad(CatanModel game, int currentPlayerIndex){

		int longest = 4;

		Player currentPlayer = game.getPlayers()[currentPlayerIndex];

		int playerWith=game.getTurnTracker().getLongestRoad();

		
		
		if(playerWith == -1 && 15-currentPlayer.getRoads() > longest) { // no one has it yet

			playerWith = currentPlayerIndex;
			currentPlayer.setVictoryPoints(currentPlayer.getVictoryPoints() + 2);
		}

		else if(playerWith > -1 && playerWith < game.getPlayers().length && 15 - currentPlayer.getRoads() > 15 - game.getPlayers()[playerWith].getRoads()) 
		{ // take it from who has it
			game.getPlayers()[playerWith].setVictoryPoints(game.getPlayers()[playerWith].getVictoryPoints() - 2);
       	 	playerWith = currentPlayerIndex;
       	 	currentPlayer.setVictoryPoints(currentPlayer.getVictoryPoints() + 2);

		}

		if(playerWith!=-1) {

			game.getTurnTracker().setLongestRoad(playerWith);

		}

	}
	
	public static void checkForLargestArmy(CatanModel game, int currentPlayerIndex){
		 Player currentPlayer = game.getPlayers()[currentPlayerIndex];

         int largest=2;

         int playerWith=game.getTurnTracker().getLargestArmy();

         if(playerWith == -1 && currentPlayer.getSoldiers() > largest) { // no one has it yet

                 playerWith = currentPlayerIndex;
                 currentPlayer.setVictoryPoints(currentPlayer.getVictoryPoints() + 2);

         }

         else if(playerWith > -1 && playerWith < game.getPlayers().length && currentPlayer.getSoldiers() > game.getPlayers()[playerWith].getSoldiers()) { // take it from who has it
        	 game.getPlayers()[playerWith].setVictoryPoints(game.getPlayers()[playerWith].getVictoryPoints() - 2);
             playerWith = currentPlayerIndex;
             currentPlayer.setVictoryPoints(currentPlayer.getVictoryPoints() + 2);

         }

         if(playerWith!=-1) {

                 game.getTurnTracker().setLargestArmy(playerWith);

         }
	}
	
	public static void checkForWinner(CatanModel game,int owner){
		int points=game.getPlayers()[owner].getVictoryPoints();
		if(points>=10)
			game.setWinner(owner);
	}

}
