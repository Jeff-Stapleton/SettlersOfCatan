package comm.shared.serialization;

import shared.locations.HexLocation;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class SoldierRequest extends AbstractMovesRequest
{
	int _victimIndex;
	HexLocation _location;
	
	public SoldierRequest(int playerIndex, int victimIndex, HexLocation location)
	{
		super("Soldier", playerIndex);
		
		_victimIndex = victimIndex;
		_location = location;
	}
	
	public int getVictimIndex()
	{
		return _victimIndex;
	}
	
	public HexLocation getLocation()
	{
		return _location;
	}
}
