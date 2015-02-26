package shared.definitions;

public enum PortType
{
	
	WOOD, BRICK, SHEEP, WHEAT, ORE, THREE;
	
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

