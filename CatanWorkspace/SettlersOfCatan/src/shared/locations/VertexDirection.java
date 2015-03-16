package shared.locations;

import com.google.gson.annotations.SerializedName;

public enum VertexDirection
{
	@SerializedName("W")
	West,
	
	@SerializedName("NW")
	NorthWest,
	
	@SerializedName("NE")
	NorthEast,
	
	@SerializedName("E")
	East,
	
	@SerializedName("SE")
	SouthEast,
	
	@SerializedName("SW")
	SouthWest;
	
	private VertexDirection opposite;
	private String string;
	
	static
	{
		West.opposite = East;
		NorthWest.opposite = SouthEast;
		NorthEast.opposite = SouthWest;
		East.opposite = West;
		SouthEast.opposite = NorthWest;
		SouthWest.opposite = NorthEast;
		
		West.string = "W";
		NorthWest.string = "NW";
		NorthEast.string = "NE";
		East.string = "E";
		SouthEast.string = "SE";
		SouthWest.string = "SW";
	}
	
	public VertexDirection getOppositeDirection()
	{
		return opposite;
	}
	
	public String toString()
	{
		return string;
	}
}

