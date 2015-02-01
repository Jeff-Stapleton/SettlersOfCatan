package shared;

import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.locations.EdgeDirection;


public class Port {

	private PortType type;
	private HexLocation location;
	private EdgeDirection direction;
	private int ratio;
	
	public Port(PortType type, HexLocation location, EdgeDirection direction, int ratio)
	{
		this.type = type;
		this.location = location;
		this.direction = direction;
		this.ratio = ratio;		
	}
	
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
	public EdgeDirection getDirection() {
		return direction;
	}
	
	/*
	 * Set the direction of the port
	 * 
	 * @Return void
	 */
	public void setDirection(EdgeDirection direction) {
		this.direction = direction;
	}
	
	/*
	 * Get the ratio of the port
	 *
	 * @Return the port ratio 
	 */
	public int getRatio() {
		return ratio;
	}
	
	/*
	 * Set the ratio of the port
	 * 
	 * @Return void
	 */
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
}
