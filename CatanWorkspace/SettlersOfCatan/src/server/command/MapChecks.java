package server.command;

import shared.CatanModel;
import shared.Player;
import shared.definitions.HexType;

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
        checkForWinner(game, currentPlayerIndex);

	}
	
	public static void checkForLargestArmy(CatanModel game, int currentPlayerIndex)
	{
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
         checkForWinner(game, currentPlayerIndex);
	}
	
	public static void checkForWinner(CatanModel game,int owner)
	{
		int points=game.getPlayers()[owner].getVictoryPoints();
		if(points>=10)
			game.setWinner(owner);
	}
	
	public static void incrementResources(CatanModel game,int owner,HexType resource,int amount){
		if(resource.equals(HexType.WOOD) && game.getBank().getWood() > 0)
		{
			game.getPlayers()[owner].getResources().setWood(game.getPlayers()[owner].getResources().getWood()+amount);
			game.getBank().setWood(game.getBank().getWood() - amount);
		}
		else if(resource.equals(HexType.SHEEP) && game.getBank().getSheep() > 0)
		{
			game.getPlayers()[owner].getResources().setSheep(game.getPlayers()[owner].getResources().getSheep()+amount);
			game.getBank().setSheep(game.getBank().getSheep() - amount);
		}
		else if(resource.equals(HexType.WHEAT) && game.getBank().getWheat() > 0)
		{
			game.getPlayers()[owner].getResources().setWheat(game.getPlayers()[owner].getResources().getWheat()+amount);
			game.getBank().setWheat(game.getBank().getWheat() - amount);
		}
		else if(resource.equals(HexType.ORE) && game.getBank().getOre() > 0)
		{
			game.getPlayers()[owner].getResources().setOre(game.getPlayers()[owner].getResources().getOre()+amount);
			game.getBank().setOre(game.getBank().getOre() - amount);
		}
		else if(resource.equals(HexType.BRICK) && game.getBank().getBrick() > 0)
		{
			game.getPlayers()[owner].getResources().setBrick(game.getPlayers()[owner].getResources().getBrick()+amount);
			game.getBank().setBrick(game.getBank().getBrick() - amount);
		}
	}

}
