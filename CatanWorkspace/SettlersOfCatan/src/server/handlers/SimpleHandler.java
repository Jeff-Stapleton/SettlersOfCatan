package server.handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

import server.Server;
import server.ServerGame;
import server.comm.response.IResponse;
import server.models.ServerUser;
import shared.Util;
import shared.comm.ServerException;
import shared.comm.cookie.GameCookie;
import shared.comm.cookie.PlayerCookie;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class SimpleHandler implements HttpHandler
{
	private static final Logger log = Logger.getLogger(SimpleHandler.class);

	private Gson gson = new Gson();
	
	public PlayerCookie getPlayerCookie(HttpExchange exchange) throws IOException
	{
		log.trace("Getting player cookie");
		if (exchange.getRequestHeaders().containsKey("Cookie"))
		{
			log.trace("Has a cookie");
			for (String s : exchange.getRequestHeaders().get("Cookie"))
			{
				s.trim();
				log.trace("Parsing cookie. raw=\"" + s + "\"");
				for (String s2 : s.split(";(?=(?:[^\"']*\"[^\"]*\"|[^\"']*'[^']*')*[^\"']*$)")) // Split on semicolons not in quotes
				{
					s.trim();
					log.trace("cookie k-v pair=\"" + s2 + "\"");
					String key = URLDecoder.decode(s2.split("=")[0], "utf-8").trim();
					
					if (key.equals("catan.user"))
					{
						PlayerCookie cookie = gson.fromJson(URLDecoder.decode(s2.substring(s2.indexOf('=') + 1), "utf-8").trim(), PlayerCookie.class);
						log.trace("Found player cookie for \"" + cookie.getName() + "\"");
						return cookie;
					}
				}
			}
		}
		log.trace("No player cookie found");
		return null;
	}
	
	public GameCookie getGameCookie(HttpExchange exchange) throws IOException
	{
		if (exchange.getRequestHeaders().containsKey("Cookie"))
		{
			log.trace("Has a cookie");
			for (String s : exchange.getRequestHeaders().get("Cookie"))
			{
				s.trim();
				log.trace("Parsing cookie. raw=\"" + s + "\"");
				for (String s2 : s.split(";(?=(?:[^\"']*\"[^\"]*\"|[^\"']*'[^']*')*[^\"']*$)")) // Split on semicolons not in quotes
				{
					s.trim();
					log.trace("cookie k-v pair=\"" + s2 + "\"");
					String key = URLDecoder.decode(s2.split("=")[0], "utf-8").trim();
					
					if (key.equals("catan.game"))
					{
						return new GameCookie(Integer.valueOf(URLDecoder.decode(s2.substring(s2.indexOf('=') + 1), "utf-8").trim()));
					}
				}
			}
		}
		return null;
	}
	
	public <T> T getRequest(HttpExchange exchange, Class<T> clazz)
	{

		log.trace("Handling user exhange");
		InputStreamReader isr = null;
		JsonReader jreader = null;
		T request = null;
		try
		{
			isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
			jreader = new JsonReader(isr);
			request = gson.fromJson(jreader, clazz);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			Util.safeClose(jreader);
			Util.safeClose(isr);
		}
		
		return request;
	}

	protected void addResponseHeaders(HttpExchange exchange, IResponse response) throws IOException
	{
		assert (response != null);
		
		// Set the content type
		exchange.getResponseHeaders().set("Content-Type", response.getContentType());
		
		if (response.getHeaders().containsKey("Set-Cookie"))
		{
			log.trace("Setting cookies");
			exchange.getResponseHeaders().set("Set-Cookie", response.getHeaders().get("Set-Cookie"));
		}
	}

	protected void sendResponse(HttpExchange exchange, IResponse response)
	{	
		assert (response != null);
		OutputStream os = null;
		try
		{
			os = exchange.getResponseBody();
			
			log.trace("Sending " + response.getResponseCode() + " length:" + response.getResponseLength());
			exchange.sendResponseHeaders(response.getResponseCode(), response.getResponseLength());
			os.write(response.getResponseBody());
		}
		catch(IOException e)
		{
			System.err.println("Error in sending response to client");
			e.printStackTrace();
		}
		finally
		{
			if (os != null)
			{
				log.trace("Closing the reponse body");
				try {
					os.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	public ServerUser verifyUser(HttpExchange exchange, Server server) throws ServerException
	{
		log.trace("verifying user");
		PlayerCookie player = null;
		try {
			player = this.getPlayerCookie(exchange);
		} catch (IOException e)
		{
			throw new ServerException("Unable to parse player cookie");
		}
		
		if (player == null)
		{
			log.trace("No player cookie found");
			throw new ServerException("Error: User must be logged (no cookie found)");
		}
		else if (!server.getServerLobby().verifyUser(player.getName(), player.getPassword()))
		{
			log.trace("Invalid cookie credentials");
			throw new ServerException("Error: Invalid user credentials");
		}
		
		return server.getServerLobby().getUser(player.getPlayerId());
	}

	protected ServerGame getGame(HttpExchange exchange, Server server) throws ServerException
	{
		GameCookie cookie = null;
		try {
			cookie = getGameCookie(exchange);
		} catch (IOException e)
		{
			log.error("Error while parsing game cookie", e);
			throw new ServerException("Could not parse game cookie");
		}
		if (cookie == null)
		{
			log.error("Error: no game cookie found");
			throw new ServerException("Join a game before getting a model");
		}
		
		return server.getServerLobby().getGame(cookie.getId());
	}
	
}
