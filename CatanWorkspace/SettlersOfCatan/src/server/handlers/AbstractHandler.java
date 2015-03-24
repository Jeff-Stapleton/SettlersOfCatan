package server.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import server.comm.response.IResponse;
import server.comm.response.MessageResponse;
import shared.comm.ServerException;
import shared.comm.cookie.CookieFactory;
import shared.comm.cookie.ICookie;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class AbstractHandler implements HttpHandler
{
	private static final Logger log = Logger.getLogger(AbstractHandler.class);
	private IResponse response = null;

	protected abstract void handleRequest(HttpExchange exchange, Map<String, ICookie> cookies) throws ServerException;

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try
		{
			log.trace("Handling games exhange");
			try
			{
				HashMap<String, ICookie> cookies = new HashMap<String, ICookie>();
				// Load in cookies from the client
				// Example of user cookie and game cookie:
				// Cookie: catan.user={"name":"Sam","password":"sam","playerID":0}; catan.game=01
				if (exchange.getRequestHeaders().containsKey("Cookie"))
				{
					log.trace("Has a cookie");
					for (String s : exchange.getRequestHeaders().get("Cookie"))
					{
						s.trim();
						log.trace("Parsing cookie. Raw=\"" + s + "\"");
						for (String s2 : s.split(";")) // s.split(";(?=(?:[^\"']*\"[^\"]*\"|[^\"']*'[^']*')*[^\"']*$)")) // Split on semicolons not in quotes
						{
							s.trim();
							log.trace("cookie k-v pair: " + s2);
							String key = URLDecoder.decode(s2.split("=")[0], "utf-8");
							String value = URLDecoder.decode(s2.substring(s2.indexOf('=') + 1), "utf-8");
							key = key.trim();
							value = value.trim();
							cookies.put(key, CookieFactory.parseCookie(key, value));
						}
					}
					log.trace(cookies.entrySet().size() + " Cookies Loaded");
				}
				
				handleRequest(exchange, cookies);
			}
			catch (ServerException e)
			{
				log.error("Error while handling request: " + e.getMessage());
				e.printStackTrace();
				response = new MessageResponse(400, e.getMessage());
			}
			
			OutputStream os = null;
			try
			{
				// Set the content type
				exchange.getResponseHeaders().set("Content-Type", response.getContentType());
				if (response != null)
				{
					log.trace("Sending \"" + response.getContentType() + "\" " + response.getResponseCode() + " length: " + response.getResponseLength());
					exchange.sendResponseHeaders(response.getResponseCode(), response.getResponseLength());
					
					log.trace("There are " + response.getHeaders().size() + " headers to send");
					if (response.getHeaders().size() > 0)
					{
						log.trace("Adding " + response.getHeaders().size() + " headers");
						for (Map.Entry<String,String> e : response.getHeaders().entrySet())
						{
							exchange.getResponseHeaders().add(e.toString(), e.toString());
						}
					}
	
					log.trace("Writing response body:\n" + new String(response.getResponseBody()));
					os = exchange.getResponseBody();
					os.write(response.getResponseBody());
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
				log.error("Error in sending response to client");
				e.printStackTrace();
			}
			finally
			{
				if (os != null)
				{
					log.trace("Closing output stream");
					os.close();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected IResponse getResponse()
	{
		return response;
	}
	
	protected void setResponse(IResponse response)
	{
		this.response = response;
	}

}
