package server.comm.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import server.Server;
//import server.comm.cookie.ICookie;
import server.comm.response.IResponse;
import shared.comm.ServerException;

import com.sun.net.httpserver.HttpExchange;

/**
 * The Class Request.
 */
public abstract class Request<Resp extends IResponse> implements IRequest<Resp>
{
	private static final Logger log = Logger.getLogger(Request.class.getName());
	private Server server;
	private Map<String, List<String>> headers = null;
	
	public Resp getResponse() throws ServerException
	{
		return null;
	}
	
	/**
	 * Load from exchange.
	 *
	 * @param exchange the exchange
	 */
	public void loadFromExchange(HttpExchange exchange)
	{
		log.trace("Loading from httpexchange");
		// Check for the cookie header
		if (!exchange.getRequestHeaders().isEmpty())
		{
			log.trace("Loading headers from exchange");
			headers = new HashMap<String, List<String>>();
			exchange.getRequestHeaders().putAll(headers);
		}
		
		if (headers.containsKey("Cookie"))
		{
			log.trace("Cookies found. Loading cookies.");
			//TODO: Load the cookies in
		}
		
	}
	
	/**
	 * Execute.
	 *
	 * @param server the server
	 */
	public void execute(Server server)
	{
		
	}
	
//	/**
//	 * Adds the cookie.
//	 *
//	 * @param cookie the cookie
//	 */
//	public void addCookie(ICookie cookie)
//	{
//		
//	}
//	
//	/**
//	 * Gets the cookies.
//	 *
//	 * @return the cookies
//	 */
//	public List<ICookie> getCookies()
//	{
//		return null;
//	}
	
	/**
	 * Sets the header.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setHeader(String key, String value)
	{
		
	}
	
	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public Map<String,String> getHeaders()
	{
		return null;
		
	}

	public void setServer(Server server)
	{
		this.server = server;
	}
	
	public Server getServer()
	{
		return server;
	}


}
