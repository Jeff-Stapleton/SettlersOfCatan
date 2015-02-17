package shared.comm.serialization;

import shared.locations.VertexLocation;

public class VertexLocationRequest {
	int x;
	int y;
	String direction;
	
	public VertexLocationRequest(VertexLocation buildingLocation) {
		x = buildingLocation.getHexLoc().getX();
		y = buildingLocation.getHexLoc().getY();
		direction = buildingLocation.getDir().toString();
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
