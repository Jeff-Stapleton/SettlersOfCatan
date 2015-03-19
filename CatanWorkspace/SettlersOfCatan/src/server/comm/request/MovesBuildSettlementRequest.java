package server.comm.request;

import server.comm.response.CatanModelResponse;
import shared.Building;
import shared.CatanModel;
import shared.Hex;
import shared.Player;
import shared.comm.ServerException;
import shared.definitions.HexType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

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
public class MovesBuildSettlementRequest extends Request<CatanModelResponse> {

	@Override
	public CatanModelResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
