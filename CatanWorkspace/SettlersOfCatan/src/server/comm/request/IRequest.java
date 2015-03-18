package server.comm.request;

import java.util.List;
import java.util.Map;

import server.Server;
import server.comm.cookie.ICookie;
import server.comm.response.IResponse;
import shared.comm.ServerException;

import com.sun.net.httpserver.HttpExchange;


// TODO: Auto-generated Javadoc
/**
 * The Interface IRequest.
 *
 * @param <Response> the generic type
 */
public interface IRequest<Resp extends IResponse>
{	
	/**
	 * Generate a response from information in the database.
	 * Upon entering this function, the database has an open
	 * transaction. Upon leaving this function, the database
	 * changes will be commited and the transaction closed.
	 *
	 * @return the response
	 * @throws ServerException the server exception
	 */
	public Resp getResponse() throws ServerException;
	
	/**
	 * Load from exchange.
	 *
	 * @param exchange the exchange
	 */
	public void loadFromExchange(HttpExchange exchange);
	
	/**
	 * Execute.
	 *
	 * @param server the server
	 */
	public void execute(Server server);
	
	/**
	 * Adds the cookie.
	 *
	 * @param cookie the cookie
	 */
	public void addCookie(ICookie cookie);
	
	/**
	 * Gets the cookies.
	 *
	 * @return the cookies
	 */
	public List<ICookie> getCookies();
	
	/**
	 * Sets the header.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setHeader(String key, String value);
	
	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public Map<String,String> getHeaders();
}
