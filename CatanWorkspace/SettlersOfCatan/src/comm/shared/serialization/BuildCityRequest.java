package comm.shared.serialization;

import shared.locations.VertexLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class BuildCityRequest extends AbstractMovesRequest
{
	VertexLocation vertexLocation;
	boolean free;
	
	public BuildCityRequest(int playerIndex, VertexLocation vertexLocation, boolean free)
	{
		super("buildCity", playerIndex);
		
		this.vertexLocation = vertexLocation;
		this.free = free;
	}
	
	public VertexLocation getVertexLocation()
	{
		return vertexLocation;
	}
	
	public boolean isFree() { return getFree(); }
	public boolean getFree()
	{
		return free;
	}
}
