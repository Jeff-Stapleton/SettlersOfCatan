package shared;

import java.util.Observable;

import shared.definitions.PortType;
import shared.locations.HexLocation;
import shared.locations.EdgeDirection;

public class Port extends Observable
{

	private PortType resource;
	private HexLocation location;
	private EdgeDirection direction;
	private int ratio;
	
	public Port(PortType type, HexLocation location, EdgeDirection direction, int ratio)
	{
		this.resource = type;
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
		return resource;
	}
	
	/*
	 * Set the Port type
	 * 
	 * @Return void
	 */
	public void setType(PortType type) {
		this.resource = type;
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
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("ratio : ").append(ratio).append(",\n");
		string.append("resource(!type! !missing when 'three'!) : ").append(resource).append(",\n");
		string.append("direction : ").append(direction).append(",\n");
		string.append("location : ").append(location).append("\n");
		
		string.append("}");
		
		return string.toString();
	}

	public boolean updateFrom(Port rhs)
	{
		boolean updated = false;

		updated = updated | location.updateFrom(rhs.location);
		
		if (resource != rhs.resource)
		{
			resource = rhs.resource;
			updated = true;
		}
		
		if (direction != rhs.direction)
		{
			direction = rhs.direction;
			updated = true;
		}
		
		if (ratio != rhs.ratio)
		{
			ratio = rhs.ratio;
			updated = true;
		}
		
		if (updated)
		{
			setChanged();
			notifyObservers();
		}
		
		return updated;
	}
}