package server;

import java.util.List;

import server.facade.GamesFacade;
import server.facade.UserFacade;
import server.facade.UtilFacade;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class ServerLobby.
 */
public class ServerLobby
{
	Server server;
	List<GameInfo> games;
	List<PlayerInfo> players;
	UserFacade userFacade = null;
	GamesFacade gamesFacade = null;
	UtilFacade utilFacade = null;
	
	public ServerLobby(Server server)
	{
		userFacade = new UserFacade(this);
		gamesFacade = new GamesFacade(this);
		utilFacade = new UtilFacade(server);
	}
	
	/**
	 * User register.
	 *
	 * @return the player info
	 */
	public PlayerInfo userRegister()
	{
		return null;
	}
	
	/**
	 * User login.
	 *
	 * @return the player info
	 */
	public PlayerInfo userLogin()
	{
		return null;
	}
	
	/**
	 * Games list.
	 *
	 * @return the game info[]
	 */
	public GameInfo[] gamesList()
	{
		return null;
	}
	
	/**
	 * Games join.
	 *
	 * @return the game info
	 */
	public GameInfo gamesJoin()
	{
		return null;
	}
	
	/**
	 * Games save.
	 */
	public void gamesSave()
	{
		
	}
	
	/**
	 * Games load.
	 */
	public void gamesLoad()
	{
		
	}
	
	/**
	 * Verify user.
	 */
	public void verifyUser()
	{
		
	}

}
