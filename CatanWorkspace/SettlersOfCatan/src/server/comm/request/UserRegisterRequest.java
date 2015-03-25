package server.comm.request;

import org.apache.log4j.Logger;

import com.sun.net.httpserver.HttpExchange;

import server.comm.response.IResponse;
import server.comm.response.UserResponse;
import shared.comm.ServerException;

/**
 * The Class UserRegisterRequest, Creates a new user account and Logs the caller in to the server as the new user, and sets their catan.user HTTP cookie.
 * 
 * @pre
 * 	username is not null
 * 	password is not null
 * 	The specified username is not already in use.
 * 
 * @post
 * 	A new user account has been created with the specified username and password.
 * 	The server returns an HTTP 200 success response with �Success� in the body
 * 	The HTTP response headers set the catan.user cookie to contain the identity of the logged�in player. The cookie uses �Path=/�, and its value contains a url�encoded JSON object
 */
public class UserRegisterRequest extends AbstractRequest<UserResponse>
{
	private static final Logger log = Logger.getLogger(UserRegisterRequest.class.getName());
	
	/**
	 * Load from exchange.
	 *
	 * @param exchange the exchange
	 */
	@Override
	public void loadFromExchange(HttpExchange exchange)
	{
		super.loadFromExchange(exchange);
	}

	@Override
	public UserResponse getResponse() throws ServerException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
