package server.comm.request;

import org.apache.log4j.Logger;

import server.comm.response.IResponse;
import server.comm.response.MessageResponse;
import server.comm.response.UserResponse;
import shared.comm.ServerException;
import shared.comm.serialization.CredentialsRequest;

/**
 * The Class UserLoginRequest, Logs the caller in to the server, and sets their catan.user HTTP cookie.
 * 
 * @pre
 * 	username is not null
 * 	password is not null
 * 
 * @post
 * 	The server returns an HTTP 200 success response with "Success" in the body
 * 	The HTTP response headers set the catan.user cookie to contain the identity of the logged-in player.
 *  The cookie uses "Path=/", and its value contains a url-encoded JSON object 
 */
public class UserLoginRequest extends JsonRequest<MessageResponse, CredentialsRequest>
{
	private static final Logger log = Logger.getLogger(UserLoginRequest.class);
	
	/* (non-Javadoc)
	 * @see server.comm.request.IRequest#getResponse()
	 */
	@Override
	public MessageResponse getResponse() throws ServerException
	{
		log.trace("Verifying user credentials");
		MessageResponse response = getServer().getServerLobby().getUserFacade().login(getRequestObject());
		log.trace("User validation result : " + (response != null));
		response.setContentType("text/plain");
		return response;
	}

	@Override
	protected Class<CredentialsRequest> getBodyClass()
	{
		return CredentialsRequest.class;
	}

}
