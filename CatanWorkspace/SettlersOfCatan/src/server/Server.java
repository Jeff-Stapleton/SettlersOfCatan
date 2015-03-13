package server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import server.comm.ReqResHandler;
import server.comm.request.UserLoginRequest;
import server.comm.response.UserResponse;
import shared.Util;

import com.sun.net.httpserver.HttpServer;

public class Server {
	private static final int DEFAULT_SERVER_PORT_NUMBER = 8081;
	private static final int DEFAULT_MAX_WAITING_CONNECTIONS = 10;
	
	private Integer serverPortNumber = null;
	private Integer maxWaitingConnections = null;
	private File dataFolder = null;

	private HttpServer server;
	
	/**
	 * Starts a server on the default port
	 */
	public Server() {
		init(DEFAULT_SERVER_PORT_NUMBER, DEFAULT_MAX_WAITING_CONNECTIONS);
	}
	
	/**
	 * Starts a server on the given port
	 * @param port the port to start the server listening on
	 */
	public Server(int port) {
		init(port, DEFAULT_MAX_WAITING_CONNECTIONS);
	}

	/**
	 * Starts a server on the given port with the max connections specified
	 * @param port the port to start the server listening on
	 * @param maxConnections the maximum number of connections for the server
	 */
	public Server(int port, int maxConnections)
	{
		init(port, maxConnections);
	}
	
	private void init(int port, int maxConnections)
	{
		serverPortNumber = port;
		maxWaitingConnections = maxConnections;
	}
	
	/**
	 * Run the server. Connect to a port and begin listening for traffic. 
	 * Create and set up contexts with the handlers for each operation
	 * the server needs.
	 */
	public void run() {
		// TODO Initialize database or data storage here
		
		try {
			server = HttpServer.create(new InetSocketAddress(serverPortNumber),
											maxWaitingConnections);
			
			Map<String, String> env = new HashMap<String,String>();
			env.put("server.port", serverPortNumber.toString());
			
			Util.setEnv(env);
			
			dataFolder = new File(Config.getConfig().getProperty(Config.DATA_FOLDER));
		} 
		catch (IOException e) {
			System.err.println("Could not create HTTP server: " + e.getMessage());
			e.printStackTrace();
			return;
		}

		server.setExecutor(null); // use the default executor

		// TODO: Add on all of the url specifiers
		//server.createContext("/user/login", new ReqResHandler<UserResponse,UserLoginRequest>());

		server.start();
	}
	
	/**
	 * Stop the server. This has a 1000ms timeout set.
	 */
	public void stop() {
		server.stop(1000);
	}
	
	/**
	 * Start a server on the default port or a port specified on the command line
	 * @param args a port can be specified as the first parameter
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
