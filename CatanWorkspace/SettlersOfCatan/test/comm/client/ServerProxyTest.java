package comm.client;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import comm.shared.serialization.GameResponse;
import comm.shared.serialization.PlayerResponse;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.HexLocation;

public class ServerProxyTest
{
	
	ServerProxy proxy;

	@Before
	public void setUp() throws Exception
	{
		proxy = new ServerProxy("http://localhost:8081");
	}

	@After
	public void tearDown() throws Exception
	{
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testUser() throws IOException
	{
		proxy.userRegister("JJ", "JJ");
		proxy.userRegister("Jeff", "Jeff");
		proxy.userRegister("Jordan", "Jordan");
		proxy.userRegister("Cory", "Cory");
		
		proxy.userLogin("Cory", "Cory");
		proxy.userLogin("Sam", "sam");

	    exception.expect(IOException.class);
		proxy.userRegister("Cory", "Cory");
		proxy.userLogin("fakeman", "jake");
	}
	
	@Test
	public void testGames() throws IOException
	{
		proxy.userRegister("James", "Franco");

		GameResponse[] games = proxy.gamesList();
		assertEquals("There should be 3 games", games.length, 3);
		
		GameResponse game = proxy.gamesCreate("Coolio Game Yo", true, true, true);
		
		assertTrue("Game title comparison", game.getTitle().equals("Coolio Game Yo"));
		assertEquals("Should be the third game on the server", game.getId(), 3);
		for (PlayerResponse player : game.getPlayers())
		{
			assertEquals(player.getId(), -1);
			assertTrue(player.getName() == null);
			assertTrue(player.getColor() == null);
		}
		
		games = proxy.gamesList();
		assertEquals("There should be 4 games now", games.length, 4);
		
		proxy.gamesJoin(CatanColor.BLUE, 3);
	}
	
	@Test
	public void testMoves() throws IOException
	{
		proxy.userLogin("Sam", "sam");
		proxy.gamesJoin(CatanColor.ORANGE, 0);
		
		proxy.gameModel();
		testMovesSendChat();
		testMovesRollNumber();
		testMovesRobPlayer();
		testMovesBuyDevCard();
		testMovesYearOfPlenty();
		
		
		
		testMovesFinishTurn();		
	}
	
	@Test
	public void testGamesSaving() throws IOException
	{
		
	}
	
	@Test
	public void testGameModel() throws IOException
	{
		
	}
	
	@Test
	public void testGameReset() throws IOException
	{
		
	}
	
	@Test
	public void testGameCommandsPost() throws IOException
	{
		
	}
	
	@Test
	public void testGameCommandsGet() throws IOException
	{
		
	}
	
	@Test
	public void testGameAddAI() throws IOException
	{

	}
	
	@Test
	public void testGameListAI() throws IOException
	{
		
	}

	public void testMovesSendChat() throws IOException
	{
		proxy.movesSendChat(0, "GUINEA PIGS");
	}
	
	public void testMovesRollNumber() throws IOException
	{
		proxy.movesRollNumber(0, 7);
	}
	
	public void testMovesRobPlayer() throws IOException
	{
		proxy.movesRobPlayer(0, 1, new HexLocation(0, 0));
	}

	public void testMovesFinishTurn() throws IOException
	{
		proxy.movesFinishTurn(0);
	}
	
	public void testMovesBuyDevCard() throws IOException
	{
		proxy.movesBuyDevCard(0);
	}
	
	public void testMovesYearOfPlenty() throws IOException
	{
		proxy.movesYearOfPlenty(0, ResourceType.BRICK, ResourceType.ORE);
	}
	
	public void testMovesRoadBuilding() throws IOException
	{
		//proxy.movesRoadBuilding(0, new EdgeLocation(new HexLocation(), EdgeDirection.), new EdgeLocation(new HexLocation(), EdgeDirection.)
	}
	
	@Test
	public void testMovesSoldier() throws IOException
	{

	}

	@Test
	public void testMovesMonopoly() throws IOException
	{

	}
	
	@Test
	public void testMovesMonument() throws IOException
	{
		
	}
	
	@Test
	public void testMovesBuildRoad() throws IOException
	{

	}
	
	@Test
	public void testMovesBuildSettlement() throws IOException
	{
		proxy.movesBuildSettlement(0, new VertexLocation(new HexLocation()), free)
	}
	
	@Test
	public void testMovesBuildCity() throws IOException
	{
		
	}
	
	@Test
	public void testMovesOfferTrade() throws IOException
	{
		
	}
	
	@Test
	public void testMovesAcceptTrade() throws IOException
	{
		
	}
	
	@Test
	public void testMovesMaritimeTrade() throws IOException
	{
		
	}

	@Test
	public void testMovesDiscardCards() throws IOException
	{
		
	}
	
	@Test
	public void testUtilChangeLogLevel() throws IOException
	{
		
	}

}
