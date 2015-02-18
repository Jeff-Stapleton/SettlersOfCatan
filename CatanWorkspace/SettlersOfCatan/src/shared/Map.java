package shared;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.Robber;

public class Map 
{
	private Hex[] hexes;
	private List<Port> ports;
	private List<Road> roads;
	private List<Building> settlements;
	private List<Building> cities;
	private Integer radius;
	private Robber robber;	
	
	/*public Map()
	{
		
		ArrayList<HexType> boardHexTypes = new ArrayList<HexType>();
	
		boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.WOOD); 	boardHexTypes.add(HexType.WOOD); 
		boardHexTypes.add(HexType.SHEEP); 	boardHexTypes.add(HexType.SHEEP); 	boardHexTypes.add(HexType.SHEEP); 	boardHexTypes.add(HexType.SHEEP); 
		boardHexTypes.add(HexType.WHEAT); 	boardHexTypes.add(HexType.WHEAT); 	boardHexTypes.add(HexType.WHEAT); 	boardHexTypes.add(HexType.WHEAT); 
		boardHexTypes.add(HexType.BRICK); 	boardHexTypes.add(HexType.BRICK); 	boardHexTypes.add(HexType.BRICK);
		boardHexTypes.add(HexType.ORE); 	boardHexTypes.add(HexType.ORE); 	boardHexTypes.add(HexType.ORE); 
		boardHexTypes.add(HexType.DESERT); 
		
		HexLocation[] boardHexLocations = 
		{
			new HexLocation(-2, 2), 	new HexLocation(-1, 2), 	new HexLocation( 0, 2),
			new HexLocation(-2, 1), 	new HexLocation(-1, 1), 	new HexLocation( 0, 1), 	new HexLocation(1, 1),
			new HexLocation(-2, 0), 	new HexLocation(-1, 0), 	new HexLocation( 0, 0), 	new HexLocation(1, 0), new HexLocation(2, 0), 
			new HexLocation(-1,-1), 	new HexLocation( 0,-1), 	new HexLocation( 1,-1), 	new HexLocation(2, -1), 
			new HexLocation(0, -2), 	new HexLocation( 1,-2), 	new HexLocation(2, -2)
		};
		
		ArrayList<Integer> boardHexNumbers = new ArrayList<Integer>();
		boardHexNumbers.add(5); boardHexNumbers.add(10); boardHexNumbers.add(8); boardHexNumbers.add(6);
		boardHexNumbers.add(2); boardHexNumbers.add(9);  boardHexNumbers.add(10); boardHexNumbers.add(3);
		boardHexNumbers.add(6); boardHexNumbers.add(12); boardHexNumbers.add(9); boardHexNumbers.add(11);
		boardHexNumbers.add(3); boardHexNumbers.add(11); boardHexNumbers.add(4); 
		boardHexNumbers.add(8); boardHexNumbers.add(4);  boardHexNumbers.add(5);
		
		Collections.sort(boardHexNumbers);
		Collections.shuffle(Arrays.asList(boardHexTypes));
		boardHexNumbers.add(boardHexTypes.indexOf(HexType.DESERT), 0);
		
		for (int i = 0; i < 19; i++)
		{
			hexes[i] = new Hex(boardHexLocations[i], boardHexTypes.get(i), boardHexNumbers.get(i));
			if (hexes[i].getType() == HexType.DESERT)
			{
				hexes[i].giveRobber();
				robber.setLocation(hexes[i].getLocation());
			}
		}
		
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
		Collections.shuffle(Arrays.asList(portsType));
		
		ArrayList<Port> portsLocation = new ArrayList<Port>();
		portsLocation.add(new Port(null, new HexLocation(3, 0), EdgeDirection.SouthWest, radius));
		portsLocation.add(new Port(null, new HexLocation(1, 2), EdgeDirection.South, radius));
		portsLocation.add(new Port(null, new HexLocation(-1, 3), EdgeDirection.South, radius));
		portsLocation.add(new Port(null, new HexLocation(-3, 3), EdgeDirection.SouthEast, radius));
		portsLocation.add(new Port(null, new HexLocation(-3, 1), EdgeDirection.NorthEast, radius));
		portsLocation.add(new Port(null, new HexLocation(-2, -1), EdgeDirection.North, radius));
		portsLocation.add(new Port(null, new HexLocation(0, -3), EdgeDirection.North, radius));
		portsLocation.add(new Port(null, new HexLocation(2, -3), EdgeDirection.NorthWest, radius));
		portsLocation.add(new Port(null, new HexLocation(3, -2), EdgeDirection.SouthWest, radius));
		Collections.shuffle(Arrays.asList(portsLocation));
		
		for (int i = 0; i < 9; i++)
			ports[i] = new Port(portsType.get(i).getType(), portsLocation.get(i).getLocation(), portsLocation.get(i).getDirection(), portsType.get(i).getRatio());
			
	} */
	
	
	/*
	 * When a 7 is rolled this method is called and allows the 
	 * user to place the robber on any Hex on the map
	 * 
	 * @Param Hex location
	 * 
	 * @Return void
	 */
	public void moveRobber(int x, int y)//HexLocation hexLocation)
	{
		 for(int i = 0; i < 19; i++)
			 if (getHexes()[i].hasRobber())
				 getHexes()[i].takeRobber();
			 else if (getHexes()[i].getLocation().getX() == x//hexLocation)
					 && getHexes()[i].getLocation().getY() == y)
			 {
				 getHexes()[i].giveRobber();
				 robber.setLocation(x, y);//hexLocation);
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
		return null;/*
		List<Integer> players = new ArrayList<Integer>();
		HexLocation robberHex = robber.getLocation();
		
		for (int i = 0; i < cities.size(); i++)
			if ((cities.get(i).getLocation().getHexLoc().getX() == robberHex.getX() + 1) && 
				(cities.get(i).getLocation().getHexLoc().getY() == robberHex.getY()) &&
			    ((cities.get(i).getLocation().getDir() == VertexDirection.East) || 
				(cities.get(i).getLocation().getDir() == VertexDirection.SouthEast)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getHexLoc().getX() == robberHex.getX()) && 
					 (cities.get(i).getLocation().getHexLoc().getY() == robberHex.getY() + 1) &&
					 ((cities.get(i).getLocation().getDir() == VertexDirection.SouthEast) || 
					 (cities.get(i).getLocation().getDir() == VertexDirection.SouthWest)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getHexLoc().getX() == robberHex.getX() - 1) && 
					 (cities.get(i).getLocation().getHexLoc().getY() == robberHex.getY() + 1) &&
					 ((cities.get(i).getLocation().getDir() == VertexDirection.East) || 
					 (cities.get(i).getLocation().getDir() == VertexDirection.SouthEast)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getHexLoc().getX() == robberHex.getX() - 1) && 
					 (cities.get(i).getLocation().getHexLoc().getY() == robberHex.getY()) &&
					 ((cities.get(i).getLocation().getDir() == VertexDirection.East) || 
					 (cities.get(i).getLocation().getDir() == VertexDirection.NorthEast)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getHexLoc().getX() == robberHex.getX()) && 
					 (cities.get(i).getLocation().getHexLoc().getY() == robberHex.getY() - 1) &&
					 ((cities.get(i).getLocation().getDir() == VertexDirection.NorthWest) || 
					 (cities.get(i).getLocation().getDir() == VertexDirection.NorthEast)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getHexLoc().getX() == robberHex.getX() + 1) && 
					 (cities.get(i).getLocation().getHexLoc().getY() == robberHex.getY() - 1) &&
					 ((cities.get(i).getLocation().getDir() == VertexDirection.NorthWest) || 
					 (cities.get(i).getLocation().getDir() == VertexDirection.West)))
				players.add(cities.get(i).getOwner());
			else if ((cities.get(i).getLocation().getHexLoc().getX() == robberHex.getX()) && 
					 (cities.get(i).getLocation().getHexLoc().getY() == robberHex.getY()) &&
					 ((cities.get(i).getLocation().getDir() == VertexDirection.NorthWest) || 
					 (cities.get(i).getLocation().getDir() == VertexDirection.West) ||
					 (cities.get(i).getLocation().getDir() == VertexDirection.SouthWest) ||
					 (cities.get(i).getLocation().getDir() == VertexDirection.SouthEast) ||
					 (cities.get(i).getLocation().getDir() == VertexDirection.East) ||
					 (cities.get(i).getLocation().getDir() == VertexDirection.NorthEast)))
				players.add(cities.get(i).getOwner());
		
		for (int i = 0; i < settlements.size(); i++)
			if ((settlements.get(i).getLocation().getHexLoc().getX() == robberHex.getX() + 1) && 
				(settlements.get(i).getLocation().getHexLoc().getY() == robberHex.getY()) &&
			    ((settlements.get(i).getLocation().getDir() == VertexDirection.East) || 
				(settlements.get(i).getLocation().getDir() == VertexDirection.SouthEast)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getHexLoc().getX() == robberHex.getX()) && 
					 (settlements.get(i).getLocation().getHexLoc().getY() == robberHex.getY() + 1) &&
					 ((settlements.get(i).getLocation().getDir() == VertexDirection.SouthEast) || 
					 (settlements.get(i).getLocation().getDir() == VertexDirection.SouthWest)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getHexLoc().getX() == robberHex.getX() - 1) && 
					 (settlements.get(i).getLocation().getHexLoc().getY() == robberHex.getY() + 1) &&
					 ((settlements.get(i).getLocation().getDir() == VertexDirection.East) || 
					 (settlements.get(i).getLocation().getDir() == VertexDirection.SouthEast)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getHexLoc().getX() == robberHex.getX() - 1) && 
					 (settlements.get(i).getLocation().getHexLoc().getY() == robberHex.getY()) &&
					 ((settlements.get(i).getLocation().getDir() == VertexDirection.East) || 
					 (settlements.get(i).getLocation().getDir() == VertexDirection.NorthEast)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getHexLoc().getX() == robberHex.getX()) && 
					 (settlements.get(i).getLocation().getHexLoc().getY() == robberHex.getY() - 1) &&
					 ((settlements.get(i).getLocation().getDir() == VertexDirection.NorthWest) || 
					 (settlements.get(i).getLocation().getDir() == VertexDirection.NorthEast)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getHexLoc().getX() == robberHex.getX() + 1) && 
					 (settlements.get(i).getLocation().getHexLoc().getY() == robberHex.getY() - 1) &&
					 ((settlements.get(i).getLocation().getDir() == VertexDirection.NorthWest) || 
					 (settlements.get(i).getLocation().getDir() == VertexDirection.West)))
				players.add(settlements.get(i).getOwner());
			else if ((settlements.get(i).getLocation().getHexLoc().getX() == robberHex.getX()) && 
					 (settlements.get(i).getLocation().getHexLoc().getY() == robberHex.getY()) &&
					 ((settlements.get(i).getLocation().getDir() == VertexDirection.NorthWest) || 
					 (settlements.get(i).getLocation().getDir() == VertexDirection.West) ||
					 (settlements.get(i).getLocation().getDir() == VertexDirection.SouthWest) ||
					 (settlements.get(i).getLocation().getDir() == VertexDirection.SouthEast) ||
					 (settlements.get(i).getLocation().getDir() == VertexDirection.East) ||
					 (settlements.get(i).getLocation().getDir() == VertexDirection.NorthEast)))
				players.add(settlements.get(i).getOwner());
		
		return players;*/
	}

	public Hex[] getHexes() {
		return hexes;
	}

	public void setHexes(Hex[] hexes) {
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
}
