package server.command;

import java.util.ArrayList;
import java.util.Random;

import shared.CatanModel;
import shared.Player;
import shared.TurnType;
import shared.comm.serialization.RobPlayerRequest;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

public class RobPlayerCommand implements ICommand<CatanModel>
{
	private int victimIndex;
	private int playerIndex;
	private HexLocation hexLocation;
	
	public RobPlayerCommand(RobPlayerRequest request) 
	{
		this.victimIndex = request.getVictimIndex();
		this.playerIndex = request.getPlayerIndex();
		this.hexLocation = request.getLocation();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Executes "Rob Player", player picks from a list of robbable player.  The selected player
	 * must give the player one card at random.
	 *
	 * @pre The victim is built on the hex and has at least one card 
	 * @post completes the rob player
	 * 
	 * @param a PlayerIndex, a VictimeIndex, a Location
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) 
	{		
		if (victimIndex >= 0 && victimIndex < 4) 
		{
			
			Player player = catanModel.getPlayers()[playerIndex];
			Player target = catanModel.getPlayers()[victimIndex];
			
			
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

		catanModel.getMap().getRobber().setLocation(hexLocation.getX(), hexLocation.getY());
		catanModel.getTurnTracker().setStatus(TurnType.PLAYING);
		return catanModel;
	}

}
