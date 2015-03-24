package server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private Map<Integer, ServerGame> games = new HashMap<Integer, ServerGame>();
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

	public boolean addUser(String username, String password)
	{
		users.add(new ServerUser(username, password));
		return true;
	}

	public Integer getUserID(String username)
	{
		ServerUser user = getUser(username);
		return (user == null) ? null : user.getID();
	}
	
	public ServerUser getUser(String username)
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
	
	public ServerGame[] getGames()
	{
		return games.values().toArray(new ServerGame[0]);
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
