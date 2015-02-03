package shared.definitions;

import java.awt.Color;

public enum CatanColor
{
	RED, ORANGE, YELLOW, BLUE, GREEN, PURPLE, PUCE, WHITE, BROWN;
	
	private Color color;
	private String string;
	
	static
	{
		RED.color = new Color(227, 66, 52);
		ORANGE.color = new Color(255, 165, 0);
		YELLOW.color = new Color(253, 224, 105);
		BLUE.color = new Color(111, 183, 246);
		GREEN.color = new Color(109, 192, 102);
		PURPLE.color = new Color(157, 140, 212);
		PUCE.color = new Color(204, 136, 153);
		WHITE.color = new Color(223, 223, 223);
		BROWN.color = new Color(161, 143, 112);
		
		RED.string = "red";
		ORANGE.string = "orange";
		YELLOW.string = "yellow";
		BLUE.string = "blue";
		GREEN.string = "green";
		PURPLE.string = "purple";
		PUCE.string = "puce";
		WHITE.string = "white";
		BROWN.string = "brown";
	}
	
	public Color getJavaColor()
	{
		return color;
	}
	
	public String toString()
	{
		return string;
	}
}

