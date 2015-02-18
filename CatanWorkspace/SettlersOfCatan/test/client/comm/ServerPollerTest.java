package client.comm;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import shared.CatanModel;
import shared.definitions.CatanColor;
import client.CatanGame;

public class ServerPollerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		ServerProxy server = new ServerProxy("http://localhost:8081");
		server.userLogin("Sam", "sam");
		server.gamesJoin(CatanColor.ORANGE, 0);
		CatanModel initialModel = server.gameModel();
		CatanGame game = new CatanGame(server, initialModel);
		
		ServerPoller poller = new ServerPoller(server, game);
		poller.start();
		
		Thread.sleep(3000);
		
		poller.close();
		
		CatanModel newModel = game.getModel();
		assertTrue(newModel != initialModel);
		assertTrue(newModel != null);
		assertTrue(newModel.getChat() != null);
		assertTrue(newModel.getPlayers() != null);
		
		
	}

}
