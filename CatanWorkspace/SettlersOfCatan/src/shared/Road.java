package shared;
import shared.locations.EdgeLocation;

public class Road {
	private int owner;
	private EdgeLocation location;
	
	public Road(int owner, EdgeLocation location)
	{
		this.location = location;
		this.owner = owner;
	}

	/*
	 * Get the location of the road
	 * 
	 * @Return the location
	 */
	public EdgeLocation getLocation() {
		return location;
	}

	/*
	 * Set the location of the road
	 * 
	 * @Return void
	 */
	public EdgeLocation setLocation() {
		return location;
	}
	
	/*
	 * Return the Road Owner
	 * 
	 * @Return Player index
	 */
	public int getOwner() {
		return owner;
	}

	/*
	 * Sets the Roads Owner
	 * 
	 * @Return void
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}

}
