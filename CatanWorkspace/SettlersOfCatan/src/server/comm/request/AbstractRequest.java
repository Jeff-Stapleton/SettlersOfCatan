package server.comm.request;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import server.Server;
//import server.comm.cookie.ICookie;
import server.comm.response.IResponse;
import shared.comm.cookie.CookieFactory;
import shared.comm.cookie.ICookie;

import com.sun.net.httpserver.HttpExchange;

/**
 * The Class Request.
 */
public abstract class AbstractRequest<Resp extends IResponse> implements IRequest<Resp>
{
	private static final Logger log = Logger.getLogger(AbstractRequest.class.getName());
	
	private Server server;
	private HashMap<String, ICookie> cookies = new HashMap<String, ICookie>();
//	private Map<String, List<String>> headers = null;
	
	/**
	 * Load from exchange.
	 *
	 * @param exchange the exchange
	 */
	public void loadFromExchange(HttpExchange exchange)
	{
		log.trace("Loading from httpexchange");
		try
		{
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
		}
		catch (IOException e)
		{
			log.error(e.getMessage());
			e.printStackTrace();
		}
		
//		log.trace("Pull off other headers.");
//		if (!exchange.getRequestHeaders().isEmpty())
//		{
//			log.trace("Loading headers from exchange");
//			headers = new HashMap<String, List<String>>();
//			exchange.getRequestHeaders().putAll(headers);
//		}
		
	}
	
	public void setServer(Server server)
	{
		this.server = server;
	}
	
	protected Server getServer()
	{
		return server;
	}

}
