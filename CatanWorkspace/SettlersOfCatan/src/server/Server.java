package server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import server.handlers.GameListAIHandler;
import server.handlers.GameModelHandler;
import server.handlers.GameResetHandler;
import server.handlers.GamesCreateHandler;
import server.handlers.GamesJoinHandler;
import server.handlers.GamesListHandler;
import server.handlers.MovesAcceptTradeHandler;
import server.handlers.MovesBuildCityHandler;
import server.handlers.MovesBuildRoadHandler;
import server.handlers.MovesBuildSettlementHandler;
import server.handlers.MovesBuyDevCardHandler;
import server.handlers.MovesDiscardCardsHandler;
import server.handlers.MovesFinishTurnHandler;
import server.handlers.MovesMaritimeTradeHandler;
import server.handlers.MovesMonopolyHandler;
import server.handlers.MovesMonumentHandler;
import server.handlers.MovesOfferTradeHandler;
import server.handlers.MovesRoadBuildingHandler;
import server.handlers.MovesRobPlayerHandler;
import server.handlers.MovesRollNumberHandler;
import server.handlers.MovesSendChatHandler;
import server.handlers.MovesSoldierHandler;
import server.handlers.MovesYearOfPlentyHandler;
import server.handlers.UserLoginHandler;
import server.handlers.UserRegisterHandler;
import shared.Util;

import com.sun.net.httpserver.HttpServer;

/**
 * The Class Server.
 */
public class Server
{
	private static final Logger log = Logger.getLogger(Server.class.getName());

	/** The Constant DEFAULT_SERVER_PORT_NUMBER. */
	private static final int DEFAULT_SERVER_PORT_NUMBER = 8081;

	/** The Constant DEFAULT_MAX_WAITING_CONNECTIONS. */
	private static final int DEFAULT_MAX_WAITING_CONNECTIONS = 10;

	/** The server port number. */
	private Integer serverPortNumber = null;

	/** The max waiting connections. */
	private Integer maxWaitingConnections = null;

	/** The data folder. */
	private File dataFolder = null;

	/** The server. */
	private HttpServer server;

	private ServerLobby serverLobby;

	/**
	 * Starts a server on the default port.
	 */
	public Server()
	{
		init(DEFAULT_SERVER_PORT_NUMBER, DEFAULT_MAX_WAITING_CONNECTIONS);
	}

	/**
	 * Starts a server on the given port.
	 * 
	 * @param port
	 *            the port to start the server listening on
	 */
	public Server(int port)
	{
		init(port, DEFAULT_MAX_WAITING_CONNECTIONS);
	}

	/**
	 * Starts a server on the given port with the max connections specified.
	 * 
	 * @param port
	 *            the port to start the server listening on
	 * @param maxConnections
	 *            the maximum number of connections for the server
	 */
	public Server(int port, int maxConnections)
	{
		init(port, maxConnections);
	}

	/**
	 * Inits the.
	 * 
	 * @param port
	 *            the port
	 * @param maxConnections
	 *            the max connections
	 */
	private void init(int port, int maxConnections)
	{
		serverPortNumber = port;
		maxWaitingConnections = maxConnections;
		serverLobby = new ServerLobby(this);
	}

	/**
	 * Run the server. Connect to a port and begin listening for traffic. Create
	 * and set up contexts with the handlers for each operation the server
	 * needs.
	 */
	public void run()
	{
		log.trace("Starting server");
		// TODO Initialize database or data storage here

		try
		{
			server = HttpServer.create(new InetSocketAddress(serverPortNumber), maxWaitingConnections);

			Map<String, String> env = new HashMap<String, String>();
			env.put("server.port", serverPortNumber.toString());

			Util.setEnv(env);

			// May need this for saving stuff later
			dataFolder = new File(Config.getConfig().getProperty(Config.DATA_FOLDER));
		}
		catch (IOException e)
		{
			System.err.println("Could not create HTTP server: " + e.getMessage());
			e.printStackTrace();
			return;
		}

		log.trace("Http server created. Adding contexts.");

		server.setExecutor(null); // use the default executor

		server.createContext("/docs/api/data", new Handlers.JSONAppender(""));
		server.createContext("/docs/api/view", new Handlers.BasicFile(""));

		server.createContext("/user/login", new UserLoginHandler(this));
		server.createContext("/user/register", new UserRegisterHandler(this));

		server.createContext("/games/list", new GamesListHandler(this));
		server.createContext("/games/create", new GamesCreateHandler(this));
		server.createContext("/games/join", new GamesJoinHandler(this));
//		server.createContext("/games/save", new ReqResHandler<MessageResponse, GamesSaveRequest>(this, GamesSaveRequest.class));
//		server.createContext("/games/load", new ReqResHandler<MessageResponse, GamesLoadRequest>(this, GamesLoadRequest.class));

		server.createContext("/game/model", new GameModelHandler(this));
		server.createContext("/game/reset", new GameResetHandler(this));
//		server.createContext("/game/commands", new ReqResHandler<CommandsResponse, GameCommandsRequest>(this, GameCommandsRequest.class));
		server.createContext("/game/listAI", new GameListAIHandler(this));

		server.createContext("/moves/sendChat", new MovesSendChatHandler(this));
		server.createContext("/moves/rollNumber", new MovesRollNumberHandler(this));
		server.createContext("/moves/robPlayer", new MovesRobPlayerHandler(this));
		server.createContext("/moves/finishTurn", new MovesFinishTurnHandler(this));
		server.createContext("/moves/buyDevCard", new MovesBuyDevCardHandler(this));
		server.createContext("/moves/Year_of_Plenty", new MovesYearOfPlentyHandler(this));
		server.createContext("/moves/Road_Building", new MovesRoadBuildingHandler(this));
		server.createContext("/moves/Soldier", new MovesSoldierHandler(this));
		server.createContext("/moves/Monument", new MovesMonumentHandler(this));
		server.createContext("/moves/Monopoly", new MovesMonopolyHandler(this));
		server.createContext("/moves/buildRoad", new MovesBuildRoadHandler(this));
		server.createContext("/moves/buildCity", new MovesBuildCityHandler(this));
		server.createContext("/moves/buildSettlement", new MovesBuildSettlementHandler(this));
		server.createContext("/moves/offerTrade", new MovesOfferTradeHandler(this));
		server.createContext("/moves/acceptTrade", new MovesAcceptTradeHandler(this));
		server.createContext("/moves/maritimeTrade", new MovesMaritimeTradeHandler(this));
		server.createContext("/moves/discardCards", new MovesDiscardCardsHandler(this));

//		server.createContext("/util/changeLogLevel", new UtilChangeLogLevelHandler(this));

		log.trace("Created contexts. Starting server");

		server.start();

		log.info("Server started");
	}

	/**
	 * Stop the server. This has a 1000ms timeout set.
	 */
	public void stop()
	{
		server.stop(1000);
	}

	/**
	 * Start a server on the default port or a port specified on the command
	 * line.
	 * 
	 * @param args
	 *            a port can be specified as the first parameter
	 */
	public static void main(String[] args)
	{
		// Initialize logging
		DOMConfigurator.configure("log4j.xml");
		log.info("Starting Catan Client");

		// look for a port
		if (args.length == 0)
		{
			new Server().run();
		}
		else if (args.length == 1)
		{
			new Server(Integer.valueOf(args[0])).run();
		}
		else
		{
			System.err.println("Usage: server [port]");
		}
	}

	/**
	 * Set the level of logging for the server
	 * 
	 * @param logLevel
	 *            the log level to set the server to
	 */
	public void setLogLevel(Level logLevel)
	{
		LogManager.getRootLogger().setLevel(logLevel);
	}
	
	/**
	 * Get the server lobby of this server
	 * @return this server's server lobby
	 */
	public ServerLobby getServerLobby()
	{
		return serverLobby;
	}
}
