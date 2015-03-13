package server.comm.request;
/**
 * The Class GamesListAIRequest, Adds an AI player to the current game
 * 
 * @pre
 * 	The caller has previously logged in to the server and joined a game
 * 	There is space in the game for another player (i.e., the game is not “full”).
 * 	The specified “AIType” is valid 
 * 
 * @post
 * 	The server returns an HTTP 200 success response.
 *  A new AI player of the specified type has been added to the current game. The server selected a name and color for the player.
 * 	
 */
public class GamesListAIRequest extends Request {

}
