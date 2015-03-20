package shared.locations;

import com.google.gson.annotations.SerializedName;

public enum EdgeDirection
{
	@SerializedName("NW")
	NorthWest,

	@SerializedName("N")
	North,

	@SerializedName("NE")
	NorthEast,

	@SerializedName("SE")
	SouthEast,

	@SerializedName("S")
	South,

	@SerializedName("SW")
	SouthWest;
	
	private EdgeDirection opposite;
	private String string;
	
	static
	{
		NorthWest.opposite = SouthEast;
		North.opposite = South;
		NorthEast.opposite = SouthWest;
		SouthEast.opposite = NorthWest;
		South.opposite = North;
		SouthWest.opposite = NorthEast;
		
		NorthWest.string = "NW";
		North.string = "N";
		NorthEast.string = "NE";
		SouthEast.string = "SE";
		South.string = "S";
		SouthWest.string = "SW";
	}
	
	public EdgeDirection getOppositeDirection()
	{
		return opposite;
	}
	
	public String toString()
	{
		return string;
	}
	
	public static EdgeDirection fromString(String direction)
	{
		
		switch (direction)
		{
			case "NW":
				return EdgeDirection.NorthWest;
			case "N":
				return EdgeDirection.North;
			case "NE":
				return EdgeDirection.NorthEast;
			case "S":
				return EdgeDirection.South;
			case "SW":
				return EdgeDirection.SouthWest;
			case "SE":
				return EdgeDirection.SouthEast;
			default:
				return null;
			
		}
		
	}
}

