package shared.definitions;

import com.google.gson.annotations.SerializedName;

public enum ResourceType
{
	@SerializedName("wood")
	WOOD,
	@SerializedName("brick")
	BRICK,
	@SerializedName("sheep")
	SHEEP,
	@SerializedName("wheat")
	WHEAT,
	@SerializedName("ore")
	ORE;

	private String string;
	
	static
	{
		WOOD.string = "wood";
		BRICK.string = "brick";
		SHEEP.string = "sheep";
		WHEAT.string = "wheat";
		ORE.string = "ore";
	}
	
	public String toString()
	{
		return string;
	}
}
