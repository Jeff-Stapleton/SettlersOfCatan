package server.comm.request;

import java.util.List;
import java.util.Map;

import server.Server;
import server.comm.cookie.ICookie;
import server.comm.response.IResponse;
import shared.comm.ServerException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


// TODO: Auto-generated Javadoc
/**
 * The Interface IRequest.
 *
 * @param <Response> the generic type
 */
public interface IRequest<Response extends IResponse> {
	
	/**
	 * Generate a response from information in the database.
	 * Upon entering this function, the database has an open
	 * transaction. Upon leaving this function, the database
	 * changes will be commited and the transaction closed.
	 *
	 * @return the response
	 * @throws ServerException the server exception
	 */
	public Response getResponse() throws ServerException;
	
	/**
	 * Load from exchange.
	 *
	 * @param exchange the exchange
	 */
	public static void loadFromExchange(HttpExchange exchange)
	{
		
	}
	
	/**
	 * Execute.
	 *
	 * @param server the server
	 */
	public static void execute(Server server)
	{
		
	}
	
	/**
	 * Adds the cookie.
	 *
	 * @param cookie the cookie
	 */
	public static void addCookie(ICookie cookie)
	{
		
	}
	
	/**
	 * Gets the cookies.
	 *
	 * @return the cookies
	 */
	public static List<ICookie> getCookies()
	{
		return null;
	}
	
	/**
	 * Sets the header.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public static void setHeader(String key, String value)
	{
		
	}
	
	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public static Map<String,String> getHeaders()
	{
		return null;
		
	}
}
