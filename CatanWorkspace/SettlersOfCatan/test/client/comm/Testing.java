package client.comm;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import shared.Building;
import shared.CanCan;
import shared.CatanModel;
import shared.DevCardList;
import shared.Hex;
import shared.Map;
import shared.Player;
import shared.Port;
import shared.ResourceList;
import shared.Road;
import shared.Robber;
import shared.TurnTracker;
import shared.TurnType;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class Testing {

	private Player player0 = new Player();
	private Player player1 = new Player();
	private ResourceList offer = new ResourceList();
	private TurnTracker turn = new TurnTracker();
	private Hex robberHex = new Hex(new HexLocation(0,0), HexType.SHEEP, 4);
	private Robber robber = new Robber();
	private DevCardList deck = new DevCardList();
	private ResourceList bank = new ResourceList();
	private Map map;
	private ArrayList <Building> settlements;
	private Player player2;
	private ArrayList <Port> port;
	private ArrayList <Road> road;
	private CatanModel catanmodel;
	
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
		port.add(new Port(null, new HexLocation(2, -3), EdgeDirection.South, 3));
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
		setup();
		// Incorrect ratio
		/*assertFalse("Can Maritime Trade", CanCan.canMaritimeTrade(player0, turn, new ResourceList(-1, 4, 0, 0, 0), bank, port, map));
		
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
		*/
	}
	
	@Test
	public void canBuildSettlement() 
	{
		setup();
		//assertTrue("Can Build Settlement", CanCan.canBuildSettlement(player0, new VertexLocation(1, -3, VertexDirection.West), turn, map));
		turn.setCurrentTurn(1);
		assertFalse("Can Build Settlement", CanCan.canBuildSettlement(player1, new VertexLocation(2, 1, VertexDirection.West), turn, map));
	}
	
	@Test
	public void canBuildCity() 
	{
		setup();
		assertTrue("Can Build City", CanCan.canBuildCity(player0, new VertexLocation(3, 0, VertexDirection.West), turn, map));
		assertFalse("Can Build City", CanCan.canBuildCity(player0, new VertexLocation(3, 0, VertexDirection.SouthWest), turn, map));
	}
	
	@Test
	public void canBuildRoad() 
	{
		setup();
		assertTrue("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(1, 1, EdgeDirection.SouthEast), turn, map));
		
		//Can't build on another persons road
		assertFalse("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(2, 0, EdgeDirection.SouthWest), turn, map));
		
		//Can't build on already built road
		assertFalse("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(2, 0, EdgeDirection.South), turn, map));
		
		//no roads on water
		//assertFalse("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(3, 0, EdgeDirection.SouthWest), turn, map));
		
		//Can't build by a settlement you don't own
		assertFalse("Can Build Road", CanCan.canBuildRoad(player0, new EdgeLocation(1, 0, EdgeDirection.South), turn, map));
		
		turn.setCurrentTurn(1);
		assertTrue("Can Build Road", CanCan.canBuildRoad(player1, new EdgeLocation(0, 2, EdgeDirection.SouthWest), turn, map));
		assertTrue("Can Build Road", CanCan.canBuildRoad(player1, new EdgeLocation(0, 2, EdgeDirection.SouthEast), turn, map));
	}
	
	
	
	
	
	@Before
	public void SetUp1(){
		turn.setStatus(TurnType.PLAYING);
		turn.setCurrentTurn(0);
		
		player0.setPlayerIndex(0);
		player0.setResources(new ResourceList(2,0,0,0,0));
		
		player1.setPlayerIndex(1);
		player1.setResources(new ResourceList(0,2,0,0,0));
		
		offer = new ResourceList(2,-2,0,0,0);
		
		bank = new ResourceList(5,5,5,5,5);
		deck = new DevCardList(5,5,5,5,5);
		player0.setOldDevCards(new DevCardList(1,1,1,1,1));
		
//		robber.setLocation(robberHex.getLocation());
		robber.setLocation(0, 0);
	}
	
	@Test
	public void testCanOfferTrade(){
		// Initial test to check if it can pass
		SetUp1();
		assertTrue(CanCan.canOfferTrade(player0, player1, turn, offer));
		
		// Receiver doesn't have required resources
		SetUp1();
		player1.setResources(new ResourceList(0,0,0,0,0));
		assertFalse(CanCan.canOfferTrade(player0, player1, turn, offer));

		// Player doesn't have required resources
		SetUp1();
		player0.setResources(new ResourceList(0,0,0,0,0));
		assertFalse(CanCan.canOfferTrade(player0, player1, turn, offer));
		
		// Incorrect phase of the game
		SetUp1();
		turn.setStatus(TurnType.FIRST_ROUND);
		assertFalse(CanCan.canOfferTrade(player0, player1, turn, offer));
		
		// Not the players turn
		SetUp1();
		turn.setCurrentTurn(1);
		assertFalse(CanCan.canOfferTrade(player0, player1, turn, offer));
	}
	
	@Test
	public void testCanBuyDevCard(){ 
		// Initial test to check if it can pass
		SetUp1();
		player0.setResources(new ResourceList(0,0,1,1,1));
		assertTrue(CanCan.canBuyDevCard(player0, deck, turn));
		
		// insufficient resources
		SetUp1();
		player0.setResources(new ResourceList(0,0,0,0,0));
		assertFalse(CanCan.canBuyDevCard(player0, deck, turn));
		
		// Deck has no cards left to buy from
		SetUp1();
		deck = new DevCardList(0,0,0,0,0);
		assertFalse(CanCan.canBuyDevCard(player0, deck, turn));

		// Incorrect phase of the game
		SetUp1();
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canBuyDevCard(player0, deck, turn));
		
		// Not the players turn
		SetUp1();
		turn.setCurrentTurn(1);
		assertFalse(CanCan.canBuyDevCard(player0, deck, turn));
	}

	@Test
	public void testCanUseYearOfPlenty(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseYearOfPlenty(player0, turn));
		
		// has already played a dev card
		SetUp1();
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseYearOfPlenty(player0, turn));
		
		// doesn't have the dev card to play
		SetUp1();
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseYearOfPlenty(player0, turn));

		// Incorrect phase of the game
		SetUp1();
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canUseYearOfPlenty(player0, turn));
		
		// Not the players turn
		SetUp1();
		turn.setCurrentTurn(1);
		assertFalse(CanCan.canUseYearOfPlenty(player0, turn));		
	}

	@Test
	public void testCanUseRoadBuilder(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseRoadBuilder(player0, turn));

		// has already played a dev card
		SetUp1();
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseRoadBuilder(player0, turn));
		
		// doesn't have the dev card to play
		SetUp1();
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseRoadBuilder(player0, turn));

		// Incorrect phase of the game
		SetUp1();
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canUseRoadBuilder(player0, turn));
		
		// Not the players turn
		SetUp1();
		turn.setCurrentTurn(1);
		assertFalse(CanCan.canUseRoadBuilder(player0, turn));
	}

	@Test
	public void testCanUseSoldier(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseSoldier(player0, turn));

		// has already played a dev card
		SetUp1();
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseSoldier(player0, turn));
		
		// doesn't have the dev card to play
		SetUp1();
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseSoldier(player0, turn));

		// Incorrect phase of the game
		SetUp1();
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canUseSoldier(player0, turn));
		
		// Not the players turn
		SetUp1();
		turn.setCurrentTurn(1);
		assertFalse(CanCan.canUseSoldier(player0, turn));
	}

	@Test
	public void testCanUseMonopoly(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseMonopoly(player0, turn));

		// has already played a dev card
		SetUp1();
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseMonopoly(player0, turn));
		
		// doesn't have the dev card to play
		SetUp1();
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseMonopoly(player0, turn));

		// Incorrect phase of the game
		SetUp1();
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canUseMonopoly(player0, turn));
		
		// Not the players turn
		SetUp1();
		turn.setCurrentTurn(1);
		assertFalse(CanCan.canUseMonopoly(player0, turn));
	}

	@Test
	public void testCanUseMonument(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseMonument(player0, turn));

		// has already played a dev card
		SetUp1();
		player0.setPlayedDevCard(true);
		assertTrue(CanCan.canUseMonument(player0, turn));
		
		// doesn't have the dev card to play
		SetUp1();
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseMonument(player0, turn));

		// Incorrect phase of the game
		SetUp1();
		turn.setCurrentTurn(1);
		assertFalse(CanCan.canUseMonument(player0, turn));
		
		// Not the players turn
		SetUp1();
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canUseMonument(player0, turn));
	}

	@Test
	public void testCanPlaceRobber(){
		// Initial test to check if it can pass
		SetUp1();
		turn.setStatus(TurnType.ROBBING);
		assertTrue(CanCan.canPlaceRobber(new Hex(new HexLocation(1,0), HexType.ORE, 3), robber, turn, player0));

		// Incorrect phase of the game
		SetUp1();
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canUseRoadBuilder(player0, turn));
		
		// Try to move robber to same hex he is already on
		SetUp1();
		assertFalse(CanCan.canPlaceRobber(robberHex, robber, turn, player0));

		// Try to move robber to desert hex
		SetUp1();
		assertFalse(CanCan.canPlaceRobber(new Hex(new HexLocation(2,0), HexType.DESERT, 0), robber, turn, player0));
		
		// Try to move robber to water hex
		SetUp1();
		assertFalse(CanCan.canPlaceRobber(new Hex(new HexLocation(2,2), HexType.WATER, 0), robber, turn, player0));
	}

	@Test
	public void testCanDiscardCards(){
		// Initial test to check if it can pass
		SetUp1();
		turn.setCurrentTurn(0);
		turn.setStatus(TurnType.DISCARDING);
		player0.setDiscarded(false);
		assertTrue(CanCan.canDiscardCards(player0, turn));
		
		// Not players turn
		SetUp1();
		turn.setCurrentTurn(1);
		turn.setStatus(TurnType.DISCARDING);
		player0.setDiscarded(false);
		//assertFalse(CanCan.canDiscardCards(player0, turn));

		// Not discarding phase
		SetUp1();
		turn.setCurrentTurn(0);
		turn.setStatus(TurnType.PLAYING);
		player0.setDiscarded(false);
		assertFalse(CanCan.canDiscardCards(player0, turn));
		
		// Already discarded
		SetUp1();
		turn.setCurrentTurn(0);
		turn.setStatus(TurnType.DISCARDING);
		player0.setDiscarded(true);
		assertFalse(CanCan.canDiscardCards(player0, turn));
	}

	@Test
	public void testCanRollNumber(){
		// Initial test to check if it can pass
		SetUp1();
		turn.setCurrentTurn(0);
		turn.setStatus(TurnType.ROLLING);
		assertTrue(CanCan.canRollNumber(player0, turn));

		// Not players turn
		SetUp1();
		turn.setCurrentTurn(1);
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canRollNumber(player0, turn));
		
		// Not rolling phase
		SetUp1();
		turn.setCurrentTurn(0);
		turn.setStatus(TurnType.PLAYING);
		assertFalse(CanCan.canRollNumber(player0, turn));
	}

	@Test
	public void testCanFinishTurn(){
		// Initial test to check if it can pass
		SetUp1();
		turn.setCurrentTurn(0);
		turn.setStatus(TurnType.PLAYING);
		assertTrue(CanCan.canFinishTurn(player0, turn));
		
		// Not players turn
		SetUp1();
		turn.setCurrentTurn(1);
		turn.setStatus(TurnType.PLAYING);
		assertFalse(CanCan.canFinishTurn(player0, turn));
		
		// Not playing phase
		SetUp1();
		turn.setCurrentTurn(1);
		turn.setStatus(TurnType.ROBBING);
		assertFalse(CanCan.canFinishTurn(player0, turn));
	}
}
