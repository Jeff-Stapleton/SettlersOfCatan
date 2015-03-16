package server.comm.request;

import java.util.List;
import java.util.Map;

import javax.xml.ws.Response;

import server.Server;
import server.comm.cookie.ICookie;
import server.comm.response.IResponse;
import shared.comm.ServerException;

import com.sun.net.httpserver.HttpExchange;

/**
 * The Class Request.
 */
public abstract class Request<Resp extends IResponse> implements IRequest<Resp>
{
	
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
		
	}
	
	/**
	 * Execute.
	 *
	 * @param server the server
	 */
	public   void execute(Server server)
	{
		
	}
	
	/**
	 * Adds the cookie.
	 *
	 * @param cookie the cookie
	 */
	public   void addCookie(ICookie cookie)
	{
		
	}
	
	/**
	 * Gets the cookies.
	 *
	 * @return the cookies
	 */
	public   List<ICookie> getCookies()
	{
		return null;
	}
	
	/**
	 * Sets the header.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public   void setHeader(String key, String value)
	{
		
	}
	
	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public   Map<String,String> getHeaders()
	{
		return null;
		
	}


}
