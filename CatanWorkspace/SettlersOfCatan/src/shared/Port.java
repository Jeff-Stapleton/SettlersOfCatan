
public class Port {

	private PortType type = new PortType();
	private HexsLocation location = new HexLocation();
	private String direction;
	private Integer ratio;
	
	/*
	 * Gets the port type
	 *
	 * @Return The port type
	 */
	public PortType getType() {
		return type;
	}
	
	/*
	 * Set the Port type
	 * 
	 * @Return void
	 */
	public void setType(PortType type) {
		this.type = type;
	}
	
	/*
	 * Gets the Port location
	 * 
	 * @Returns the port location
	 */
	public HexsLocation getLocation() {
		return location;
	}
	
	/*
	 * Set the Port location
	 * 
	 * @Return void
	 */
	public void setLocation(HexsLocation location) {
		this.location = location;
	}
	
	/*
	 * Get the direction of the port
	 * 
	 * @Return portDirection
	 */
	public String getDirection() {
		return direction;
	}
	
	/*
	 * Set the direction of the port
	 * 
	 * @Return void
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/*
	 * Get the ratio of the port
	 *
	 * @Return the port ratio 
	 */
	public Integer getRatio() {
		return ratio;
	}
	
	/*
	 * Set the ratio of the port
	 * 
	 * @Return void
	 */
	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}
}
