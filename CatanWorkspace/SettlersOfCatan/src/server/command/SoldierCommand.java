package server.command;

import java.util.ArrayList;
import java.util.Random;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.comm.serialization.SoldierRequest;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

public class SoldierCommand implements ICommand<CatanModel>
{
	private int owner;
	private int victim;
	private HexLocation hex;

	public SoldierCommand(SoldierRequest request) 
	{
		this.owner = request.getPlayerIndex();
		this.hex = request.getLocation();
		this.victim = request.getVictimIndex();
	}

	/**
	 * Executes "Soldier Dev Card", removes the dev card from the players hand.  Awards the player 
	 * allowing him or her to move the robber and rob a player
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play soldier card
	 * 
	 * @param a PlayerIndex, a VictimIndex, a Location
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) 
	{
		Player player=catanModel.getPlayers()[owner];		
		
		if (!player.hasPlayedDevCard()) {
			
			if (victim >= 0 && victim < 4) {
				
				Player target = catanModel.getPlayers()[victim];
				
				
				ArrayList<ResourceType> potentialLoot = new ArrayList<ResourceType>();
				
				if (target.getResources().totalCount() > 0) {
					if (target.getResources().getBrick() > 0)
						potentialLoot.add(ResourceType.BRICK);
					if (target.getResources().getOre() > 0)
						potentialLoot.add(ResourceType.ORE);
					if (target.getResources().getSheep() > 0)
						potentialLoot.add(ResourceType.SHEEP);
					if (target.getResources().getWheat() > 0)
						potentialLoot.add(ResourceType.WHEAT);
					if (target.getResources().getWood() > 0)
						potentialLoot.add(ResourceType.WOOD);
					
					Random randomGenerator = new Random();
					int lootIndex = randomGenerator.nextInt(potentialLoot.size());
					
					ResourceType loot=potentialLoot.get(lootIndex);
					if(loot.equals(ResourceType.WOOD))
					{
						player.getResources().setWood(player.getResources().getWood()+1);
						target.getResources().setWood(target.getResources().getWood()-1);
					}
					else if(loot.equals(ResourceType.WHEAT))
					{
						player.getResources().setWheat(player.getResources().getWheat()+1);
						target.getResources().setWheat(target.getResources().getWheat()-1);
					}
					else if(loot.equals(ResourceType.ORE))
					{
						player.getResources().setOre(player.getResources().getOre()+1);
						target.getResources().setOre(target.getResources().getOre()-1);
					}
					else if(loot.equals(ResourceType.BRICK))
					{
						player.getResources().setBrick(player.getResources().getBrick()+1);
						target.getResources().setBrick(target.getResources().getBrick()-1);
					}
					else if(loot.equals(ResourceType.SHEEP))
					{
						player.getResources().setSheep(player.getResources().getSheep()+1);
						target.getResources().setSheep(target.getResources().getSheep()-1);
					}
				}
			}
			
			player.getOldDevCards().setSoldier(player.getOldDevCards().getSoldier() - 1);
			player.setSoldiers(player.getSoldiers()+1);
	
			MapChecks.checkForLargestArmy(catanModel, owner);
			
			player.setPlayedDevCard(true);
		}

		return catanModel;
		
		
		
		
		/*
		// expecting these params: playerIndex, victim.getPlayerIndex(), robber
		
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[catanModel.getTurnTracker().getCurrentTurn()];
//		Player thisPlayer = catanModel.getPlayers()[playerIndex];
		
//		Player victim = catanModel.getPlayers()[victimIndex];
//		ResourceList.moveResources(victim, thisPlayer, victim.Rob());
		
		
		// remove card from player
		thisPlayer.getOldDevCards().setSoldier(thisPlayer.getOldDevCards().getSoldier() - 1);
		thisPlayer.setPlayedDevCard(true);
		return catanModel;
		*/
	}

}
