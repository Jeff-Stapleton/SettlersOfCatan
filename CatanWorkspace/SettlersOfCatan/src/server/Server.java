package server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.comm.ReqResHandler;
import server.comm.request.GameModelRequest;
import server.comm.request.GameResetRequest;
import server.comm.request.GamesCreateRequest;
import server.comm.request.GamesJoinRequest;
import server.comm.request.GamesListRequest;
import server.comm.request.GamesLoadRequest;
import server.comm.request.GamesSaveRequest;
import server.comm.request.MovesBuyDevCardRequest;
import server.comm.request.MovesFinishTurnRequest;
import server.comm.request.MovesRobPlayerRequest;
import server.comm.request.MovesRollNumberRequest;
import server.comm.request.MovesSendChatRequest;
import server.comm.request.UserLoginRequest;
import server.comm.request.UserRegisterRequest;
import server.comm.response.CatanModelResponse;
import server.comm.response.CommandsResponse;
import server.comm.response.GameInfoResponse;
import server.comm.response.GameInfosResponse;
import server.comm.response.GameResponse;
import server.comm.response.MessageResponse;
import server.comm.response.UserResponse;
import shared.Util;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import com.sun.net.httpserver.HttpServer;

// TODO: Auto-generated Javadoc
/**
 * The Class Server.
 */
public class Server {

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

	/**
	 * Starts a server on the default port.
	 */
	public Server() {
		init(DEFAULT_SERVER_PORT_NUMBER, DEFAULT_MAX_WAITING_CONNECTIONS);
	}

	/**
	 * Starts a server on the given port.
	 * 
	 * @param port
	 *            the port to start the server listening on
	 */
	public Server(int port) {
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
	public Server(int port, int maxConnections) {
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
	private void init(int port, int maxConnections) {
		serverPortNumber = port;
		maxWaitingConnections = maxConnections;
	}

	/**
	 * Run the server. Connect to a port and begin listening for traffic. Create
	 * and set up contexts with the handlers for each operation the server
	 * needs.
	 */
	public void run() {
		// TODO Initialize database or data storage here

		try {
			server = HttpServer.create(new InetSocketAddress(serverPortNumber),
					maxWaitingConnections);

			Map<String, String> env = new HashMap<String, String>();
			env.put("server.port", serverPortNumber.toString());

			Util.setEnv(env);

			// May need this for saving stuff later
			dataFolder = new File(Config.getConfig().getProperty(
					Config.DATA_FOLDER));
		} catch (IOException e) {
			System.err.println("Could not create HTTP server: "
					+ e.getMessage());
			e.printStackTrace();
			return;
		}

		server.setExecutor(null); // use the default executor

		server.createContext("/docs/api/data", new Handlers.JSONAppender(""));
		server.createContext("/docs/api/view", new Handlers.BasicFile(""));

		server.createContext("/user/login",
				new ReqResHandler<UserResponse, UserLoginRequest>());
		server.createContext("/user/register",
				new ReqResHandler<UserResponse, UserRegisterRequest>());

		server.createContext("/games/list",
				new ReqResHandler<GameInfosResponse, GamesListRequest>());
		server.createContext("/games/create",
				new ReqResHandler<GameInfoResponse, GamesCreateRequest>());
		server.createContext("/games/join",
				new ReqResHandler<GameResponse, GamesJoinRequest>());
		server.createContext("/games/save",
				new ReqResHandler<MessageResponse, GamesSaveRequest>());
		server.createContext("/games/load",
				new ReqResHandler<MessageResponse, GamesLoadRequest>());

		server.createContext("/game/model",
				new ReqResHandler<CatanModelResponse, GameModelRequest>());
		server.createContext("/game/reset",
				new ReqResHandler<CatanModelResponse, GameResetRequest>());
		// server.createContext("/game/commands", new
		// ReqResHandler<CommandsResponse,GameCommandsRequest>());

		server.createContext("/moves/sendChat",
				new ReqResHandler<CatanModelResponse, MovesSendChatRequest>());
		server.createContext("/moves/rollNumber",
				new ReqResHandler<CatanModelResponse, MovesRollNumberRequest>());
		server.createContext("/moves/robPlayer",
				new ReqResHandler<CatanModelResponse, MovesRobPlayerRequest>());
		server.createContext("/moves/finishTurn",
				new ReqResHandler<CatanModelResponse, MovesFinishTurnRequest>());
		server.createContext("/moves/buyDevCard",
				new ReqResHandler<CatanModelResponse, MovesBuyDevCardRequest>());
		// server.createContext("/moves/Year_of_Plenty", new
		// ReqResHandler<CatanModelResponse,MovesYearOfPlentyRequest>());
		// server.createContext("/moves/Road_Building", new
		// ReqResHandler<CatanModelResponse,MovesRoadBuildingRequest>());
		// server.createContext("/moves/Soldier", new
		// ReqResHandler<CatanModelResponse,MovesSoldierRequest>());
		// server.createContext("/moves/Monument", new
		// ReqResHandler<CatanModelResponse,MovesMonumentRequest>());
		// server.createContext("/moves/buildRoad", new
		// ReqResHandler<CatanModelResponse,MovesBuildRoadRequest>());
		// server.createContext("/moves/buildCity", new
		// ReqResHandler<CatanModelResponse,MovesBuildCityRequest>());
		// server.createContext("/moves/buildSettlement", new
		// ReqResHandler<CatanModelResponse,MovesBuildSettlementRequest>());
		// server.createContext("/moves/offerTrade", new
		// ReqResHandler<CatanModelResponse,MovesOfferTradeRequest>());
		// server.createContext("/moves/acceptTrade", new
		// ReqResHandler<CatanModelResponse,MovesAcceptTradeRequest>());
		// server.createContext("/moves/maritimeTrade", new
		// ReqResHandler<CatanModelResponse,MovesMaritimeTradeRequest>());
		// server.createContext("/moves/discardCards", new
		// ReqResHandler<CatanModelResponse,MovesDiscardCardsRequest>());
		//
		// server.createContext("/util/changeLogLevel", new
		// ReqResHandler<MessageResponse,UtilChangeLogLevelRequest>());

		server.start();
	}

	/**
	 * Stop the server. This has a 1000ms timeout set.
	 */
	public void stop() {
		server.stop(1000);
	}

	/**
	 * Start a server on the default port or a port specified on the command
	 * line.
	 * 
	 * @param args
	 *            a port can be specified as the first parameter
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			new Server().run();
		} else if (args.length == 1) {
			new Server(Integer.valueOf(args[0])).run();
		} else {
			System.err.println("Usage: server [port]");
		}
	}
}


