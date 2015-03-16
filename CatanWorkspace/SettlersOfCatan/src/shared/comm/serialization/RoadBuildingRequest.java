package shared.comm.serialization;

import shared.locations.EdgeLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class RoadBuildingRequest extends AbstractMovesRequest
{
	EdgeLocationRequest spot1;
	EdgeLocationRequest spot2;
	
	public RoadBuildingRequest(int playerIndex, EdgeLocation spot1, EdgeLocation spot2)
	{
		super("Road_Building", playerIndex);
		
		this.spot1 = new EdgeLocationRequest(spot1);
		this.spot2 = new EdgeLocationRequest(spot2);
	}
	
	public EdgeLocationRequest getSpot1()
	{
		return spot1;
	}
	
	public EdgeLocationRequest getSpot2()
	{
		return spot2;
	}
}
