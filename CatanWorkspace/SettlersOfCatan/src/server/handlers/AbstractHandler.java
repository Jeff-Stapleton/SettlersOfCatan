package server.handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import server.Server;
import server.comm.cookie.ICookie;
import server.comm.response.IResponse;
import server.comm.response.MessageResponse;
import shared.Util;
import shared.comm.serialization.CredentialsRequest;
import shared.comm.serialization.PlayerCookie;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class AbstractHandler implements HttpHandler
{
	private static final Logger log = Logger.getLogger(AbstractHandler.class);
	private Gson gson = new Gson();
	private Server server = null;
	private HashMap<String, String> cookies = new HashMap<String, String>();
	private IResponse response = null;

	public AbstractHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		log.trace("Handling games exhange");
		InputStreamReader isr = null;
		JsonReader jreader = null;
		try
		{
			// Load in cookies from the client
			// catan.user={"name":"Sam","password":"sam","playerID":0};catan.game=NN
			if (exchange.getRequestHeaders().containsKey("Cookie"))
			{
				log.trace("Has a cookie");
				for (String s : exchange.getRequestHeaders().get("Cookie"))
				{
					s.trim();
					for (String s2 : s.split(";")) // s.split(";(?=(?:[^\"']*\"[^\"]*\"|[^\"']*'[^']*')*[^\"']*$)")) // Split on semicolons not in quotes
					{
						s.trim();
						String key = URLDecoder.decode(s2.split("=")[0], "utf-8");
						String value = URLDecoder.decode(s2.substring(s2.indexOf('=') + 1), "utf-8");
						cookies.put(key, value);
					}
				}
				log.trace(cookies.entrySet().size() + " Cookies Loaded");
			}
			
			loadResponse(exchange);
//			initFromExchange(exchange);
//			isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
//			jreader = new JsonReader(isr);
//			readRequestFrom(jreader);
			//TODO: put this in readRequest: request = gson.fromJson(jreader, <:RequestClass:>.class);
			
//			response = handleRequest();
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
		
		OutputStream os = null;
		try
		{
			// Set the content type
			exchange.getResponseHeaders().set("Content-Type", response.getContentType());
			if (response != null)
			{
				log.trace("Sending " + response.getResponseCode() + " (" + response.getResponseLength() + ")");
				exchange.sendResponseHeaders(response.getResponseCode(), response.getResponseLength());
				
				// Add Headers from the response
				for (Map.Entry<String,String> e : response.getHeaders().entrySet())
				{
					exchange.getResponseHeaders().add(e.toString(), e.toString());
				}

				// Write the response body
				exchange.getResponseBody().write(response.getResponseBody());
				
				// Close the exchange
				exchange.getResponseBody().close();
			}
			else
			{
				log.trace("sending response error message");
				exchange.sendResponseHeaders(400, "Failed to login - bad username or password..".getBytes().length);
				os = exchange.getResponseBody();
				os.write("Failed to login - bad username or password..".getBytes());
			}
			log.trace("sent response");
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
				os.close();
			}
		}
	}

//	protected abstract void initFromExchange(HttpExchange exchange);
//	protected abstract IResponse handleRequest();
	protected abstract void loadResponse(HttpExchange exchange);
	
	protected IResponse getResponse()
	{
		return response;
	}
	
	protected void setResponse(IResponse response)
	{
		this.response = response;
	}

}
