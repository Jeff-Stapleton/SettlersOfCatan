package server.comm.request;

import server.comm.response.CatanModelResponse;
import shared.comm.ServerException;

/**
 * The Class MovesRollNumberRequest.
 * 
 * @pre
 * 	It is your turn
 * 	The client model�s status is �Rolling�
 * 
 * @post
 * 	The client model�s status is now in �Discarding� or �Robbing� or �Playing�
 * 	
 * 
 */
public class MovesMaritimeTradeRequest extends AbstractRequest<CatanModelResponse> {

	@Override
	public CatanModelResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
