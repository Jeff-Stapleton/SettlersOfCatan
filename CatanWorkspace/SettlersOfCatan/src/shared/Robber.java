package shared;

import shared.locations.HexLocation;

public class Robber extends HexLocation {
//	private HexLocation location;
//	private HexType type;
//	private Integer number;
	
	public Robber()
	{
		super(0, 0);
	}
	
	public Robber(int x, int y)
	{
		super(x, y);
	}
	
//	/*
//	 * This method gets the current location of the Robber
//	 * 
//	 * @Return the location of the robber
//	 */
//	public HexLocation getLocation()
//	{
//		return location;		
//	}
//	
//	/*
//	 * This method changes the current location of the Robber
//	 * 
//	 * @Param HexLocation location
//	 * 
//	 * @Return void
//	 */
//	public void setLocation(HexLocation location)
//	{
//		this.location = location;
//	}
	
	/*
	 * This method changes the current location of the Robber
	 * 
	 * @Param x the x coordinate
	 * @Param y the y coordinate
	 * 
	 * @Return void
	 */
	public void setLocation(int x, int y)
	{
		setX(x);
		setY(y);
	}
	
	@Override
	public String toString() {
		return "{\n"
				+ "x : " + getX() + ",\n"
				+ "y : " + getY() + "\n"
				+ "}";
//		return "{      (!should just have x and y!)\n"
//				+ "location : " + location.toString() + ",\n"
//				+ "type : " + type.toString() + ",\n"
//				+ "number : " + number + ",\n"
//				+ "}";
//		
		
	}
}
