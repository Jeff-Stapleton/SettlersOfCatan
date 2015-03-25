package server.comm.request;

import server.comm.response.CatanModelResponse;
import shared.comm.ServerException;

/**
 * The Class MovesRobPlayerRequest.
 * 
 * @pre
 * 	The robber is not being kept in the same location
 * 	If a player is being robbed (i.e., victimIndex != �1), the player being robbed has resource cards
 * 
 * @post
 * 	The player being robbed (if any) gave you one of his resource cards
 * 	The robber is in the new location
 * 
 */
public class MovesRobPlayerRequest extends AbstractRequest<CatanModelResponse> {

	@Override
	public CatanModelResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
