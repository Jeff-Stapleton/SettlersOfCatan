package shared.comm.serialization;

import shared.locations.EdgeLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class BuildRoadRequest extends AbstractMovesRequest
{
	EdgeLocationRequest roadLocation;
	boolean free;
	
	public BuildRoadRequest(int playerIndex, EdgeLocation roadLocation, boolean free)
	{
		super("buildRoad", playerIndex);
		
		this.roadLocation = new EdgeLocationRequest(roadLocation);
		this.free = free;
	}
	
	public EdgeLocationRequest getRoadLocation()
	{
		return roadLocation;
	}
	
	public boolean isFree() { return getFree(); }
	public boolean getFree()
	{
		return free;
	}
}
