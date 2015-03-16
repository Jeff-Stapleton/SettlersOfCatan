package shared.comm.serialization;

import shared.locations.HexLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class SoldierRequest extends AbstractMovesRequest
{
	int victimIndex;
	HexLocation location;
	
	public SoldierRequest(int playerIndex, int victimIndex, HexLocation location)
	{
		super("Soldier", playerIndex);
		
		this.victimIndex = victimIndex;
		this.location = location;
	}
	
	public int getVictimIndex()
	{
		return victimIndex;
	}
	
	public HexLocation getLocation()
	{
		return location;
	}
}
