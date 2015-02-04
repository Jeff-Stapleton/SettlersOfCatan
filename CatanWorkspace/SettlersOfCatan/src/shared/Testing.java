package shared;

import static org.junit.Assert.*;

import org.junit.*;

public class Testing {

	private Player player0 = new Player();
	private Player player1 = new Player();
	ResourceList offer = new ResourceList();
	TurnTracker turn = new TurnTracker();
	
	public void SetUp1(){
		turn.setStatus(TurnType.PLAYING);
		turn.setCurrentTurn(0);
		
		player0.setPlayerIndex(0);
		player0.setResources(new ResourceList(2,0,0,0,0));
		
		player1.setPlayerIndex(1);
		player1.setResources(new ResourceList(0,2,0,0,0));
		
		offer = new ResourceList(2,-2,0,0,0);
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
	public void testSetStatus(){
		turn.setStatus(TurnType.PLAYING);
		assertEquals("Testing", turn.getStatus(), TurnType.PLAYING);
	}
}
