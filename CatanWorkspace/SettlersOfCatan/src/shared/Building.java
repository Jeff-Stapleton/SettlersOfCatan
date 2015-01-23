
public class Building {
	private int owner;
	private VertexLocation location = new VertexLocation();

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
}
