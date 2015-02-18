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
				if (newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
						return newPorts.get(j);
					else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() + 1 &&
							newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY()&&
							newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
						return newPorts.get(j);
					else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() + 1 &&
							newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.South)
						return newPorts.get(j);
				}
				else if (newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
						return newPorts.get(j);
					else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.South)
						return newPorts.get(j);
					else if (newBuildings.get(i).getOwner() == player.getPlayerIndex() && 
							newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() + 1 &&
							newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() + 1 &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
						return newPorts.get(j);
				}
			}
		}
		return null;
	}
	
	public static boolean canMaritimeTrade(Player player, TurnTracker turn, ResourceList maritimeOffer, ResourceList bank, List<Port> newPorts, Map map)
	{
		if (ResourceList.hasResourcesCheck(player.getResources(), maritimeOffer) && ResourceList.hasResourcesCheck(bank, ResourceList.invertResources(maritimeOffer)) && turn.getCurrentTurn() == player.getPlayerIndex() && (player.getSettlements() >= 4 || player.getCities() <= 3) && turn.getStatus() == TurnType.PLAYING)
		{
			List<Building> newBuildings = new ArrayList<Building>();
			if (map.getSettlements() != null && !map.getSettlements().isEmpty())
			{
				newBuildings.addAll(map.getSettlements());
				if (map.getCities() != null && !map.getCities().isEmpty())
					newBuildings.addAll(map.getCities());
			}
			else if (map.getCities() != null && !map.getCities().isEmpty())
			{
				newBuildings.addAll(map.getCities());
				if (map.getSettlements() != null && !map.getSettlements().isEmpty())
					newBuildings.addAll(map.getSettlements());
			}
			else
				return false;
			
			ArrayList<Integer> tradeRatio = new ArrayList<Integer>();
			tradeRatio.add(maritimeOffer.getBrick());
			tradeRatio.add(maritimeOffer.getWood());
			tradeRatio.add(maritimeOffer.getWheat());
			tradeRatio.add(maritimeOffer.getSheep());
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
					else if (Math.abs(maritimeOffer.getSheep()) >= 2 && onPort.getType() == PortType.SHEEP)
						return true;
					else if (Math.abs(maritimeOffer.getWheat()) >= 2 && onPort.getType() == PortType.WHEAT)
						return true;
					else if (Math.abs(maritimeOffer.getWood()) >= 2 && onPort.getType() == PortType.WOOD)
						return true;
					else if ((Math.abs(maritimeOffer.getWood()) >= 3 || Math.abs(maritimeOffer.getWheat()) >= 3 || Math.abs(maritimeOffer.getSheep()) >= 3 || Math.abs(maritimeOffer.getOre()) >= 3 || Math.abs(maritimeOffer.getBrick()) >= 3) && onPort.getType() == PortType.THREE)
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
	public static boolean canBuyDevCard(Player player, DevCardList bank, TurnTracker turn){
		// current players turn
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			// correct phase of the game
			if (turn.getStatus() == TurnType.PLAYING){
				if (player.getResources().getSheep() >= 1 && player.getResources().getWheat() >= 1 && player.getResources().getOre() >= 1)
					if (bank.getTotal() > 0)
						return true;
			}
		}

		return false;

	}
	
	public static boolean canUseYearOfPlenty(Player player, TurnTracker turn){
		// current players turn
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			// correct phase of the game
			if (turn.getStatus() == TurnType.PLAYING){
				if (player.getOldDevCards().getYearOfPlenty() > 0){
					if (player.hasPlayedDevCard()){
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean canUseRoadBuilder(Player player, TurnTracker turn){
		// current players turn
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			// correct phase of the game
			if (turn.getStatus() == TurnType.PLAYING){
				if (player.getOldDevCards().getRoadBuilding() > 0){
					if (player.hasPlayedDevCard()){
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean canUseSoldier(Player player, TurnTracker turn){
		// current players turn
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			// correct phase of the game
			if (turn.getStatus() == TurnType.PLAYING){
				if (player.getOldDevCards().getSoldier() > 0){
					if (player.hasPlayedDevCard()){
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean canUseMonopoly(Player player, TurnTracker turn){
		// current players turn
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			// correct phase of the game
			if (turn.getStatus() == TurnType.PLAYING){
				if (player.getOldDevCards().getMonopoly() > 0){
					if (player.hasPlayedDevCard()){
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean canUseMonument(Player player, TurnTracker turn){
		// current players turn
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			// correct phase of the game
			if (turn.getStatus() == TurnType.PLAYING){
				if (player.getOldDevCards().getMonument() > 0){
					if (player.hasPlayedDevCard()){
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	
	
	public static boolean canBuildSettlement(Player player, VertexLocation vertexLocation, TurnTracker turn, Map map)
	{
		Boolean hasRoad = false;
		
		if (turn.getCurrentTurn() == player.getPlayerIndex() && ((player.getSettlements() <= 4 && player.getSettlements() >= 1) && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1 && player.getResources().getWheat() >= 1 && player.getResources().getSheep() >= 1) || turn.getStatus() == TurnType.FIRST_ROUND)
		{
			List<Building> newBuildings = new ArrayList<Building>();
			if (map.getSettlements() != null && !map.getSettlements().isEmpty())
			{
				newBuildings = new ArrayList<Building>(map.getSettlements());
				if (map.getCities() != null && !map.getCities().isEmpty())
					newBuildings.addAll(map.getCities());
			}
			else if (map.getCities() != null && !map.getCities().isEmpty())
			{
				newBuildings = new ArrayList<Building>(map.getCities());
				if (map.getSettlements() != null && !map.getSettlements().isEmpty())
					newBuildings.addAll(map.getSettlements());
			}
			else
				return false;
			
			List<Road> newRoads = new ArrayList<Road>();
			if (map.getRoads() != null && !map.getRoads().isEmpty())
				newRoads = map.getRoads();
			
			if(vertexLocation.getDirection() == VertexDirection.West)
			{
				for(int i = 0; i < newBuildings.size(); i++)
				{
					if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
						return false;
				}
				
				if (turn.getStatus() != TurnType.FIRST_ROUND)
					for(int i = 0; i < newRoads.size(); i++)
					{
						if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && newRoads.get(i).getLocation().getY() == vertexLocation.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newRoads.get(i).getLocation().getY() == vertexLocation.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newRoads.get(i).getLocation().getY() == vertexLocation.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
							hasRoad = true;
					}
			}
			else if (vertexLocation.getDirection() == VertexDirection.SouthWest)
			{
				for(int i = 0; i < newBuildings.size(); i++)
				{
					if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() + 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() + 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
						return false;
				}
				
				if (turn.getStatus() != TurnType.FIRST_ROUND)
					for(int i = 0; i < newRoads.size(); i++)
					{
						if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && newRoads.get(i).getLocation().getY() == vertexLocation.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && newRoads.get(i).getLocation().getY() == vertexLocation.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() + 1 && newRoads.get(i).getLocation().getY() == vertexLocation.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
							hasRoad = true;
					}
			}
		}
	
		return hasRoad;

		// Check Phases setup phase doesn't need adjacent road
		// check if has adjacnet road
	}
	
	public static boolean canBuildCity(Player player, VertexLocation vertexLocation, TurnTracker turn, Map map)
	{
		if (turn.getCurrentTurn() == player.getPlayerIndex() && player.getSettlements() <= 4 && player.getCities() >= 1 && player.getResources().getWheat() >= 2 && player.getResources().getOre() >= 3)
		{
			List<Building> newSettlements = map.getSettlements();
			for (int i = 0; i < newSettlements.size(); i++)
				if (newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
						newSettlements.get(i).getLocation().getY() == vertexLocation.getY() && 
						newSettlements.get(i).getLocation().getDirection() == vertexLocation.getDirection() && 
						newSettlements.get(i).getOwner() == player.getPlayerIndex())
					return true;
		}
		
		return false;
	}
	
	public static boolean hasAdjacentRoad(Player player, EdgeLocation edge, Map map)
	{
		List<Road> newRoads = map.getRoads();
		
		if (edge.getDir() == EdgeDirection.South)
		{
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getLocation().getX() == edge.getX() && newRoads.get(i).getLocation().getY() == edge.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() && newRoads.get(i).getLocation().getY() == edge.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && newRoads.get(i).getLocation().getY() == edge.getY() + 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && newRoads.get(i).getLocation().getY() == edge.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation() == edge && newRoads.get(i).getLocation().getDir() == EdgeDirection.South) // TODO: ?? This looks broken
					return false;
			}
		}
		else if (edge.getDir() == EdgeDirection.SouthEast)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getLocation().getX() == edge.getX() && newRoads.get(i).getLocation().getY() == edge.getY() &&  newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && newRoads.get(i).getLocation().getY() == edge.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && newRoads.get(i).getLocation().getY() == edge.getY() - 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && newRoads.get(i).getLocation().getY() == edge.getY() - 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation() == edge && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast) // TODO: Looks broken
					return false;
			}
			
		}
		else if (edge.getDir() == EdgeDirection.SouthWest)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getLocation().getX() == edge.getX() && newRoads.get(i).getLocation().getY() == edge.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && newRoads.get(i).getLocation().getY() == edge.getY() + 1 && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && newRoads.get(i).getLocation().getY() == edge.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.South && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && newRoads.get(i).getLocation().getY() == edge.getY() && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast && newRoads.get(i).getOwner() == player.getPlayerIndex())
					return true;
				else if (newRoads.get(i).getLocation() == edge && newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest) // TODO: Looks broken
					return false;
			}
			
		}
		
		return false;
	}
	
	public static boolean canBuildRoad(Player player, EdgeLocation edge, TurnTracker turn, Map map){
		if (turn.getCurrentTurn() == player.getPlayerIndex() && player.getRoads() >= 1 && (player.getRoads() <= 14 && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1) || turn.getStatus() == TurnType.FIRST_ROUND)
		{
			List<Building> newBuildings = new ArrayList<Building>();
			if (map.getSettlements() != null && !map.getSettlements().isEmpty())
			{
				newBuildings = new ArrayList<Building>(map.getSettlements());
				if (map.getCities() != null && !map.getCities().isEmpty())
					newBuildings.addAll(map.getCities());
			}
			else if (map.getCities() != null && !map.getCities().isEmpty())
			{
				newBuildings = new ArrayList<Building>(map.getCities());
				if (map.getSettlements() != null && !map.getSettlements().isEmpty())
					newBuildings.addAll(map.getSettlements());
			}
			else
				return false;
			
			List<Road> newRoads = map.getRoads();
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getLocation().getDir() == edge.getDir() &&
						newRoads.get(i).getLocation().getX() == edge.getX() &&
						newRoads.get(i).getLocation().getY() == edge.getY())
					return false;
			}

			//Water checks
			if((edge.getX() == -3 || edge.getX() == 3) && edge.getDir() == EdgeDirection.South)
				return false;
			else if((edge.getY() == 3 || edge.getY() == -3) && edge.getDir() == EdgeDirection.SouthEast)
				return false;
			else if((edge.getX() == -2 && edge.getY() == -1) || 
					(edge.getX() == -1 && edge.getY() == -2) || 
					(edge.getX() == 0 && edge.getY() == -3) || 
					(edge.getX() == 1 && edge.getY() == 2) || 
					(edge.getX() == 2 && edge.getY() == 1) ||
					(edge.getX() == 3 && edge.getY() == 0)
					&& edge.getDir() == EdgeDirection.SouthWest)
				return false;
			
			if (!hasAdjacentRoad(player,edge, map) || turn.getStatus() == TurnType.FIRST_ROUND)
			{
				if (edge.getDir() == EdgeDirection.South)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && newBuildings.get(i).getLocation().getY() == edge.getY() && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
							return true;
						else if (newBuildings.get(i).getLocation().getX() == edge.getX() && newBuildings.get(i).getLocation().getY() == edge.getY() && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
							return true;
					}
				}
				else if (edge.getDir() == EdgeDirection.SouthEast)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && newBuildings.get(i).getLocation().getY() == edge.getY() && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
							return true;
						else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
							return true;
					}		
				}
				else if (edge.getDir() == EdgeDirection.SouthWest)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getLocation().getX() == edge.getX() && newBuildings.get(i).getLocation().getY() == edge.getY() && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
							return true;
						else if (newBuildings.get(i).getLocation().getX() == edge.getX() && newBuildings.get(i).getLocation().getY() == edge.getY() && newBuildings.get(i).getOwner() == player.getPlayerIndex() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
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
	
	public static boolean canPlaceRobber(Hex location, Robber robber, TurnTracker turn){
		if (turn.getStatus() == TurnType.ROBBING){
			if (robber.getX() == location.getLocation().getX() &&
				robber.getY() == location.getLocation().getY()){
				// Robber must be moved from its location
				return false;
			}
			if (location.getResource() == HexType.DESERT){
				// Robber cannot be placed on the Desert
				return false;
			}
			if (location.getResource() == HexType.WATER){
				// Robber cannot be placed on water
				return false;
			}
			return true;	
		}
		return false;
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
