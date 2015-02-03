package comm.shared.serialization;

import shared.locations.VertexLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class BuildCityRequest extends AbstractMovesRequest
{
	VertexLocation _vertexLocation;
	boolean _free;
	
	public BuildCityRequest(int playerIndex, VertexLocation vertexLocation, boolean free)
	{
		super("buildCity", playerIndex);
		
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
