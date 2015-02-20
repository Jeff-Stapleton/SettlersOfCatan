package shared;

import shared.definitions.PortType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.EdgeDirection;


public class Port {

	private PortType type;
	private EdgeLocation location;
	private int ratio;
	
	public Port(PortType type, EdgeLocation location, int ratio)
	{
		this.type = type;
		this.location = location;
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
	public EdgeLocation getLocation() {
		return location;
	}
	
	/*
	 * Set the Port location
	 * 
	 * @Return void
	 */
	public void setLocation(EdgeLocation location) {
		this.location = location;
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
	
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("ratio : ").append(ratio).append(",\n");
		string.append("resource(!type! !missing when 'three'!) : ").append(type).append(",\n");
		string.append("direction : ").append(direction).append(",\n");
		string.append("location : ").append(location).append("\n");
		
		string.append("}");
		
		return string.toString();
	}
}
