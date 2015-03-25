package shared;

import java.util.ArrayList;
import java.util.List;

import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
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
		for(int i = 0; i < newBuildings.size(); i++)
		{
			for (int j = 0; j < newPorts.size(); j++)
			{
				if (newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
					{
						if(newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
						newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
						newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() - 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() - 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY()  &&
								newPorts.get(j).getDirection() == EdgeDirection.South)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() - 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() + 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.North)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() - 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() + 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthWest)
							return newPorts.get(j);
					}
				}
				else if (newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
					{
					
						if(newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.South)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() - 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() + 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() + 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.North)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() - 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() + 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() + 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthWest)
							return newPorts.get(j);
					}
				}
				else if (newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
					{
					
						if(newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getY() - 1 == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() - 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.South)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() - 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.North)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() - 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthWest)
							return newPorts.get(j);
					}
				}
				else if (newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
					{
					
						if(newBuildings.get(i).getLocation().getX()  + 1 == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getY() - 1 == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() - 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.South)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() - 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.North)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() + 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() - 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthWest)
							return newPorts.get(j);
					}
				}
				else if (newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
					{
					
						if(newBuildings.get(i).getLocation().getX() + 1 == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getY() - 1 == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() + 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() - 1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.South)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() + 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.North)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() + 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthWest)
							return newPorts.get(j);
					}
				}
				else if (newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
				{
					if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
					{
					
						if(newBuildings.get(i).getLocation().getX() + 1 == newPorts.get(j).getLocation().getX() &&
							newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
							newPorts.get(j).getDirection() == EdgeDirection.SouthWest)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.South)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.SouthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() + 1  == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.North)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() +1 == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthEast)
							return newPorts.get(j);
						else if (newBuildings.get(i).getLocation().getX() + 1 == newPorts.get(j).getLocation().getX() &&
								newBuildings.get(i).getLocation().getY() == newPorts.get(j).getLocation().getY() &&
								newPorts.get(j).getDirection() == EdgeDirection.NorthWest)
							return newPorts.get(j);
					}
				}
			}
		}
		return null;
	}
	
	public static int bestRatio(Player player, List<Port> newPorts, int maritimeOffer, Map map, PortType portType)
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
		
		
		Port onPort = isOnPort(newBuildings, newPorts, player);
		List<Port> oneTimePorts = new ArrayList<Port>();
		
		for (int i = 0; i < newPorts.size(); i++)
			oneTimePorts.add(newPorts.get(i));
		
		int bestRatio = 4;
		
		while(onPort != null)
		{
			Port onPort1 = null;
			if (onPort.getType() == null)
				onPort1 = new Port(PortType.THREE, onPort.getLocation(), onPort.getDirection(), onPort.getRatio());
			else
				onPort1 = new Port(onPort.getType(), onPort.getLocation(), onPort.getDirection(), onPort.getRatio());
			switch(onPort1.getType())
			{
				case THREE:
				{
					//if (portType.equals(onPort1.getType()))
						if (maritimeOffer >= onPort1.getRatio())
						{
							if (onPort1.getRatio() < bestRatio)
							{
								bestRatio = onPort1.getRatio();
							}
						}
					break;
				}
				
				case WOOD:
				{
					if (portType.equals(onPort1.getType()))//recall
					{
						if (maritimeOffer >= onPort1.getRatio())
						{
							if (onPort1.getRatio() < bestRatio)
							{
								bestRatio = onPort1.getRatio();
							}
						}
					}
					break;
				}
				case BRICK:
				{
					if (portType.equals(onPort1.getType()))
					{
						if (maritimeOffer >= onPort1.getRatio())
						{
							if (onPort1.getRatio() < bestRatio)
							{
								bestRatio = onPort1.getRatio();
							}
						}
					}
					break;
				}
				case SHEEP:
				{
					if (portType.equals(onPort1.getType()))
					{
						if (maritimeOffer >= onPort1.getRatio())
						{
							if (onPort1.getRatio() < bestRatio)
							{
								bestRatio = onPort1.getRatio();
							}
						}
					}
					break;
				}
				case WHEAT:
				{
					if (portType.equals(onPort1.getType()))
					{
						if (maritimeOffer >= onPort1.getRatio())
						{
							if (onPort1.getRatio() < bestRatio)
							{
								bestRatio = onPort1.getRatio();
							}
						}
					}
					break;
				}
				case ORE:
				{
					if (portType.equals(onPort1.getType()))
					{
						if (maritimeOffer >= onPort1.getRatio())
						{
							if (onPort1.getRatio() < bestRatio)
							{
								bestRatio = onPort1.getRatio();
							}
						}
					}
					break;
				}
			}
			
			oneTimePorts.remove(onPort);
			onPort = isOnPort(newBuildings, oneTimePorts, player);
		}
		return bestRatio;
	}
	
	public static boolean canMaritimeTrade(Player player, TurnTracker turn, ResourceList maritimeOffer, ResourceType resource, ResourceList bank, List<Port> newPorts, Map map)
	{
		boolean validTrade = false;
		if (ResourceList.hasResourcesCheck(player.getResources(), maritimeOffer) 
				&& ResourceList.hasResourcesCheck(bank, ResourceList.invertResources(maritimeOffer)) 
				&& turn.getCurrentTurn() == player.getPlayerIndex()  
				&& turn.getStatus() == TurnType.PLAYING)
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
			
			Port onPort = isOnPort(newBuildings, newPorts, player);
			List<Port> oneTimePorts = new ArrayList<Port>();
			
			for (int i = 0; i < newPorts.size(); i++)
				oneTimePorts.add(newPorts.get(i));
			
			while(onPort != null)
			{
				switch (resource)
				{
					case WOOD:
					{
						if(onPort.getType() == null || onPort.getType().equals(PortType.WOOD) )
						{
							if (maritimeOffer.getWood() >= onPort.getRatio())
							{
								validTrade = true;
								return validTrade;
							}
							else
							{
								break;
							}
						}
						break;
					}
					case BRICK:
					{
						if(onPort.getType() == null || onPort.getType().equals(PortType.BRICK))
						{
							if (maritimeOffer.getBrick() >= onPort.getRatio())
							{
								validTrade = true;
								return validTrade;
							}
							else
							{
								break;
							}
						}
						break;
					}
					case SHEEP:
					{
						if(onPort.getType() == null || onPort.getType().equals(PortType.SHEEP))
						{
							if (maritimeOffer.getSheep() >= onPort.getRatio())
							{
								validTrade = true;
								return validTrade;
							}
							else
							{
								break;
							}
						}
						break;
					}
					case WHEAT:
					{
						if(onPort.getType() == null || onPort.getType().equals(PortType.WHEAT))
						{
							if (maritimeOffer.getWheat() >= onPort.getRatio())
							{
								validTrade = true;
								return validTrade;
							}
							else
							{
								break;
							}
						}
						break;
					}
					case ORE:
					{
						if(onPort.getType() == null || onPort.getType().equals(PortType.ORE))
						{
							if (maritimeOffer.getOre() >= onPort.getRatio())
							{
								validTrade = true;
								return validTrade;
							}
							else
							{
								break;
							}
						}
						break;
					}
				}
				oneTimePorts.remove(onPort);
				onPort = isOnPort(newBuildings, oneTimePorts, player);
			}
			
			// no valid ports, default 4 to 1 exchange	
			switch (resource)
			{
				case WOOD:
				{
					if (maritimeOffer.getWood() >= 4)
					{
						validTrade = true;
						return validTrade;
					}
					else
					{
						break;
					}
				}
				case BRICK:
				{
					if (maritimeOffer.getBrick() >= 4)
					{
						validTrade = true;
						return validTrade;
					}
					else
					{
						break;
					}
				}
				case SHEEP:
				{
					if (maritimeOffer.getSheep() >= 4)
					{
						validTrade = true;
						return validTrade;
					}
					else
					{
						break;
					}
				}
				case WHEAT:
				{
					if (maritimeOffer.getWheat() >= 4)
					{
						validTrade = true;
						return validTrade;
					}
					else
					{
						break;
					}
				}
				case ORE:
				{
					if (maritimeOffer.getOre() >= 4)
					{
						validTrade = true;
						return validTrade;
					}
					else
					{
						break;
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
	public static boolean canBuyDevCard(Player player, DevCardList deck, TurnTracker turn){
		// current players turn
		if (turn.getCurrentTurn() == player.getPlayerIndex()){
			// correct phase of the game
			if (turn.getStatus() == TurnType.PLAYING){
				if (player.getResources().getSheep() >= 1 && player.getResources().getWheat() >= 1 && player.getResources().getOre() >= 1)
					if (deck.getTotal() > 0)
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
		// Make sure the player has the roads necessary to build with
		if (player.getRoads() >= 2){
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
					return true;
				}
			}
		}
		return false;
	}

	
	public static boolean hasAdjacentRoad(Player player, EdgeLocation edge, Map map, List<Building> newBuildings)
	{
		List<Road> newRoads = map.getRoads();
		//boolean hasCity = false;
		boolean hasRoad = false;
		
		if (edge.getDir() == EdgeDirection.South)
		{
			for (int i = 0; i < newRoads.size(); i++)
			{

				if (newRoads.get(i).getOwner() == player.getPlayerIndex())
				{
					if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					{
						hasRoad = true;
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() &&
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					{
						hasRoad = true;
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
					{
						hasRoad = true;
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() &&
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
					{
						hasRoad = true;
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					{
						hasRoad = true;
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					{
						hasRoad = true;
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
					{
						hasRoad = true;
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 &&
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
					{
						hasRoad = true;
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East))
										hasRoad = false;
						}
					}
				}
			}
		}
		else if (edge.getDir() == EdgeDirection.SouthEast)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getOwner() == player.getPlayerIndex())
				{
					if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() &&  
							newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast))
										hasRoad = false;
						}
						
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() &&
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast))
										hasRoad = false;
						}
						
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast))
										hasRoad = false;
						}
						
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest))
										hasRoad = false;
						}
					}
				}
			}
			
		}
		else if (edge.getDir() == EdgeDirection.SouthWest)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getOwner() == player.getPlayerIndex())
				{

					if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast))
									hasRoad = false;
						}
					}
					if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1&& 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest))
										hasRoad = false;
						}
					}
				}
			}
		}
		else if (edge.getDir() == EdgeDirection.NorthWest)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getOwner() == player.getPlayerIndex())
				{
					if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() + 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
									hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||

								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() + 1 &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
								
								(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
								newBuildings.get(j).getLocation().getY() == edge.getY() &&
								newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
									hasRoad = false;
						}
					}
				}
			}
		}
		else if (edge.getDir() == EdgeDirection.North)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getOwner() == player.getPlayerIndex())
				{
					if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() - 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
										hasRoad = false;
						}
					}
				}
			}
		}
		else if (edge.getDir() == EdgeDirection.NorthEast)
		{
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getOwner() == player.getPlayerIndex())
				{
					if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthEast) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.West) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthEast))
										hasRoad = false;
						}
					}
					if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() - 1 && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
					else if (newRoads.get(i).getLocation().getX() == edge.getX() + 1 && 
							newRoads.get(i).getLocation().getY() == edge.getY() && 
							newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
					{
						hasRoad = true; 
						for (int j = 0; j < newBuildings.size(); j++)
						{
							if ((newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.NorthWest) ||
	
									(newBuildings.get(j).getLocation().getX() == edge.getX() && 
									newBuildings.get(j).getLocation().getY() == edge.getY() &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.East) ||
									
									(newBuildings.get(j).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(j).getLocation().getY() == edge.getY() - 1 &&
									newBuildings.get(j).getLocation().getDirection() == VertexDirection.SouthWest))
										hasRoad = false;
						}
					}
				
				}
			}
		}
		
		return hasRoad;
	}
	
	public static boolean secondPhaseRoadCheck(List<Building> newBuildings, EdgeLocation edge)
	{
		if (edge.getDir().equals(EdgeDirection.North))
		{
			boolean oneSettlement = false;
			boolean twoSettlement = false;
			
			for(int i = 0; i < newBuildings.size(); i++)
			{
				// Checks if a road is built near a settlement
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() 
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					return false;
				
				//Checks if there is a settlement on one side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 2
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				//Checks if there is a settlement on the other side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX() 
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				// If there is one blocking both than it returns false
				if (oneSettlement && twoSettlement)
					return false;
			}
		}
		else if (edge.getDir().equals(EdgeDirection.NorthEast))
		{
			boolean oneSettlement = false;
			boolean twoSettlement = false;
			
			for(int i = 0; i < newBuildings.size(); i++)
			{
				// Checks if a road is built near a settlement
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					return false;
				
				//Checks if there is a settlement on one side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 2
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				//Checks if there is a settlement on the other side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 2
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				// If there is one blocking both than it returns false
				if (oneSettlement && twoSettlement)
					return false;
			}
		}
		else if (edge.getDir().equals(EdgeDirection.SouthEast))
		{
			boolean oneSettlement = false;
			boolean twoSettlement = false;
			
			for(int i = 0; i < newBuildings.size(); i++)
			{
				// Checks if a road is built near a settlement
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					return false;
				
				//Checks if there is a settlement on one side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				//Checks if there is a settlement on the other side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 2
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				// If there is one blocking both than it returns false
				if (oneSettlement && twoSettlement)
					return false;
			}
			
		}
		else if (edge.getDir().equals(EdgeDirection.South))
		{
			boolean oneSettlement = false;
			boolean twoSettlement = false;
			
			for(int i = 0; i < newBuildings.size(); i++)
			{
				// Checks if a road is built near a settlement
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() 
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					return false;
				
				//Checks if there is a settlement on one side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				//Checks if there is a settlement on the other side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 2
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				// If there is one blocking both than it returns false
				if (oneSettlement && twoSettlement)
					return false;
			}
		}
		else if (edge.getDir().equals(EdgeDirection.SouthWest))
		{
			boolean oneSettlement = false;
			boolean twoSettlement = false;
			
			for(int i = 0; i < newBuildings.size(); i++)
			{
				// Checks if a road is built near a settlement
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() 
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					return false;
				
				//Checks if there is a settlement on one side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX() - 2
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				//Checks if there is a settlement on the other side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 2
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				// If there is one blocking both than it returns false
				if (oneSettlement && twoSettlement)
					return false;
			}
		}
		else if (edge.getDir().equals(EdgeDirection.NorthWest))
		{
			boolean oneSettlement = false;
			boolean twoSettlement = false;
			
			for(int i = 0; i < newBuildings.size(); i++)
			{
				// Checks if a road is built near a settlement
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					return false;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() 
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					return false;
				
				//Checks if there is a settlement on one side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX() - 2
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.East))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthWest))
					oneSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() + 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthWest))
					oneSettlement = true;
				
				//Checks if there is a settlement on the other side which would prevent a build on that side
				if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() 
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.West))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY() - 1
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.SouthEast))
					twoSettlement = true;
				else if (newBuildings.get(i).getLocation().getX() == edge.getX()
						&& newBuildings.get(i).getLocation().getY() == edge.getY()
						&& newBuildings.get(i).getLocation().getDirection().equals(VertexDirection.NorthEast))
					twoSettlement = true;
				
				// If there is one blocking both than it returns false
				if (oneSettlement && twoSettlement)
					return false;
			}
		}

		return true;
	}
	
	public static boolean canBuildRoad(Player player, EdgeLocation edge, TurnTracker turn, Map map)
	{
		if (canBuyRoad(player, turn) || (turn.getStatus() == TurnType.FIRST_ROUND || turn.getStatus() == TurnType.SECOND_ROUND))
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
			
			List<Road> newRoads = map.getRoads();
			
			for (int i = 0; i < newRoads.size(); i++)
			{
				if (newRoads.get(i).getLocation().getDir() == edge.getDir() &&
						newRoads.get(i).getLocation().getX() == edge.getX() &&
						newRoads.get(i).getLocation().getY() == edge.getY())
					return false;
				else if (edge.getDir() == EdgeDirection.North)
				{
					if (newRoads.get(i).getLocation().getDir() == EdgeDirection.South &&
						newRoads.get(i).getLocation().getX() == edge.getX() &&
						newRoads.get(i).getLocation().getY() == edge.getY() - 1)
					return false;
				}
				else if (edge.getDir() == EdgeDirection.NorthEast)
				{
					if (newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest &&
						newRoads.get(i).getLocation().getX() == edge.getX() + 1 &&
						newRoads.get(i).getLocation().getY() == edge.getY() - 1)
					return false;
				}
				else if (edge.getDir() == EdgeDirection.NorthWest)
				{
					if (newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast &&
						newRoads.get(i).getLocation().getX() == edge.getX() - 1 &&
						newRoads.get(i).getLocation().getY() == edge.getY())
					return false;
				}
				else if (edge.getDir() == EdgeDirection.South)
				{
					if (newRoads.get(i).getLocation().getDir() == EdgeDirection.North &&
						newRoads.get(i).getLocation().getX() == edge.getX() &&
						newRoads.get(i).getLocation().getY() == edge.getY() + 1)
					return false;
				}
				else if (edge.getDir() == EdgeDirection.SouthEast)
				{
					if (newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest &&
						newRoads.get(i).getLocation().getX() == edge.getX() + 1 &&
						newRoads.get(i).getLocation().getY() == edge.getY())
					return false;
				}
				else if (edge.getDir() == EdgeDirection.SouthWest)
				{
					if (newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast &&
						newRoads.get(i).getLocation().getX() == edge.getX() - 1 &&
						newRoads.get(i).getLocation().getY() == edge.getY() + 1 )
					return false;
				}
				

			}

			//Water checks
			
			if(((edge.getX() == -3 || edge.getX() == 3) || edge.getY() == -3) && (edge.getDir().equals(EdgeDirection.North)))
				return false;
			else if(((edge.getX() == -1 && edge.getY() == -2) || 
					(edge.getX() == -2 && edge.getY() == -1)) 
					&& edge.getDir() == EdgeDirection.North)
				return false;
			else if(((edge.getX() == 1 && edge.getY() == 3) || 
					(edge.getX() == 2 && edge.getY() == 2)) 
					&& edge.getDir() == EdgeDirection.North)
				return false;
			else if((edge.getY() == -3 || edge.getX() == 3) && edge.getDir().equals(EdgeDirection.NorthEast))
				return false;
			else if(((edge.getX() == -1 && edge.getY() == -2) || 
					(edge.getX() == -2 && edge.getY() == -1) ||
					(edge.getX() == 0 && edge.getY() == 3) || 
					(edge.getX() == 1 && edge.getY() == 2) || 
					(edge.getX() == -3 && edge.getY() == 0) || 
					(edge.getX() == 2 && edge.getY() == 1))
					&& edge.getDir().equals(EdgeDirection.NorthEast))
				return false;
			else if((Math.abs(edge.getY()) >= 4 || Math.abs(edge.getX()) >= 4))
				return false;
			else if((edge.getY() == -3 || edge.getX() == -3 || edge.getY() == 3) && edge.getDir().equals(EdgeDirection.NorthWest))
				return false;
			else if(((edge.getX() == 2 && edge.getY() == 2) || 
					(edge.getX() == 3 && edge.getY() == 1)) 
					&& edge.getDir() == EdgeDirection.NorthWest)
				return false;
			else if(((edge.getX() == -2 && edge.getY() == -1) || 
					(edge.getX() == -1 && edge.getY() == -2)) 
					&& edge.getDir() == EdgeDirection.NorthWest)
				return false;
			
			if (turn.getStatus() == TurnType.FIRST_ROUND)
				return true;
			else if (turn.getStatus() == TurnType.SECOND_ROUND)
				return secondPhaseRoadCheck(newBuildings, edge);
			
			
			
			if (!hasAdjacentRoad(player,edge, map, newBuildings))
			{
				if (edge.getDir() == EdgeDirection.South)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
						{
							if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() + 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() + 1&& 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
								return true;
						}
					}
				}
				else if (edge.getDir() == EdgeDirection.SouthEast)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
						{
							if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() + 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
								return true;
						}
					}		
				}
				else if (edge.getDir() == EdgeDirection.SouthWest)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
						{
							if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() + 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() + 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() + 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
								return true;
						}
					}			
				}
				else if (edge.getDir() == EdgeDirection.NorthWest)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
						{
							if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() + 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
								return true;
						}
					}			
				}
				else if (edge.getDir() == EdgeDirection.North)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
						{
							if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() - 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
								return true;
						}
					}			
				}
				else if (edge.getDir() == EdgeDirection.NorthEast)
				{
					for (int i = 0; i < newBuildings.size(); i++)
					{
						if (newBuildings.get(i).getOwner() == player.getPlayerIndex())
						{
							if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() - 1 && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() + 1 && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
								return true;
							else if (newBuildings.get(i).getLocation().getX() == edge.getX() && 
									newBuildings.get(i).getLocation().getY() == edge.getY() && 
									newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
								return true;
						}
					}			
				}
			}
			else 
				return true;
			
		}

		return false;
		
		// Needs a settlement or road adjacent
	}
	
	public static boolean canBuildSettlement(Player player, VertexLocation vertexLocation, TurnTracker turn, Map map)
	{
		Boolean hasRoad = false;
		
		if (canBuySettlement(player, turn) || (turn.getStatus() == TurnType.FIRST_ROUND || turn.getStatus() == TurnType.SECOND_ROUND))
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
			
			List<Road> newRoads = new ArrayList<Road>();
			if (map.getRoads() != null && !map.getRoads().isEmpty())
				newRoads = map.getRoads();
			
			if(vertexLocation.getDirection() == VertexDirection.NorthEast)
			{
				for(int i = 0; i < newBuildings.size(); i++)
				{
					if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() + 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() + 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() + 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 2 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() + 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() + 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
						return false;
				}
				

				for(int i = 0; i < newRoads.size(); i++)
				{
					if (newRoads.get(i).getOwner() == player.getPlayerIndex())
					{
						if (newRoads.get(i).getLocation().getX()== vertexLocation.getX() + 1 && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() - 1 && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() - 1 && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() - 1 && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX()== vertexLocation.getX() + 1 && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() - 1 && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
							hasRoad = true;
					}
				}
			}
			else if (vertexLocation.getDirection() == VertexDirection.NorthWest)
			{
				for(int i = 0; i < newBuildings.size(); i++)
				{
					if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newBuildings.get(i).getLocation().getY()== vertexLocation.getY() + 1  && newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthEast)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() + 1 && newBuildings.get(i).getLocation().getY()== vertexLocation.getY() - 1  && newBuildings.get(i).getLocation().getDirection() == VertexDirection.West)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newBuildings.get(i).getLocation().getY()== vertexLocation.getY() - 1  && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthEast)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.NorthWest)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() - 1 && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() && newBuildings.get(i).getLocation().getDirection() == VertexDirection.East)
						return false;
					else if (newBuildings.get(i).getLocation().getX() == vertexLocation.getX() && newBuildings.get(i).getLocation().getY() == vertexLocation.getY() - 1 && newBuildings.get(i).getLocation().getDirection() == VertexDirection.SouthWest)
						return false;
				}
				
				for(int i = 0; i < newRoads.size(); i++)
				{
					if (newRoads.get(i).getOwner() == player.getPlayerIndex())
					{
						if (newRoads.get(i).getLocation().getX()== vertexLocation.getX() && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() - 1 && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthWest)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() - 1 && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.South)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() - 1 && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.SouthEast)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX()== vertexLocation.getX() + 1 && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() - 1 && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthWest)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() && 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.North)
							hasRoad = true;
						else if (newRoads.get(i).getLocation().getX() == vertexLocation.getX() - 1&& 
								newRoads.get(i).getLocation().getY() == vertexLocation.getY() && 
								newRoads.get(i).getLocation().getDir() == EdgeDirection.NorthEast)
							hasRoad = true;
					}
				}
			}
		}
	
		return hasRoad;

		// Check Phases setup phase doesn't need adjacent road
		// check if has adjacent road
	}
	
	public static boolean canBuildCity(Player player, VertexLocation vertexLocation, TurnTracker turn, Map map)
	{
		if (canBuyCity(player, turn))
		{
			List<Building> newSettlements = map.getSettlements();
			for (int i = 0; i < newSettlements.size(); i++)
			{
				if (newSettlements.get(i).getOwner() == player.getPlayerIndex())
				{
					if (vertexLocation.getDirection() == VertexDirection.NorthEast)
					{
						if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.NorthEast && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.West && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() + 1 && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY() - 1)
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.SouthEast && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY() - 1)
							return true;
					}
					else if (vertexLocation.getDirection() == VertexDirection.East)
					{
						if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.East && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.SouthWest && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() + 1 && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY() - 1)
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.NorthWest && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() + 1 && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
					}
					else if (vertexLocation.getDirection() == VertexDirection.SouthEast)
					{
						if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.SouthEast && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.West && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() + 1 && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.NorthEast && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY() + 1)
							return true;
					}
					else if (vertexLocation.getDirection() == VertexDirection.NorthWest)
					{
						if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.NorthWest && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.East && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() - 1 && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.SouthWest && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY() - 1)
							return true;
					}
					else if (vertexLocation.getDirection() == VertexDirection.West)
					{
						if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.West && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.SouthEast && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() - 1 && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.NorthEast && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() - 1 && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY() + 1)
							return true;
					}
					else if (vertexLocation.getDirection() == VertexDirection.SouthWest)
					{
						if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.SouthWest && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY())
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.East && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() - 1 && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY() + 1)
							return true;
						else if (newSettlements.get(i).getLocation().getDirection() == VertexDirection.NorthWest && 
								newSettlements.get(i).getLocation().getX() == vertexLocation.getX() && 
								newSettlements.get(i).getLocation().getY() == vertexLocation.getY() + 1)
							return true;
					}
				}
			}
		}
		
		return false;
	}

	// Need checks here to see if player can buy a road
	public static boolean canBuyRoad(Player player, TurnTracker turn) {
		if ((turn.getCurrentTurn() == player.getPlayerIndex() && player.getRoads() >= 1 && /* player.getRoads() <= 14 && */ player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1)){
			return true;
		}
		if (player.getIsRoadBuilding()){
//			System.out.println("Player can buy road because road building");
			return true;
		}
		return false;
	}
	
	// Need checks here to see if player can buy a settlement
	public static boolean canBuySettlement(Player player, TurnTracker turn) {
		if ((turn.getCurrentTurn() == player.getPlayerIndex() && /* player.getSettlements() <= 4 && */ player.getSettlements() >= 1 && player.getResources().getBrick() >= 1 && player.getResources().getWood() >= 1 && player.getResources().getWheat() >= 1 && player.getResources().getSheep() >= 1) || turn.equals(TurnType.FIRST_ROUND)|| turn.equals(TurnType.SECOND_ROUND)){
			return true;
		}
		return false;
	}
	
	// Need checks here to see if player can buy a city
	public static boolean canBuyCity(Player player, TurnTracker turn) {
		if (turn.getCurrentTurn() == player.getPlayerIndex() && /* player.getSettlements() <= 4 && */ player.getCities() >= 1 && player.getResources().getWheat() >= 2 && player.getResources().getOre() >= 3){
			return true;
		}
		return false;
	}
	
	
	
	public static boolean canPlaceRobber(Hex location, Robber robber, TurnTracker turn, Player player){
		if (turn.getStatus() == TurnType.ROBBING || player.getIsPlayingSoldier()){
			if ((robber == null || location == null) || robber.getX() == location.getLocation().getX() &&
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
