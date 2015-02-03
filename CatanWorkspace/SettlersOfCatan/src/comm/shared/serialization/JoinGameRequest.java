package comm.shared.serialization;

public class JoinGameRequest {
	int _id;
	String _color;
	
	public JoinGameRequest(int id, String color)
	{
		_id = id;
		_color = color;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public String getColor()
	{
		return _color;
	}
}
