package comm.shared.serialization;

public class PlayerResponse
{
	int _id;
	String _name;
	String _color;
	
	public PlayerResponse(int id, String name, String color)
	{
		_id = id;
		_name = name;
		_color = color;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public String getColor()
	{
		return _color;
	}
}
