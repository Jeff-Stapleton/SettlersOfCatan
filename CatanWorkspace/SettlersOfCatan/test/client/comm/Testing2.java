package client.comm;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import shared.Building;
import shared.CanCan;
import shared.CatanModel;
import shared.Map;
import shared.Player;
import shared.Port;
import shared.ResourceList;
import shared.Road;
import shared.TurnTracker;
import shared.TurnType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class Testing2 {
	
	private TurnTracker turn;
	private Map map;
	private ArrayList <Building> settlements;
	private Player player0;
	private Player player1;
	private Player player2;
	private ArrayList <Port> port;
	private ArrayList <Road> road;
	private CatanModel catanmodel;
	private ResourceList bank;
	
	@Before
	public void setup()
	{
		turn = new TurnTracker();
		turn.setStatus(TurnType.PLAYING);
		turn.setCurrentTurn(0);
		bank = new ResourceList(5, 5, 5, 5, 5);
		
		map = new Map();
		settlements = new ArrayList<Building>();
		settlements.add(new Building(0, new VertexLocation(3, 0, VertexDirection.West)));
		settlements.add(new Building(0, new VertexLocation(3, -3, VertexDirection.West)));
		settlements.add(new Building(1, new VertexLocation(1, 2, VertexDirection.West)));
		settlements.add(new Building(1, new VertexLocation(2, 0, VertexDirection.West)));
		settlements.add(new Building(2, new VertexLocation(0, 1, VertexDirection.SouthWest)));
		map.setSettlements(settlements);
		
		port = new ArrayList<Port>();
		port.add(new Port(PortType.WOOD, new HexLocation(2, 0), EdgeDirection.SouthEast, 2));
		port.add(new Port(PortType.ORE, new HexLocation(0, 2), EdgeDirection.South, 2));
		port.add(new Port(PortType.THREE, new HexLocation(2, -3), EdgeDirection.South, 3));
		map.setPorts(port);
		
		road = new ArrayList<Road>();
		road.add(new Road(1, new EdgeLocation(2, 0, EdgeDirection.SouthWest)));
		road.add(new Road(1, new EdgeLocation(0, 2, EdgeDirection.South)));
		road.add(new Road(0, new EdgeLocation(2, 0, EdgeDirection.South)));
		road.add(new Road(0, new EdgeLocation(0, -3, EdgeDirection.South)));
		map.setRoads(road);
		
		player0 = new Player();
		player0.setPlayerIndex(0);
		player0.setResources(new ResourceList(5, 5, 5, 5, 5));
		player0.setSettlements(4);
		player0.setCities(4);
		player0.setRoads(14);
		
		player1 = new Player();
		player1.setPlayerIndex(1);
		player1.setResources(new ResourceList(5, 5, 5, 5, 5));
		player1.setSettlements(4);
		player1.setCities(4);
		player1.setRoads(13);
		
		player2 = new Player();
		player2.setPlayerIndex(1);
		player2.setResources(new ResourceList(5, 5, 5, 5, 5));
		player2.setSettlements(4);
		player2.setCities(4);
		player2.setRoads(12);
	}
	
	@Test
	public void canMaritimeTrade() 
	{
		// Incorrect ratio
		assertFalse("Can Maritime Trade", CanCan.canMaritimeTrade(player0, turn, new ResourceList(-1, 4, 0, 0, 0), bank, port, map));
		
		assertTrue("Can Maritime Trade", CanCan.canMaritimeTrade(player0, turn, new ResourceList(-2, 4, 0, 0, 0), bank, port, map));
		
		//2 positive integers
		assertFalse("Can Maritime Trade", CanCan.canMaritimeTrade(player0, turn, new ResourceList(2, 4, 0, 0, 0), bank, port, map));
		
		assertTrue("Can Maritime Trade", CanCan.canMaritimeTrade(player0, turn, new ResourceList(-1, 2, 0, 0, 0), bank, port, map));
		
		//Checking the 3 port as well
		assertTrue("Can Maritime Trade", CanCan.canMaritimeTrade(player0, turn, new ResourceList(-1, 3, 0, 0, 0), bank, port, map));
		turn.setCurrentTurn(1);
		// Incorrect Ratio
		assertFalse("Can Maritime Trade", CanCan.canMaritimeTrade(player1, turn, new ResourceList(0, 0, -1, 0, 3), bank, port, map));
		
		//Wrong Turn
		assertFalse("Can Maritime Trade", CanCan.canMaritimeTrade(player0, turn, new ResourceList(-1, 2, 0, 0, 0), bank, port, map));
		turn.setCurrentTurn(2);
		
		//Not on a port
		assertFalse("Can Maritime Trade", CanCan.canMaritimeTrade(player2, turn, new ResourceList(-1, 2, 0, 0, 0), bank, port, map));
	}
	
	@Test
	public void canBuildSettlement() 
	{
		assertTrue("Can Build Settlement", CanCan.canBuildSettlement(player0, new VertexLocation(1, -3, VertexDirection.West), turn, map));
		turn.setCurrentTurn(1);
		assertFalse("Can Build Settlement", CanCan.canBuildSettlement(player1, new VertexLocation(2, 1, VertexDirection.West), turn, map));
	}
	
	@Test
	public void canBuildCity() 
	{
		assertTrue("Can Build City", CanCan.canBuildCity(player0, new VertexLocation(3, 0, VertexDirection.West), turn, map));
		assertFalse("Can Build City", CanCan.canBuildCity(player0, new VertexLocation(3, 0, VertexDirection.SouthWest), turn, map));
	}
	
	@Test
	public void canBuildRoad() 
	{
		assertTrue("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(1, 1, EdgeDirection.SouthEast), turn, map));
		
		//Can't build on another persons road
		assertFalse("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(2, 0, EdgeDirection.SouthWest), turn, map));
		
		//Can't build on already built road
		assertFalse("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(2, 0, EdgeDirection.South), turn, map));
		
		//no roads on water
		assertFalse("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(3, 0, EdgeDirection.SouthWest), turn, map));
		
		//Can't build by a settlement you don't own
		assertFalse("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(1, 0, EdgeDirection.South), turn, map));
		
		turn.setCurrentTurn(1);
		assertTrue("Can Build Road", CanCan.canBuildRoad(player1, new EdgeLocation(0, 2, EdgeDirection.SouthWest), turn, map));
		assertTrue("Can Build Road", CanCan.canBuildRoad(player1, new EdgeLocation(0, 2, EdgeDirection.SouthEast), turn, map));
	}

}