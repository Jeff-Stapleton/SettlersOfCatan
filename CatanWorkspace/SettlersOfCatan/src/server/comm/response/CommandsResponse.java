package server.comm.response;

/**
 * The Class CommandsResponse, returned from GameGetCommandsRequest
 */
public class CommandsResponse extends JsonResponse
{

	protected CommandsResponse(String jsonBody)
	{
		super(200, jsonBody);
		// TODO Auto-generated constructor stub
	}

}
