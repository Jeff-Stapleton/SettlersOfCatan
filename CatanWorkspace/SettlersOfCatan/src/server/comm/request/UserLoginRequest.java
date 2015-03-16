package server.comm.request;

import server.comm.response.IResponse;
import server.comm.response.UserResponse;
import shared.comm.ServerException;

// TODO: Auto-generated Javadoc
/**
 * The Class UserLoginRequest, Logs the caller in to the server, and sets their catan.user HTTP cookie.
 * 
 * @pre
 * 	username is not null
 * 	password is not null
 * 
 * @post
 * 	The server returns an HTTP 200 success response with �Success� in the body
 * 	The HTTP response headers set the catan.user cookie to contain the identity of the logged�in player. The cookie uses �Path=/�, and its value contains a url�encoded JSON object 
 */
public class UserLoginRequest extends Request<UserResponse> {

	/* (non-Javadoc)
	 * @see server.comm.request.IRequest#getResponse()
	 */
	@Override
	public UserResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
