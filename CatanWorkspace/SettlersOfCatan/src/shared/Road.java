package shared;
import shared.locations.EdgeLocation;

public class Road {
	private Integer owner;
	private EdgeLocation location;
	
	public Road(EdgeLocation location, Integer owner)
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
	public Integer getOwner() {
		return owner;
	}

	/*
	 * Sets the Roads Owner
	 * 
	 * @Return void
	 */
	public void setOwner(Integer owner) {
		this.owner = owner;
	}

}
