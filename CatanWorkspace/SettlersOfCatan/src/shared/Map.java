package shared;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import client.view.data.RobPlayerInfo;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.Robber;

public class Map extends InvisObservable
{
	private List<Hex> hexes = new ArrayList<Hex>();
	private List<Port> ports = new ArrayList<Port>();
	private List<Road> roads = new ArrayList<Road>();
	private List<Building> settlements = new ArrayList<Building>();
	private List<Building> cities = new ArrayList<Building>();
	private Integer radius = 3;
	private Robber robber = new Robber();
	private transient boolean isBuilding = false;
	
	public void reset()
	{
		roads.clear();
		settlements.clear();
		cities.clear();
		
		for (int i = 0; i < 19; i++)
		{
			if (hexes.get(i).getResource() == HexType.DESERT)
			{
				hexes.get(i).giveRobber();
				robber.setLocation(hexes.get(i).getLocation().getX(), hexes.get(i).getLocation().getY());
			}
		}
		
		isBuilding = false;
	}
	
	public Map()
	{
		ArrayList<HexType> boardHexTypes = new ArrayList<HexType>();
	
		boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.ORE); 	boardHexTypes.add(HexType.WHEAT); 	
		boardHexTypes.add(HexType.WHEAT); 	boardHexTypes.add(HexType.SHEEP); 	boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.SHEEP); 	
		boardHexTypes.add(HexType.ORE);   	boardHexTypes.add(HexType.SHEEP); 	boardHexTypes.add(HexType.WHEAT); 	boardHexTypes.add(HexType.BRICK); 	boardHexTypes.add(HexType.WHEAT); 
		boardHexTypes.add(HexType.BRICK); 	boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.ORE);		boardHexTypes.add(HexType.SHEEP); 
		boardHexTypes.add(HexType.DESERT);  boardHexTypes.add(HexType.BRICK); 	boardHexTypes.add(HexType.WOOD); 	
		
		
		HexLocation[] boardHexLocations = 
		{
			new HexLocation(-2, 2), 	new HexLocation(-1, 2), 	new HexLocation( 0, 2),
			new HexLocation(-2, 1), 	new HexLocation(-1, 1), 	new HexLocation( 0, 1), 	new HexLocation(1, 1),
			new HexLocation(-2, 0), 	new HexLocation(-1, 0), 	new HexLocation( 0, 0), 	new HexLocation(1, 0), new HexLocation(2, 0), 
			new HexLocation(-1,-1), 	new HexLocation( 0,-1), 	new HexLocation( 1,-1), 	new HexLocation(2, -1), 
			new HexLocation(0, -2), 	new HexLocation( 1,-2), 	new HexLocation(2, -2)
		};
		
		ArrayList<Integer> boardHexNumbers = new ArrayList<Integer>();
		boardHexNumbers.add(6); boardHexNumbers.add(3); boardHexNumbers.add(8); 
		boardHexNumbers.add(2);	boardHexNumbers.add(9); boardHexNumbers.add(4);  boardHexNumbers.add(10); 
		boardHexNumbers.add(5); boardHexNumbers.add(10); boardHexNumbers.add(11); boardHexNumbers.add(5);	boardHexNumbers.add(6);
		boardHexNumbers.add(8); boardHexNumbers.add(3); boardHexNumbers.add(9); boardHexNumbers.add(12); 
		boardHexNumbers.add(0); boardHexNumbers.add(4);  boardHexNumbers.add(11);
		
		for (int i = 0; i < 19; i++)
		{
			hexes.add(new Hex(boardHexLocations[i], boardHexTypes.get(i), boardHexNumbers.get(i)));
			if (hexes.get(i).getResource() == HexType.DESERT)
			{
				hexes.get(i).giveRobber();
				robber.setLocation(hexes.get(i).getLocation().getX(), hexes.get(i).getLocation().getY());
			}
		}
		
		ports.add(new Port(PortType.ORE, new HexLocation(1, -3), EdgeDirection.South, radius));
		ports.add(new Port(PortType.WHEAT, new HexLocation(-1, -2), EdgeDirection.South, radius));
		ports.add(new Port(PortType.THREE, new HexLocation(-3, 0), EdgeDirection.SouthEast, radius));
		ports.add(new Port(PortType.WOOD, new HexLocation(-2, 1), EdgeDirection.SouthWest, radius));
		ports.add(new Port(PortType.BRICK, new HexLocation(-1, 2), EdgeDirection.SouthWest, radius));
		ports.add(new Port(PortType.THREE, new HexLocation(0, 2), EdgeDirection.South, radius));
		ports.add(new Port(PortType.THREE, new HexLocation(2, -1), EdgeDirection.SouthEast, radius));
		ports.add(new Port(PortType.SHEEP, new HexLocation(2, -3), EdgeDirection.NorthWest, radius));
		ports.add(new Port(PortType.THREE, new HexLocation(3, -3), EdgeDirection.SouthWest, radius));
	} 
	
	public Map(boolean portsRandom, boolean numberRandom, boolean hexRandom)
	{
		
		ArrayList<HexType> boardHexTypes = new ArrayList<HexType>();
	
		boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.ORE); 	boardHexTypes.add(HexType.WHEAT); 	
		boardHexTypes.add(HexType.WHEAT); 	boardHexTypes.add(HexType.SHEEP); 	boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.SHEEP); 	
		boardHexTypes.add(HexType.ORE);   	boardHexTypes.add(HexType.SHEEP); 	boardHexTypes.add(HexType.WHEAT); 	boardHexTypes.add(HexType.BRICK); 	boardHexTypes.add(HexType.WHEAT); 
		boardHexTypes.add(HexType.BRICK); 	boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.ORE);		boardHexTypes.add(HexType.SHEEP); 
		boardHexTypes.add(HexType.DESERT);  boardHexTypes.add(HexType.BRICK); 	boardHexTypes.add(HexType.WOOD); 	
		
		HexLocation[] boardHexLocations = 
		{
				new HexLocation(-2, 2), 	new HexLocation(-1, 2), 	new HexLocation( 0, 2),
				new HexLocation(-2, 1), 	new HexLocation(-1, 1), 	new HexLocation( 0, 1), 	new HexLocation(1, 1),
				new HexLocation(-2, 0), 	new HexLocation(-1, 0), 	new HexLocation( 0, 0), 	new HexLocation(1, 0), new HexLocation(2, 0), 
				new HexLocation(-1,-1), 	new HexLocation( 0,-1), 	new HexLocation( 1,-1), 	new HexLocation(2, -1), 
				new HexLocation(0, -2), 	new HexLocation( 1,-2), 	new HexLocation(2, -2)
		};
		
		ArrayList<Integer> boardHexNumbers = new ArrayList<Integer>();
		boardHexNumbers.add(6); boardHexNumbers.add(3); boardHexNumbers.add(8); 
		boardHexNumbers.add(2);	boardHexNumbers.add(9); boardHexNumbers.add(4);  boardHexNumbers.add(10); 
		boardHexNumbers.add(5); boardHexNumbers.add(10); boardHexNumbers.add(11); boardHexNumbers.add(5);	boardHexNumbers.add(6);
		boardHexNumbers.add(8); boardHexNumbers.add(3); boardHexNumbers.add(9); boardHexNumbers.add(12); 
		boardHexNumbers.add(0); boardHexNumbers.add(4);  boardHexNumbers.add(11);
		
		if (numberRandom)
			Collections.shuffle(boardHexNumbers);
		
		for (int i = 0; i < 19; i++)
		{
			hexes.add(new Hex(boardHexLocations[i], boardHexTypes.get(i), boardHexNumbers.get(i)));
			if (hexes.get(i).getResource() == HexType.DESERT)
			{
				hexes.get(i).giveRobber();
				robber.setLocation(hexes.get(i).getLocation().getX(), hexes.get(i).getLocation().getY());
			}
		}
		
		if (hexRandom)
		{
			Collections.shuffle(boardHexTypes);
			Hex desert = null; 
			
			for(Hex hex : hexes)
			{
				if (hex.getResource() == HexType.DESERT && hex.getNumber() != 0)
					desert = hex;
			}
			
			for(Hex hex : hexes)
			{
				if (hex.getNumber() == null && hex.getResource() != HexType.DESERT)
				{
					hex.setNumber(desert.getNumber());
					desert.setNumber(0);
				}
			}
		}
		
		if (portsRandom)
		{
			ArrayList<Port> portsType = new ArrayList<Port>();
			portsType.add(new Port(PortType.THREE, null, null, 3));
			portsType.add(new Port(PortType.THREE, null, null, 3));
			portsType.add(new Port(PortType.THREE, null, null, 3));
			portsType.add(new Port(PortType.THREE, null, null, 3));
			portsType.add(new Port(PortType.SHEEP, null, null, 2));
			portsType.add(new Port(PortType.ORE,   null, null, 2));
			portsType.add(new Port(PortType.WHEAT, null, null, 2));
			portsType.add(new Port(PortType.WOOD,  null, null, 2));
			portsType.add(new Port(PortType.BRICK, null, null, 2));
			Collections.shuffle(portsType);
			
			ArrayList<Port> portsLocation = new ArrayList<Port>();
			
			portsLocation.add(new Port(null, new HexLocation(1, -3), EdgeDirection.SouthWest, radius));
			portsLocation.add(new Port(null, new HexLocation(2, -3), EdgeDirection.SouthWest, radius));
			portsLocation.add(new Port(null, new HexLocation(3, -3), EdgeDirection.SouthWest, radius));
			portsLocation.add(new Port(null, new HexLocation(3, -2), EdgeDirection.SouthWest, radius));
			portsLocation.add(new Port(null, new HexLocation(3, -1), EdgeDirection.SouthWest, radius));
			
			portsLocation.add(new Port(null, new HexLocation(0, -3), EdgeDirection.South, radius));
			portsLocation.add(new Port(null, new HexLocation(-1,-2), EdgeDirection.South, radius));
			portsLocation.add(new Port(null, new HexLocation(-2,-1), EdgeDirection.South, radius));
			portsLocation.add(new Port(null, new HexLocation(1, -3), EdgeDirection.South, radius));
			portsLocation.add(new Port(null, new HexLocation(2, -3), EdgeDirection.South, radius));
			
			portsLocation.add(new Port(null, new HexLocation(-1,-2), EdgeDirection.SouthEast, radius));
			portsLocation.add(new Port(null, new HexLocation(-2,-1), EdgeDirection.SouthEast, radius));
			portsLocation.add(new Port(null, new HexLocation(-3, 0), EdgeDirection.SouthEast, radius));
			portsLocation.add(new Port(null, new HexLocation(-3, 1), EdgeDirection.SouthEast, radius));
			portsLocation.add(new Port(null, new HexLocation(-3, 2), EdgeDirection.SouthEast, radius));
			
			portsLocation.add(new Port(null, new HexLocation(-3, 1), EdgeDirection.NorthEast, radius));
			portsLocation.add(new Port(null, new HexLocation(-3, 2), EdgeDirection.NorthEast, radius));
			portsLocation.add(new Port(null, new HexLocation(-3, 3), EdgeDirection.NorthEast, radius));
			portsLocation.add(new Port(null, new HexLocation(-2, 3), EdgeDirection.NorthEast, radius));
			portsLocation.add(new Port(null, new HexLocation(-1, 3), EdgeDirection.NorthEast, radius));
			
			portsLocation.add(new Port(null, new HexLocation(-2, 3), EdgeDirection.North, radius));
			portsLocation.add(new Port(null, new HexLocation(-1, 3), EdgeDirection.North, radius));
			portsLocation.add(new Port(null, new HexLocation(0,  3), EdgeDirection.North, radius));
			portsLocation.add(new Port(null, new HexLocation(1,  2), EdgeDirection.North, radius));
			portsLocation.add(new Port(null, new HexLocation(2,  1), EdgeDirection.North, radius));
			
			portsLocation.add(new Port(null, new HexLocation(1,  2), EdgeDirection.NorthWest, radius));
			portsLocation.add(new Port(null, new HexLocation(2,  1), EdgeDirection.NorthWest, radius));
			portsLocation.add(new Port(null, new HexLocation(3,  0), EdgeDirection.NorthWest, radius));
			portsLocation.add(new Port(null, new HexLocation(3, -1), EdgeDirection.NorthWest, radius));
			portsLocation.add(new Port(null, new HexLocation(3, -2), EdgeDirection.NorthWest, radius));
			
			Collections.shuffle(portsLocation);
			
			for (int i = 0; i < 9; i++)
				ports.add(new Port(portsType.get(i).getType(), portsLocation.get(i).getLocation(), portsLocation.get(i).getDirection(), portsType.get(i).getRatio()));
		}
		else
		{
			ports.add(new Port(PortType.ORE, new HexLocation(1, -3), EdgeDirection.South, radius));
			ports.add(new Port(PortType.WHEAT, new HexLocation(-1, -2), EdgeDirection.South, radius));
			ports.add(new Port(PortType.THREE, new HexLocation(-3, 0), EdgeDirection.SouthEast, radius));
			ports.add(new Port(PortType.WOOD, new HexLocation(-2, 1), EdgeDirection.SouthWest, radius));
			ports.add(new Port(PortType.BRICK, new HexLocation(-1, 2), EdgeDirection.SouthWest, radius));
			ports.add(new Port(PortType.THREE, new HexLocation(0, 2), EdgeDirection.South, radius));
			ports.add(new Port(PortType.THREE, new HexLocation(2, -1), EdgeDirection.SouthEast, radius));
			ports.add(new Port(PortType.SHEEP, new HexLocation(2, -3), EdgeDirection.NorthWest, radius));
			ports.add(new Port(PortType.THREE, new HexLocation(3, -3), EdgeDirection.SouthWest, radius));
		}
			
	} 
	
	
	/*
	 * When a 7 is rolled this method is called and allows the 
	 * user to place the robber on any Hex on the map
	 * 
	 * @Param Hex location
	 * 
	 * @Return void
	 */
	public void moveRobber(HexLocation hexLocation)
	{
		 for(int i = 0; i < 19; i++)
			 if (getHexes().get(i).hasRobber())
				 getHexes().get(i).takeRobber();
			 else if (getHexes().get(i).getLocation() == hexLocation)
			 {
				 getHexes().get(i).giveRobber();
				 robber.setLocation(hexLocation.getX(), hexLocation.getY());
			 }
	}
	
	/*
	 * Build the Settlement at the designated location
	 * 
	 * @Param EdgeLocation location
	 * 
	 * @Return void
	 */
	public void buildSettlement(int player, VertexLocation location)
	{
		 getSettlements().add(new Building(player, location));
	}
	 
	 
	/*
	 * Build the City at the designated location
	 * 
	 * @Param EdgeLocation location
	 * 
	 * @Return void
	 */
	public void buildCity(int player, VertexLocation location)
	{
		Building city = new Building(player, location);
		getSettlements().remove(getSettlements().indexOf(city));
		getCities().add(city);
	}
	/*
	 * Build the road at the designated location
	 * 
	 * @Param EdgeLocation location
	 * 
	 * @Return void
	 */
	public void buildRoad(int player, EdgeLocation location)
	{
		 getRoads().add(new Road(player, location));
	}
	 
	/*
	 * When a robber is placed look to see which player are build on the Hex, making them robbable.
	 * 
	 * @Param HexLocation location
	 * 
	 * @Return Array of Robbable Players
	 */
	public List<Integer> getRobbable()
	{
		List<Integer> players = new ArrayList<Integer>();

		HexLocation robberHex = new HexLocation(robber.getX(), robber.getY());
		
		for (int i = 0; i < cities.size(); i++)
			if ((cities.get(i).getLocation().getX() == robberHex.getX() + 1) && 
				(cities.get(i).getLocation().getY() == robberHex.getY()) &&
			    ((cities.get(i).getLocation().getDirection() == VertexDirection.East) || 
				(cities.get(i).getLocation().getDirection() == VertexDirection.SouthEast)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getX() == robberHex.getX()) && 
					 (cities.get(i).getLocation().getY() == robberHex.getY() + 1) &&
					 ((cities.get(i).getLocation().getDirection() == VertexDirection.SouthEast) || 
					 (cities.get(i).getLocation().getDirection() == VertexDirection.SouthWest)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getX() == robberHex.getX() - 1) && 
					 (cities.get(i).getLocation().getY() == robberHex.getY() + 1) &&
					 ((cities.get(i).getLocation().getDirection() == VertexDirection.East) || 
					 (cities.get(i).getLocation().getDirection() == VertexDirection.SouthEast)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getX() == robberHex.getX() - 1) && 
					 (cities.get(i).getLocation().getY() == robberHex.getY()) &&
					 ((cities.get(i).getLocation().getDirection() == VertexDirection.East) || 
					 (cities.get(i).getLocation().getDirection() == VertexDirection.NorthEast)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getX() == robberHex.getX()) && 
					 (cities.get(i).getLocation().getY() == robberHex.getY() - 1) &&
					 ((cities.get(i).getLocation().getDirection() == VertexDirection.NorthWest) || 
					 (cities.get(i).getLocation().getDirection() == VertexDirection.NorthEast)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getX() == robberHex.getX() + 1) && 
					 (cities.get(i).getLocation().getY() == robberHex.getY() - 1) &&
					 ((cities.get(i).getLocation().getDirection() == VertexDirection.NorthWest) || 
					 (cities.get(i).getLocation().getDirection() == VertexDirection.West)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getX() == robberHex.getX()) && 
					 (cities.get(i).getLocation().getY() == robberHex.getY()) &&
					 ((cities.get(i).getLocation().getDirection() == VertexDirection.NorthWest) || 
					 (cities.get(i).getLocation().getDirection() == VertexDirection.West) ||
					 (cities.get(i).getLocation().getDirection() == VertexDirection.SouthWest) ||
					 (cities.get(i).getLocation().getDirection() == VertexDirection.SouthEast) ||
					 (cities.get(i).getLocation().getDirection() == VertexDirection.East) ||
					 (cities.get(i).getLocation().getDirection() == VertexDirection.NorthEast)))
				players.add(cities.get(i).getOwner());
		
		for (int i = 0; i < settlements.size(); i++)
			if ((settlements.get(i).getLocation().getX() == robberHex.getX() + 1) && 
				(settlements.get(i).getLocation().getY() == robberHex.getY()) &&
			    ((settlements.get(i).getLocation().getDirection() == VertexDirection.East) || 
				(settlements.get(i).getLocation().getDirection() == VertexDirection.SouthEast)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getX() == robberHex.getX()) && 
					 (settlements.get(i).getLocation().getY() == robberHex.getY() + 1) &&
					 ((settlements.get(i).getLocation().getDirection() == VertexDirection.SouthEast) || 
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.SouthWest)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getX() == robberHex.getX() - 1) && 
					 (settlements.get(i).getLocation().getY() == robberHex.getY() + 1) &&
					 ((settlements.get(i).getLocation().getDirection() == VertexDirection.East) || 
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.SouthEast)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getX() == robberHex.getX() - 1) && 
					 (settlements.get(i).getLocation().getY() == robberHex.getY()) &&
					 ((settlements.get(i).getLocation().getDirection() == VertexDirection.East) || 
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.NorthEast)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getX() == robberHex.getX()) && 
					 (settlements.get(i).getLocation().getY() == robberHex.getY() - 1) &&
					 ((settlements.get(i).getLocation().getDirection() == VertexDirection.NorthWest) || 
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.NorthEast)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getX() == robberHex.getX() + 1) && 
					 (settlements.get(i).getLocation().getY() == robberHex.getY() - 1) &&
					 ((settlements.get(i).getLocation().getDirection() == VertexDirection.NorthWest) || 
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.West)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getX() == robberHex.getX()) && 
					 (settlements.get(i).getLocation().getY() == robberHex.getY()) &&
					 ((settlements.get(i).getLocation().getDirection() == VertexDirection.NorthWest) || 
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.West) ||
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.SouthWest) ||
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.SouthEast) ||
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.East) ||
					 (settlements.get(i).getLocation().getDirection() == VertexDirection.NorthEast)))
				players.add(settlements.get(i).getOwner());
		
		return players;
	}

	public List<Hex> getHexes() {

		return hexes;
	}

	public void setHexes(List<Hex> hexes) {
		this.hexes = hexes;
	}

	public List<Building> getSettlements() {
		return settlements;
	}

	public void setSettlements(List<Building> settlements) {
		this.settlements = settlements;
	}

	public List<Building> getCities() {
		return cities;
	}

	public void setCities(List<Building> cities) {
		this.cities = cities;
	}

	public List<Road> getRoads() {
		return roads;
	}

	public void setRoads(List<Road> roads) {
		this.roads = roads;
	}
	
	public void setRobber(HexLocation hexloc){
		robber.setLocation(hexloc.getX(), hexloc.getY());
		
	}
	
	public Robber getRobber(){
		return robber;
	}

	public List<Port> getPorts() {
		return ports;
	}

	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}
	
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("radius : ").append(radius).append(",\n");
		
		string.append("robber : ").append(robber.toString()).append(",\n");
		
		string.append("hexes : [\n");
		for (Hex h : hexes) {
			string.append(h.toString()).append(",\n");
		}
		string.append("],\n");
		
		string.append("roads : [\n");
		for(Road r : roads) {
			string.append(r.toString()).append(",\n");
		}
		string.append("],\n");
		
		string.append("cities : [\n");
		for(Building c : cities) {
			string.append(c.toString()).append(",\n");
		}
		string.append("],\n");
		
		string.append("settlements : [\n");
		for(Building s : settlements) {
			string.append(s.toString()).append(",\n");
		}
		string.append("],\n");
		
		string.append("ports : [\n");
		for(Port p : ports) {
			string.append(p.toString()).append(",\n");
		}
		string.append("]\n");
		
		string.append("}");
		
		return string.toString();
	}

	public boolean updateFrom(Map rhs)
	{
		boolean updated = false;

		updated = updated | robber.updateFrom(rhs.robber);
		
		if (radius != rhs.radius)
		{
			radius = rhs.radius;
			updated = true;
		}
		
		if (isBuilding != rhs.isBuilding)
		{
			isBuilding = rhs.isBuilding;
			updated = true;
		}
		
		// Update hexes
		for (Hex other : rhs.hexes)
		{
			boolean hasHex = false;
			for (Hex hex : hexes)
			{
				if (hex.getLocation().equals(other.getLocation()))
				{
					updated = updated | hex.updateFrom(other);
					hasHex = true;
					break;
				}
			}
			if (!hasHex)
			{
				hexes.add(new Hex(other.getLocation(), other.getResource(), other.getNumber()));
			}
		}
		
		// Update ports
		for (Port other : rhs.ports)
		{
			boolean hasPort = false;
			for (Port port : ports)
			{
				if (port.getLocation().equals(other.getLocation()))
				{
					updated = updated | port.updateFrom(other);
					hasPort = true;
					break;
				}
			}
			if (!hasPort)
			{
				ports.add(new Port(other.getType(), other.getLocation(), other.getDirection(), other.getRatio()));
			}
		}

		// Update roads
		for (Road other : rhs.roads)
		{
			boolean hasRoad = false;
			for (Road road : roads)
			{
				if (road.getLocation().equals(other.getLocation()))
				{
					updated = updated | road.updateFrom(other);
					hasRoad = true;
					break;
				}
			}
			if (!hasRoad)
			{
				roads.add(new Road(other.getOwner(), other.getLocation()));
			}
		}

		// Update settlements
		for (Building other : rhs.settlements)
		{
			boolean hasSettlement = false;
			for (Building settlement : settlements)
			{
				if (settlement.getLocation().equals(other.getLocation()))
				{
					updated = updated | settlement.updateFrom(other);
					hasSettlement = true;
					break;
				}
			}
			if (!hasSettlement)
			{
				settlements.add(new Building(other.getOwner(), other.getLocation()));
			}
		}

		// Update cities
		for (Building other : rhs.cities)
		{
			boolean hasCity = false;
			for (Building city : cities)
			{
				if (city.getLocation().equals(other.getLocation()))
				{
					updated = updated | city.updateFrom(other);
					hasCity = true;
					break;
				}
			}
			if (!hasCity)
			{
				cities.add(new Building(other.getOwner(), other.getLocation()));
			}
		}
		
		if (updated)
		{
			setChanged();
			notifyObservers();
		}
		
		return updated;
	}
}
