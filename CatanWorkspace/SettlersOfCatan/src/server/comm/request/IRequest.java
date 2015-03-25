package server.comm.request;

//import server.comm.cookie.ICookie;
import server.comm.response.IResponse;
import shared.comm.ServerException;

import com.sun.net.httpserver.HttpExchange;


// TODOC: Auto-generated Javadoc
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
	 * @param server 
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
	
}
