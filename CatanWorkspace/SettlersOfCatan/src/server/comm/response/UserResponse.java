package server.comm.response;

/**
 * The Class UserResponse, returned from UserRegisterRequest and UserLoginRequest
 */
public class UserResponse extends JsonResponse
{

	protected UserResponse(String jsonBody)
	{
		super(200, jsonBody);
		// TODO Auto-generated constructor stub
	}
	
}
