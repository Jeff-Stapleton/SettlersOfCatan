package comm.shared.serialization;

import shared.locations.EdgeLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class RoadBuildingRequest extends AbstractMovesRequest
{
	EdgeLocation spot1;
	EdgeLocation spot2;
	
	public RoadBuildingRequest(int playerIndex, EdgeLocation spot1, EdgeLocation spot2)
	{
		super("Road_Building", playerIndex);
		
		this.spot1 = spot1;
		this.spot2 = spot2;
	}
	
	public EdgeLocation getSpot1()
	{
		return spot1;
	}
	
	public EdgeLocation getSpot2()
	{
		return spot2;
	}
}
