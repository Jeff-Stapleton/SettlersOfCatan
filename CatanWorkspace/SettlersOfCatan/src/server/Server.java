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

import server.comm.ReqResHandler;
import server.comm.request.*;
import server.comm.response.*;
import server.handlers.GamesListHandler;
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
		server.createContext("/games/create", new ReqResHandler<GameInfoResponse, GamesCreateRequest>(this, GamesCreateRequest.class));
		server.createContext("/games/join", new ReqResHandler<GameResponse, GamesJoinRequest>(this, GamesJoinRequest.class));
		server.createContext("/games/save", new ReqResHandler<MessageResponse, GamesSaveRequest>(this, GamesSaveRequest.class));
		server.createContext("/games/load", new ReqResHandler<MessageResponse, GamesLoadRequest>(this, GamesLoadRequest.class));

		server.createContext("/game/model", new ReqResHandler<CatanModelResponse, GameModelRequest>(this, GameModelRequest.class));
		server.createContext("/game/reset", new ReqResHandler<CatanModelResponse, GameResetRequest>(this, GameResetRequest.class));
		server.createContext("/game/commands", new ReqResHandler<CommandsResponse, GameCommandsRequest>(this, GameCommandsRequest.class));

		server.createContext("/moves/sendChat", new ReqResHandler<CatanModelResponse, MovesSendChatRequest>(this, MovesSendChatRequest.class));
		server.createContext("/moves/rollNumber", new ReqResHandler<CatanModelResponse, MovesRollNumberRequest>(this, MovesRollNumberRequest.class));
		server.createContext("/moves/robPlayer", new ReqResHandler<CatanModelResponse, MovesRobPlayerRequest>(this, MovesRobPlayerRequest.class));
		server.createContext("/moves/finishTurn", new ReqResHandler<CatanModelResponse, MovesFinishTurnRequest>(this, MovesFinishTurnRequest.class));
		server.createContext("/moves/buyDevCard", new ReqResHandler<CatanModelResponse, MovesBuyDevCardRequest>(this, MovesBuyDevCardRequest.class));
		server.createContext("/moves/Year_of_Plenty", new ReqResHandler<CatanModelResponse, MovesYearOfPlentyRequest>(this, MovesYearOfPlentyRequest.class));
		server.createContext("/moves/Road_Building", new ReqResHandler<CatanModelResponse, MovesRoadBuildingRequest>(this, MovesRoadBuildingRequest.class));
		server.createContext("/moves/Soldier", new ReqResHandler<CatanModelResponse, MovesSoldierRequest>(this, MovesSoldierRequest.class));
		server.createContext("/moves/Monument", new ReqResHandler<CatanModelResponse, MovesMonumentRequest>(this, MovesMonumentRequest.class));
		server.createContext("/moves/buildRoad", new ReqResHandler<CatanModelResponse, MovesBuildRoadRequest>(this, MovesBuildRoadRequest.class));
		server.createContext("/moves/buildCity", new ReqResHandler<CatanModelResponse, MovesBuildCityRequest>(this, MovesBuildCityRequest.class));
		server.createContext("/moves/buildSettlement", new ReqResHandler<CatanModelResponse, MovesBuildSettlementRequest>(this, MovesBuildSettlementRequest.class));
		server.createContext("/moves/offerTrade", new ReqResHandler<CatanModelResponse, MovesOfferTradeRequest>(this, MovesOfferTradeRequest.class));
		server.createContext("/moves/acceptTrade", new ReqResHandler<CatanModelResponse, MovesAcceptTradeRequest>(this, MovesAcceptTradeRequest.class));
		server.createContext("/moves/maritimeTrade", new ReqResHandler<CatanModelResponse, MovesMaritimeTradeRequest>(this, MovesMaritimeTradeRequest.class));
		server.createContext("/moves/discardCards", new ReqResHandler<CatanModelResponse, MovesDiscardCardsRequest>(this, MovesDiscardCardsRequest.class));

		server.createContext("/util/changeLogLevel", new ReqResHandler<MessageResponse, UtilChangeLogLevelRequest>(this, UtilChangeLogLevelRequest.class));

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
