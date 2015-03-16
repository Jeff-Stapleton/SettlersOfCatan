package server.comm.request;

import server.comm.response.MessageResponse;
import shared.comm.ServerException;

/**
 * The Class GamesSaveRequest, This method is for testing and debugging purposes. When a bug is found, you can use the /games/save method to save the state of the game to a file, and attach the file to a bug report. A developer can later restore the state of the game when the bug occurred by loading the previously saved file using the /games/load method. Game files are saved to and loaded from the server's saves/ directory.
 * 
 * @pre 
 * 	The specified game ID is valid
 *  The specified file name is valid (i.e., not null or empty)
 * @post
 *  The server returns an HTTP 200 success response with �Success� in the body
 *  The current state of the specified game (including its ID) has been saved to the specified file name in the server�s saves/ directory
 */
public class GamesSaveRequest extends Request<MessageResponse> {

	@Override
	public MessageResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
