package comm.client;

import static org.junit.Assert.*;

import org.junit.*;

import shared.CanCan;
import shared.DevCardList;
import shared.Hex;
import shared.Player;
import shared.ResourceList;
import shared.Robber;
import shared.TurnTracker;
import shared.TurnType;
import shared.definitions.HexType;
import shared.locations.HexLocation;

public class Testing {

	private Player player0 = new Player();
	private Player player1 = new Player();
	ResourceList offer = new ResourceList();
	TurnTracker turn = new TurnTracker();
	private Player bank = new Player();
	private Hex robberHex = new Hex(new HexLocation(0,0), HexType.SHEEP, 4);
	private Robber robber = new Robber();
	
	public void SetUp1(){
		turn.setStatus(TurnType.PLAYING);
		turn.setCurrentTurn(0);
		
		player0.setPlayerIndex(0);
		player0.setResources(new ResourceList(2,0,0,0,0));
		
		player1.setPlayerIndex(1);
		player1.setResources(new ResourceList(0,2,0,0,0));
		
		offer = new ResourceList(2,-2,0,0,0);
		
		bank.setResources(new ResourceList(5,5,5,5,5));
		bank.setOldDevCards(new DevCardList(5,5,5,5,5));
		player0.setOldDevCards(new DevCardList(1,1,1,1,1));
		
		robber.setLocation(robberHex.getLocation());
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
	}
	
	@Test
	public void testCanBuyDevCard(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setResources(new ResourceList(0,0,1,1,1));
		assertTrue(CanCan.canBuyDevCard(player0, bank.getOldDevCards()));
		
		// insufficient resources
		SetUp1();
		assertFalse(CanCan.canBuyDevCard(player0, bank.getOldDevCards()));
	}

	@Test
	public void testCanUseYearOfPlenty(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseYearOfPlenty(player0));

		// has already played a dev card
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseYearOfPlenty(player0));
		
		// has the dev card to play
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseYearOfPlenty(player0));
		
	}

	@Test
	public void testCanUseRoadBuilder(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseRoadBuilder(player0));

		// has already played a dev card
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseRoadBuilder(player0));
		
		// has the dev card to play
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseRoadBuilder(player0));
	}

	@Test
	public void testCanUseSoldier(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseSoldier(player0));

		// has already played a dev card
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseSoldier(player0));
		
		// has the dev card to play
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseSoldier(player0));
	}

	@Test
	public void testCanUseMonopoly(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseMonopoly(player0));

		// has already played a dev card
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseMonopoly(player0));
		
		// has the dev card to play
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseMonopoly(player0));
	}

	@Test
	public void testCanUseMonument(){
		// Initial test to check if it can pass
		SetUp1();
		player0.setPlayedDevCard(false);
		assertTrue(CanCan.canUseMonument(player0));

		// has already played a dev card
		player0.setPlayedDevCard(true);
		assertFalse(CanCan.canUseMonument(player0));
		
		// has the dev card to play
		player0.setOldDevCards(new DevCardList(0,0,0,0,0));
		assertFalse(CanCan.canUseMonument(player0));
	}

	@Test
	public void testCanPlaceRobber(){
		// Initial test to check if it can pass
		SetUp1();
		assertTrue(CanCan.canPlaceRobber(new Hex(new HexLocation(1,0), HexType.ORE, 3), robber));
		
		// Try to move robber to same hex he is already on
		assertFalse(CanCan.canPlaceRobber(robberHex, robber));

		// Try to move robber to desert hex
		assertFalse(CanCan.canPlaceRobber(new Hex(new HexLocation(2,0), HexType.DESERT, 0), robber));
		
		// Try to move robber to water hex
		assertFalse(CanCan.canPlaceRobber(new Hex(new HexLocation(2,2), HexType.WATER, 0), robber));
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
		turn.setCurrentTurn(1);
		turn.setStatus(TurnType.DISCARDING);
		player0.setDiscarded(false);
		assertFalse(CanCan.canDiscardCards(player0, turn));

		// Not discarding phase
		turn.setCurrentTurn(0);
		turn.setStatus(TurnType.PLAYING);
		player0.setDiscarded(false);
		assertFalse(CanCan.canDiscardCards(player0, turn));
		
		// Already discarded
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
		turn.setCurrentTurn(1);
		turn.setStatus(TurnType.ROLLING);
		assertFalse(CanCan.canRollNumber(player0, turn));
		
		// Not rolling phase
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
		turn.setCurrentTurn(1);
		turn.setStatus(TurnType.PLAYING);
		assertFalse(CanCan.canFinishTurn(player0, turn));
		
		// Not playing phase
		turn.setCurrentTurn(1);
		turn.setStatus(TurnType.ROBBING);
		assertFalse(CanCan.canFinishTurn(player0, turn));
	}
}
