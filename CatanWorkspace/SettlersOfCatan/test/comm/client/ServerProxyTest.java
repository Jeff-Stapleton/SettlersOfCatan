package comm.client;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import comm.client.ServerProxy;
import comm.shared.serialization.GameResponse;
import comm.shared.serialization.PlayerResponse;
import shared.definitions.CatanColor;
import shared.definitions.ResourceType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;

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
		testMovesRoadBuilding
		testMoveSoldier();
		testMovesMonopoly();
		testMovesMonument();
		testMovesBuildRoad();
		testMovesBuildSettlement();
		testMovesBuildCity();
		testMovesOfferTrade();
		testMovesAcceptTrade();byucs
		testMovesMaritimeTrade();
		testMovesDiscardCards
		
		testMovesFinishTurn();		
	}
	
	public void testGamesSaving() throws IOException
	{
		
	}
	
	public void testGameModel() throws IOException
	{
		
	}
	
	public void testGameReset() throws IOException
	{
		
	}
	
	public void testGameCommandsPost() throws IOException
	{
		
	}
	
	public void testGameCommandsGet() throws IOException
	{
		
	}
	
	public void testGameAddAI() throws IOException
	{

	}
	
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
		proxy.movesRobPlayer(0, 1, new HexLocation(-2, 0));
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
		proxy.movesRoadBuilding(0, new EdgeLocation(new HexLocation(1,0),EdgeDirection.South), new EdgeLocation(new HexLocation(0,1),EdgeDirection.SouthEast));
	}
	
	public void testMovesSoldier() throws IOException
	{
		proxy.movesSoldier(0, 1, new HexLocation(-2, 1));
	}

	public void testMovesMonopoly() throws IOException
	{
		proxy.movesMonopoly(0, ResourceType.WOOD);
	}
	
	public void testMovesMonument() throws IOException
	{
		proxy.movesMonument(0);
	}
	
	public void testMovesBuildRoad() throws IOException
	{
		proxy.movesBuildRoad(0, new EdgeLocation(new HexLocation(-1,1),EdgeDirection.SouthEast), true);
	}
	
	public void testMovesBuildSettlement() throws IOException
	{
		proxy.movesBuildSettlement(0, new VertexLocation(new HexLocation(0,2), VertexDirection.West), free);
	}
	
	public void testMovesBuildCity() throws IOException
	{
		proxy.movesBuildCity(0, new VertexLocation(new HexLocation(1,1), VertexDirection.West), free);
	}
	
	public void testMovesOfferTrade() throws IOException
	{
		proxy.movesOfferTrade(new TradeOffer(0, 1, new ResourceList(0,-1,0,1,0)));
	}
	
	public void testMovesAcceptTrade() throws IOException
	{
		proxy.movesAcceptTrade(1, true);
	}
	
	public void testMovesMaritimeTrade() throws IOException
	{
		proxy.movesMaritimeTrade(0, 3, ResourceType.BRICK, ResourceType.WOOD);
	}

	public void testMovesDiscardCards() throws IOException
	{
		proxy.movesDiscardCards(0, new ResourceList(0,0,-1,0,0)); 
	}
	
	public void testUtilChangeLogLevel() throws IOException
	{
		
	}

}
