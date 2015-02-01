package shared;
import java.util.ArrayList;

import shared.locations.HexLocation;
import shared.definitions.HexType;

public class Robber {
	private HexLocation location;
	private HexType type;
	private Integer number;
	
	/*
	 * This method gets the current location of the Robber
	 * 
	 * @Return the location of the robber
	 */
	public HexLocation getLocation()
	{
		return location;		
	}
	
	/*
	 * This method changes the current location of the Robber
	 * 
	 * @Param HexLocation location
	 * 
	 * @Return void
	 */
	public void setLocation(HexLocation location)
	{
		this.location = location;
	}
	
	/*
	 * Once the robber has been placed steal resources from a robbable player
	 * 
	 * @Param ArrayList<int> Players
	 * 
	 * @Return void
	 */
	public ArrayList<Integer> steal()
	{
		return null;
	}

}
