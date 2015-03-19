package server;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import server.facade.GamesFacade;
import server.facade.UserFacade;
import server.facade.UtilFacade;
import server.models.ServerUser;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;

/**
 * The Class ServerLobby.
 */
public class ServerLobby
{
	private static final Logger log = Logger.getLogger(ServerLobby.class);
	private Server server = null;
	private List<GameInfo> games = new ArrayList<GameInfo>();
	private List<ServerUser> users = new ArrayList<ServerUser>();
	private UserFacade userFacade = null;
	private GamesFacade gamesFacade = null;
	private UtilFacade utilFacade = null;
	
	public ServerLobby(Server server)
	{
		this.server = server;
		userFacade = new UserFacade(this);
		gamesFacade = new GamesFacade(this);
		utilFacade = new UtilFacade(server);
		
		// TODO: Remove this after testing
		users.add(new ServerUser("Pete", "pete"));
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
	 * @param password 
	 * @param username 
	 * @return 
	 */
	public boolean verifyUser(String username, String password)
	{
		log.trace("Verifying the user's username and password");
		ServerUser user = getUser(username);
		
		if (user != null && user.getPassword().equals(password))
		{
			log.trace("User credentials valid");
			return true;
		}
		log.trace("User credentials invalid");
		return false;
	}
	
	private ServerUser getUser(String username)
	{
		log.trace("Getting user \"" + username + "\"");
		for (ServerUser user : users)
		{
			if (user.getUsername().equals(username))
			{
				log.trace("User found. returning info.");
				return user;
			}
		}
		log.trace("User not found");
		return null;
	}

	/**
	 * Get the user facade
	 * @return the user facade
	 */
	public UserFacade getUserFacade()
	{
		return userFacade;
	}
	
	/**
	 * Get the games facade
	 * @return the games facade
	 */
	public GamesFacade getGamesFacade()
	{
		return gamesFacade;
	}
	
	/**
	 * Get the util facade
	 * @return the util facade
	 */
	public UtilFacade getUtilFacade()
	{
		return utilFacade;
	}

}
