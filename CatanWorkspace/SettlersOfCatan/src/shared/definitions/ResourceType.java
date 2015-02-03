package shared.definitions;

public enum ResourceType
{
	WOOD, BRICK, SHEEP, WHEAT, ORE;

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
