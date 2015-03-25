package server.comm.request;

import server.comm.response.CatanModelResponse;
import shared.comm.ServerException;

/**
 * The Class MovesBuyDevCardRequest.
 * 
 * @pre
 * 	You have the required resources (1 ore, 1 wheat, 1 sheep)
 * 	There are dev cards left in the deck
 * 
 * @post
 * 	You have a new card
 * 	If it is a monument card, it has been added to your old devcard hand
 * 	If it is a non�monument card, it has been added to your new devcard hand
 */
public class MovesBuyDevCardRequest extends AbstractRequest<CatanModelResponse> {

	@Override
	public CatanModelResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
