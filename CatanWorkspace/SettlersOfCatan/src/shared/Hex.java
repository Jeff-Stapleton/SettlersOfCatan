
public class Hex {
	private HexLocation location = new HexLocation();
	private HexType type = new HexType();
	private Integer number;
	
	/*
	 * Get the location of the Hex
	 * 
	 * @Return The hex location
	 */
	public HexLocation getLocation() {
		return location;
	}

	/*
	 * Sets the location of the hex 
	 * 
	 * @Return void
	 */
	public void setLocation(HexLocation location) {
		this.location = location;
	}

	/*
	 * Get the resource type of the hex
	 * 
	 * @Return the resource type
	 */
	public HexType getType() {
		return type;
	}

	/*
	 * Set the resource type of the hex
	 * 
	 * @Return void
	 */
	public void setType(HexType type) {
		this.type = type;
	}

	/*
	 * Return the roll number of the Hex
	 * 
	 * @Return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/*
	 * Set the roll number of thre Hex
	 * 
	 * @Return void
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/*
	 * check if the specified Hex has the robber placed on it
	 * 
	 * @Return hasRobber
	 */
	public Boolean hasRobber()
	{
		return true;
	}

}
