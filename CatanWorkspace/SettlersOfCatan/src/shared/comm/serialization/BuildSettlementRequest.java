package shared.comm.serialization;

import shared.locations.VertexLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class BuildSettlementRequest extends AbstractMovesRequest
{
	VertexLocationRequest vertexLocation;
	boolean free;
	
	public BuildSettlementRequest(int playerIndex, VertexLocation vertexLocation, boolean free)
	{
		super("buildSettlement", playerIndex);
		
		this.vertexLocation = new VertexLocationRequest(vertexLocation);
		this.free = free;
	}
	
	public VertexLocationRequest getVertexLocation()
	{
		return vertexLocation;
	}
	
	public boolean isFree() { return getFree(); }
	public boolean getFree()
	{
		return free;
	}
}
