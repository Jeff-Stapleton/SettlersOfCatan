package comm.shared.serialization;

public class PlayerResponse
{
	int id;
	String name;
	String color;
	
	public PlayerResponse(int id, String name, String color)
	{
		this.id = id;
		this.name = name;
		this.color = color;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getColor()
	{
		return color;
	}
}
