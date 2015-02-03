package comm.shared.serialization;

import shared.locations.VertexLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class BuildSettlementRequest extends AbstractMovesRequest
{
	VertexLocation _vertexLocation;
	boolean _free;
	
	public BuildSettlementRequest(int playerIndex, VertexLocation vertexLocation, boolean free)
	{
		super("buildSettlement", playerIndex);
		
		_vertexLocation = vertexLocation;
		_free = free;
	}
	
	public VertexLocation getVertexLocation()
	{
		return _vertexLocation;
	}
	
	public boolean isFree() { return getFree(); }
	public boolean getFree()
	{
		return _free;
	}
}
