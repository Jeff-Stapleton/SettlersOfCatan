package comm.shared.serialization;

import shared.locations.EdgeLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class BuildRoadRequest extends AbstractMovesRequest
{
	EdgeLocation roadLocation;
	boolean free;
	
	public BuildRoadRequest(int playerIndex, EdgeLocation roadLocation, boolean free)
	{
		super("buildRoad", playerIndex);
		
		this.roadLocation = roadLocation;
		this.free = free;
	}
	
	public EdgeLocation getRoadLocation()
	{
		return roadLocation;
	}
	
	public boolean isFree() { return getFree(); }
	public boolean getFree()
	{
		return free;
	}
}
