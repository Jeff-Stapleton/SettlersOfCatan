package server.command;

import shared.CatanModel;
import shared.comm.serialization.VertexLocationRequest;

public class BuildSettlementCommand implements ICommand<CatanModel>
{
	private String type;
	private int playerIndex;
	private VertexLocationRequest vertexLocation;
	/**
	 * Executes "Build Settlement", subtracts the resources from the instigator
	 * subtracts a city from unbuilt settlement pool and places it on a vertex location
	 *
	 * @pre The player has enough resources, enough settlements and is a valid vertex location
	 * @post completes the build settlement
	 * 
	 * @param a PlayerIndex, a Location, a isFreeBoolean
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
