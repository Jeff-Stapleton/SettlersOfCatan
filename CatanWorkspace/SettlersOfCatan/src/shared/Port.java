package shared;

import shared.definitions.PortType;
import shared.locations.HexLocation;


public class Port {

	private PortType type;
	private HexLocation location;
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
	public HexLocation getLocation() {
		return location;
	}
	
	/*
	 * Set the Port location
	 * 
	 * @Return void
	 */
	public void setLocation(HexLocation location) {
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
