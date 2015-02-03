package comm.shared.serialization;

import shared.locations.EdgeLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class BuildRoadRequest extends AbstractMovesRequest
{
	EdgeLocation _roadLocation;
	boolean _free;
	
	public BuildRoadRequest(int playerIndex, EdgeLocation roadLocation, boolean free)
	{
		super("buildRoad", playerIndex);
		
		_roadLocation = roadLocation;
		_free = free;
	}
	
	public EdgeLocation getRoadLocation()
	{
		return _roadLocation;
	}
	
	public boolean isFree() { return getFree(); }
	public boolean getFree()
	{
		return _free;
	}
}
