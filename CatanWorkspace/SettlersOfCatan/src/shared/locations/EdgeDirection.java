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
}

