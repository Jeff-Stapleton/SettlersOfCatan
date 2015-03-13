package server.comm.request;

/**
 * The Class GamesCreateRequest, creates a new game on the server
 * @pre 
 * 	Name cannot be null
 * 	randomTiles, randomNumbers, and randomPorts contain valid boolean values
 * 
 * @post
 * 	A new game with the specified properties has been created
 * 	The server returns an HTTP 200 success response
 * 	The body contains a JSON object describing the newly created game
 */
public class GamesCreateRequest extends Request {

}
