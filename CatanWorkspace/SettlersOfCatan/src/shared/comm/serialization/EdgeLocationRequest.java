package shared.comm.serialization;

import shared.locations.EdgeLocation;

public class EdgeLocationRequest {
	int x;
	int y;
	String direction;
	
	public EdgeLocationRequest(EdgeLocation roadLocation) {
		x = roadLocation.getX();
		y = roadLocation.getY();
		direction = roadLocation.getDir().toString();
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
