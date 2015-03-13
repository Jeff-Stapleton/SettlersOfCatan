package server.comm.request;
/**
 * The Class GamesResetRequest, Clears out the command history of the current game.
 * @pre 
 * 	The caller has previously logged in to the server and joined a game
 * 
 * 
 * @post
 * 	The game’s command history has been cleared out
 *  The game’s players have NOT been cleared out
 *  The server returns an HTTP 200 success response.
 * 	The body contains the game’s updated client model JSON
 * 	
 */
public class GamesResetRequest extends Request {

}
