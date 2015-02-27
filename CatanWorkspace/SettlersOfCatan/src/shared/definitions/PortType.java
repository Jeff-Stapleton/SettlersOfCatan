package shared.definitions;

import com.google.gson.annotations.SerializedName;

public enum PortType
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
	ORE,
	@SerializedName("three")
	THREE;
	
	private String string;
	
	static
	{
		WOOD.string = "wood";
		BRICK.string = "brick";
		SHEEP.string = "sheep";
		WHEAT.string = "wheat";
		ORE.string = "ore";
		THREE.string = "three";
	}
	
	public String toString()
	{
		return string;
	}
}

