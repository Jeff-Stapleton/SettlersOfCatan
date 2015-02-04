package shared;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.HexType;
import shared.locations.*;

public class CanCan {
	// test push from my chromecrook

//  Things we are suppose to implement as given by the TA's in the message from learning suite.
//	CanDiscardCards, CanRollNumber
//	CanFinishTurn
	
	/**
	 * Checks if a player can trade.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public static boolean canOfferTrade(Player player){		
		return false;
	}
	
	/**
	 * Can port trade.
	 *
	 * @param port the port
	 * @return true, if successful
	 */
	public static boolean canMaritimeTrade(Player player)
	{
		// No other checks besides if he's by a port right now
		if (player.getSettlements() >= 1 || player.getCities() >=1)
		{
			List<Building> newBuildings = new ArrayList<Building>(Map.getSettlements());
			newBuildings.addAll(Map.getCities());
			List<Port> newPorts = Map.getPorts();
			
			for (int j = 0; j < newPorts.size(); j++)
			{
				for(int i = 0; i < newBuildings.size(); i++)
				{
					if (newBuildings.get(i).getLocation().getDir() == VertexDirection.West)
					{
						if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
								newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
							return true;
						else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
								newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() - 1 &&
								newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() + 1 &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
							return true;
						else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
								newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() - 1 &&
								newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() + 1 &&
								newPorts.get(j).getDirection() == EdgeDirection.South)
							return true;
					}
					else if (newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
					{
						if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
								newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
							return true;
						else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
								newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.South)
							return true;
						else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
								newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() - 1 &&
								newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
							return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks to see if a dev card can be bought by this player
	 * @return
	 */
	public static boolean canBuyDevCard(Player player){
		if (player.getResources().getWool() >= 1 && player.getResources().getGrain() >= 1 && player.getResources().getOre() >= 1)
			return true;

		return false;

	}
	
	public static boolean canUseYearOfPlenty(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean canUseRoadBuilder(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean canUseSoldier(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean canUseMonopoly(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	public static boolean canUseMonument(Player player){
		if (player.hasPlayedDevCard()){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	public static boolean canBuildSettlement(Player player, VertexLocation vertexLocation)
	{
		Boolean hasRoad = false;
		
		if ((player.getSettlements() >= 1 || player.getCities() >= 1) && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1 && player.getResources().getGrain() >= 1 && player.getResources().getWool() >= 1)
		{
			List<Building> newBuildings = new ArrayList<Building>(Map.getSettlements());
			newBuildings.addAll(Map.getCities());
			List<Road> newRoads = Map.getRoads();
			
			if(vertexLocation.getDir() == VertexDirection.West)
			{
				for(int i = 0; i < newBuildings.size(); i++)
				{
					if (newBuildings.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() + 1 && newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() - 1 && newBuildings.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() + 1 && newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && newBuildings.get(i).getLocation().getDir() == VertexDirection.West)
						return false;
				}
				
				for(int i = 0; i < newRoads.size(); i++)
				{
					if (newRoads.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newRoads.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
						hasRoad = true;
					else if (newRoads.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() - 1 && newRoads.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() + 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
						hasRoad = true;
					else if (newRoads.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() - 1 && newRoads.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() + 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
						hasRoad = true;
				}
			}
			else if (vertexLocation.getDir() == VertexDirection.SouthWest)
			{
				for(int i = 0; i < newBuildings.size(); i++)
				{
					if (newBuildings.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() + 1 && newBuildings.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() - 1&& newBuildings.get(i).getLocation().getDir() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() - 1 && newBuildings.get(i).getLocation().getDir() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && newBuildings.get(i).getLocation().getDir() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
						return false;
				}
				
				for(int i = 0; i < newRoads.size(); i++)
				{
					if (newRoads.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newRoads.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
						hasRoad = true;
					else if (newRoads.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && newRoads.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
						hasRoad = true;
					else if (newRoads.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() - 1 && newRoads.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
						hasRoad = true;
				}
			}
		}
	
		return hasRoad;

		// Check Phases setup phase doesn't need adjacent road
		// check if has adjacnet road
	}
	
	public static boolean canBuildCity(Player player, VertexLocation vertexLocation){
		if (player.getSettlements() >= 1 && player.getResources().getGrain() >= 2 && player.getResources().getOre() >= 3)
			{
				List<Building> newSettlements = Map.getSettlements();
				for (int i = 0; i < newSettlements.size(); i++)
					if (newSettlements.get(i).getLocation() == vertexLocation && newSettlements.get(i).getOwner() == player.getPlayerIndex())
						return true;
			}
		
				return false;
	}
	
	public static boolean hasAdjacentRoad(Player player, EdgeLocation edge)
	{
		List<Road> newRoads = Map.getRoads();
		
		if (edge.getDir() == EdgeDirection.South)
		{
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getLocation().getHexLoc() == edge.getHexLoc() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc() == edge.getHexLoc() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() - 1 && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() + 1 && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() - 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc() == edge.getHexLoc() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					return false;
			}
		}
		else if (edge.getDir() == EdgeDirection.SouthEast)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getLocation().getHexLoc() == edge.getHexLoc() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() + 1 && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() + 1 && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() + 1 && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() - 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc() == edge.getHexLoc() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					return false;
			}
			
		}
		else if (edge.getDir() == EdgeDirection.SouthWest)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getLocation().getHexLoc() == edge.getHexLoc() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() - 1 && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() - 1 && newRoads.get(i).getLocation().getHexLoc().getY() + 1 == edge.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() - 1 && newRoads.get(i).getLocation().getHexLoc().getY() + 1 == edge.getHexLoc().getY() - 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc() == edge.getHexLoc() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					return false;
			}
			
		}
		
		return false;
	}
	
	public static boolean canBuildRoad(Player player, EdgeLocation edge){
		if (player.getRoads() >= 1 && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1)
		{
			List<Building> newBuildings = new ArrayList<Building>(Map.getSettlements());
			newBuildings.addAll(Map.getCities());
			
			if (!hasAdjacentRoad(player,edge))
			{
				if (edge.getDir() == EdgeDirection.South)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() + 1 && newBuildings.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() - 1 && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDir() == VertexDirection.West)
							return true;
						else if (newBuildings.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
							return true;
					}
				}
				else if (edge.getDir() == EdgeDirection.SouthEast)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() + 1 && newBuildings.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
							return true;
						else if (newBuildings.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() + 1 && newBuildings.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() - 1 && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDir() == VertexDirection.West)
							return true;
					}		
				}
				else if (edge.getDir() == EdgeDirection.SouthWest)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
							return true;
						else if (newBuildings.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() && newBuildings.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() - 1 && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDir() == VertexDirection.West)
							return true;
					}			
				}
			}	
		}
		
		return false;
		
		// Needs a settlement or road adjacent
	}
	
	public static boolean canPlaceRobber(Hex location, Robber robber){
		if (robber.getLocation() == location.getLocation()){
			// Robber must be moved from its location
			return false;
		}
		if (location.getType() == HexType.DESERT){
			// Robber cannot be placed on the Desert
			return false;
		}
		if (location.getType() == HexType.WATER){
			// Robber cannot be placed on water
			return false;
		}
		return true;		
	}
}
