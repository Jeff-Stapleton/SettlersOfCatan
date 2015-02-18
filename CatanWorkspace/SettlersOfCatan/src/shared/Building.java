package shared;

import shared.locations.VertexLocation;


public class Building {
	private int owner;
	private VertexLocation location;
	
	public Building(int owner, VertexLocation location)
	{
		this.owner = owner;
		this.location = location;
	}

	/*
	 * Get the location of the building
	 * 
	 * @Return the location
	 */
	public VertexLocation getLocation() {
		return location;
	}

	/*
	 * Set the location of the building
	 * 
	 * @Return void
	 */
	public void setLocation(VertexLocation location) {
		this.location = location;
	}

	/*
	 * Return the Building Owner
	 * 
	 * @Return Player index
	 */
	public int getOwner() {
		return owner;
	}

	/*
	 * Sets the Building Owner
	 * 
	 * @Return void
	 */
	public void setOwner(int owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("owner : ").append(owner).append(",\n");
		string.append("location(!x&y not in obj!) : ").append(location.toString()).append("\n");
		
		string.append("}");
		
		return string.toString();
	}
}
