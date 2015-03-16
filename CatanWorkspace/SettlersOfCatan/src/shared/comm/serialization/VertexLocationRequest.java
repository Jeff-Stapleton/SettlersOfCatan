package shared.comm.serialization;

import shared.locations.VertexLocation;

public class VertexLocationRequest {
	int x;
	int y;
	String direction;
	
	public VertexLocationRequest(VertexLocation buildingLocation) {
		x = buildingLocation.getX();
		y = buildingLocation.getY();
		direction = buildingLocation.getDirection().toString();
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public String getDirection()
	{
		return direction;
	}
}
