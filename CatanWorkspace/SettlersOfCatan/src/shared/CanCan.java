package shared;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.*;

public class CanCan {
	// test push from my chromecrook
	
	/**
	 * Checks if a player can trade.
	 *
	 * @param player the player
	 * @return true, if successful
	 */
	public static boolean canOfferTrade(Player player, Player receiver, TurnTracker turn, ResourceList offer){
		// current players turn
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			// correct phase of the game
			if (turn.getStatus() == TurnType.PLAYING){
				// player has resources being offered
				if (ResourceList.hasResourcesCheck(player.getResources(), offer)){
					// receiver has resources that player wants
					if (ResourceList.hasResourcesCheck(receiver.getResources(), ResourceList.invertResources(offer))){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Can port trade.
	 *
	 * @param port the port
	 * @return true, if successful
	 */
	
	public static Port isOnPort(List<Building> newBuildings, List<Port> newPorts, Player player)
	{
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
						return newPorts.get(j);
					else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() - 1 &&
							newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() + 1 &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
						return newPorts.get(j);
					else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() - 1 &&
							newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() + 1 &&
							newPorts.get(j).getDirection() == EdgeDirection.South)
						return newPorts.get(j);
				}
				else if (newBuildings.get(i).getLocation().getDir() == VertexDirection.SouthWest)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
						return newPorts.get(j);
					else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.South)
						return newPorts.get(j);
					else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getHexLoc().getX() == newPorts.get(j).getLocation().getX() - 1 &&
							newBuildings.get(i).getLocation().getHexLoc().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
						return newPorts.get(j);
				}
			}
		}
		return null;
	}
	
	public static boolean canMaritimeTrade(Player player, TurnTracker turn, ResourceList maritimeOffer, ResourceList bank, List<Port> newPorts)
	{
		if (ResourceList.hasResourcesCheck(player.getResources(), maritimeOffer) && ResourceList.hasResourcesCheck(bank, ResourceList.invertResources(maritimeOffer)) && turn.getCurrentTurn() == player.getPlayerIndex() && (player.getSettlements() >= 4 || player.getCities() <= 3) && turn.getStatus() == TurnType.PLAYING)
		{
			List<Building> newBuildings = new ArrayList<Building>();
			if (Map.getSettlements() != null && !Map.getSettlements().isEmpty())
			{
				newBuildings = new ArrayList<Building>(Map.getSettlements());
				if (Map.getCities() != null && !Map.getCities().isEmpty())
					newBuildings.addAll(Map.getCities());
			}
			else if (Map.getCities() != null && !Map.getCities().isEmpty())
			{
				newBuildings = new ArrayList<Building>(Map.getCities());
				if (Map.getSettlements() != null && !Map.getSettlements().isEmpty())
					newBuildings.addAll(Map.getSettlements());
			}
			else
				return false;
			
			ArrayList<Integer> tradeRatio = new ArrayList<Integer>();
			tradeRatio.add(maritimeOffer.getBrick());
			tradeRatio.add(maritimeOffer.getWood());
			tradeRatio.add(maritimeOffer.getGrain());
			tradeRatio.add(maritimeOffer.getWool());
			tradeRatio.add(maritimeOffer.getOre());
			
			ArrayList<Integer> tradeRatioTemp = new ArrayList<Integer>();
			
			for (int i = 0; i < tradeRatio.size(); i++)
				if (tradeRatio.get(i) != 0)
					tradeRatioTemp.add(tradeRatio.get(i));

			if (tradeRatioTemp.size() != 2)
				return false;
			
			int have = 0;
			int give = 0;
			if (tradeRatioTemp.get(0) > tradeRatioTemp.get(1) && Math.signum(tradeRatioTemp.get(0)) == 1 && Math.signum(tradeRatioTemp.get(1)) == -1)
			{
				have = tradeRatioTemp.get(0);
				give = tradeRatioTemp.get(1);
			}
			else if (Math.signum(tradeRatioTemp.get(1)) == 1 && Math.signum(tradeRatioTemp.get(0)) == -1)
			{
				have = tradeRatioTemp.get(1);
				give = tradeRatioTemp.get(0);
			}
			else
				return false;
				
			//List<Port> newPorts = new ArrayList<Port>();
			Port onPort = isOnPort(newBuildings, newPorts, player);
			List<Port> oneTimePorts = new ArrayList<Port>();
			
			for (int i = 0; i < newPorts.size(); i++)
				oneTimePorts.add(newPorts.get(i));
			
			while(onPort != null)
			{
				if (Math.abs(have/give) == onPort.getRatio())
				{
					if (Math.abs(maritimeOffer.getBrick()) >= 2 && onPort.getType() == PortType.BRICK)
						return true;
					else if (Math.abs(maritimeOffer.getOre()) >= 2 && onPort.getType() == PortType.ORE)
						return true;
					else if (Math.abs(maritimeOffer.getWool()) >= 2 && onPort.getType() == PortType.SHEEP)
						return true;
					else if (Math.abs(maritimeOffer.getGrain()) >= 2 && onPort.getType() == PortType.WHEAT)
						return true;
					else if (Math.abs(maritimeOffer.getWood()) >= 2 && onPort.getType() == PortType.WOOD)
						return true;
					else if ((Math.abs(maritimeOffer.getWood()) >= 3 || Math.abs(maritimeOffer.getGrain()) >= 3 || Math.abs(maritimeOffer.getWool()) >= 3 || Math.abs(maritimeOffer.getOre()) >= 3 || Math.abs(maritimeOffer.getBrick()) >= 3) && onPort.getType() == PortType.THREE)
						return true;
				}
				
				oneTimePorts.remove(onPort);
				onPort = isOnPort(newBuildings, oneTimePorts, player);
			}
		}
		
		return false;
	}
	/**
	 * Checks to see if a dev card can be bought by this player
	 * @return
	 */
	public static boolean canBuyDevCard(Player player, DevCardList bank){
		if (player.getResources().getWool() >= 1 && player.getResources().getGrain() >= 1 && player.getResources().getOre() >= 1)
			if (bank.getTotal() > 0)
				return true;

		return false;

	}
	
	public static boolean canUseYearOfPlenty(Player player){
		if (player.getOldDevCards().getYearOfPlenty() > 0){
			if (player.hasPlayedDevCard()){
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean canUseRoadBuilder(Player player){
		if (player.getOldDevCards().getRoadBuilding() > 0){
			if (player.hasPlayedDevCard()){
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean canUseSoldier(Player player){
		if (player.getOldDevCards().getSoldier() > 0){
			if (player.hasPlayedDevCard()){
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean canUseMonopoly(Player player){
		if (player.getOldDevCards().getMonopoly() > 0){
			if (player.hasPlayedDevCard()){
				return false;
			}
			return true;
		}
		return false;
	}
	
	public static boolean canUseMonument(Player player){
		if (player.getOldDevCards().getMonument() > 0){
			if (player.hasPlayedDevCard()){
				return false;
			}
			return true;
		}
		return false;
	}
	
	
	public static boolean canBuildSettlement(Player player, VertexLocation vertexLocation, TurnTracker turn)
	{
		Boolean hasRoad = false;
		
		if (turn.getCurrentTurn() == player.getPlayerIndex() && ((player.getSettlements() <= 4 && player.getSettlements() >= 1) && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1 && player.getResources().getGrain() >= 1 && player.getResources().getWool() >= 1) || turn.getStatus() == TurnType.FIRST_ROUND)
		{
			List<Building> newBuildings = new ArrayList<Building>();
			if (Map.getSettlements() != null && !Map.getSettlements().isEmpty())
			{
				newBuildings = new ArrayList<Building>(Map.getSettlements());
				if (Map.getCities() != null && !Map.getCities().isEmpty())
					newBuildings.addAll(Map.getCities());
			}
			else if (Map.getCities() != null && !Map.getCities().isEmpty())
			{
				newBuildings = new ArrayList<Building>(Map.getCities());
				if (Map.getSettlements() != null && !Map.getSettlements().isEmpty())
					newBuildings.addAll(Map.getSettlements());
			}
			else
				return false;
			
			List<Road> newRoads = new ArrayList<Road>();
			if (Map.getRoads() != null && !Map.getRoads().isEmpty())
				newRoads = Map.getRoads();
			
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
				
				if (turn.getStatus() != TurnType.FIRST_ROUND)
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
				
				if (turn.getStatus() != TurnType.FIRST_ROUND)
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
	
	public static boolean canBuildCity(Player player, VertexLocation vertexLocation, TurnTracker turn)
	{
		if (turn.getCurrentTurn() == player.getPlayerIndex() && player.getSettlements() <= 4 && player.getCities() >= 1 && player.getResources().getGrain() >= 2 && player.getResources().getOre() >= 3)
		{
			List<Building> newSettlements = Map.getSettlements();
			for (int i = 0; i < newSettlements.size(); i++)
				if (newSettlements.get(i).getLocation().getHexLoc().getX() == vertexLocation.getHexLoc().getX() && 
						newSettlements.get(i).getLocation().getHexLoc().getY() == vertexLocation.getHexLoc().getY() && 
						newSettlements.get(i).getLocation().getDir() == vertexLocation.getDir() && 
						newSettlements.get(i).getOwner() == player.getPlayerIndex())
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
				if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
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
				if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() &&  newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
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
				if (newRoads.get(i).getLocation().getHexLoc().getX() == edge.getHexLoc().getX() && newRoads.get(i).getLocation().getHexLoc().getY() == edge.getHexLoc().getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
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
	
	public static boolean canBuildRoad(Player player, EdgeLocation edge, TurnTracker turn){
		if (turn.getCurrentTurn() == player.getPlayerIndex() && player.getRoads() >= 1 && (player.getRoads() <= 14 && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1) || turn.getStatus() == TurnType.FIRST_ROUND)
		{
			List<Building> newBuildings = new ArrayList<Building>();
			if (Map.getSettlements() != null && !Map.getSettlements().isEmpty())
			{
				newBuildings = new ArrayList<Building>(Map.getSettlements());
				if (Map.getCities() != null && !Map.getCities().isEmpty())
					newBuildings.addAll(Map.getCities());
			}
			else if (Map.getCities() != null && !Map.getCities().isEmpty())
			{
				newBuildings = new ArrayList<Building>(Map.getCities());
				if (Map.getSettlements() != null && !Map.getSettlements().isEmpty())
					newBuildings.addAll(Map.getSettlements());
			}
			else
				return false;

			//Water checks
			if((edge.getHexLoc().getX() == -3 || edge.getHexLoc().getX() == 3) && edge.getDir() == EdgeDirection.South)
				return false;
			else if((edge.getHexLoc().getY() == 3 || edge.getHexLoc().getY() == -3) && edge.getDir() == EdgeDirection.SouthWest)
				return false;
			else if((edge.getHexLoc().getX() == -3 && edge.getHexLoc().getY() == 0) || 
					(edge.getHexLoc().getX() == -2 && edge.getHexLoc().getY() == -1) || 
					(edge.getHexLoc().getX() == -1 && edge.getHexLoc().getY() == -2) || 
					(edge.getHexLoc().getX() ==  0 && edge.getHexLoc().getY() == 3) || 
					(edge.getHexLoc().getX() == 1 && edge.getHexLoc().getY() == 2) || 
					(edge.getHexLoc().getX() == 2 && edge.getHexLoc().getY() == 1)
					&& edge.getDir() == EdgeDirection.SouthEast)
				return false;
			
			if (!hasAdjacentRoad(player,edge) || turn.getStatus() == TurnType.FIRST_ROUND)
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
			else
				return true;
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
	
	public static boolean canDiscardCards(Player player, TurnTracker turn){
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			if (turn.getStatus() == TurnType.DISCARDING){
				if (player.hasDiscarded() == false){
					// Does this have to do with when a 7 is rolled?
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean canRollNumber(Player player, TurnTracker turn){
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			if (turn.getStatus() == TurnType.ROLLING){
				return true;
			}
		}
		return false;
	}
	
	public static boolean canFinishTurn(Player player, TurnTracker turn){
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			if (turn.getStatus() == TurnType.PLAYING){
				return true;
			}
		}
		return false;
	}
	
}