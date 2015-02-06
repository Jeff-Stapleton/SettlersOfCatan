package comm.shared.serialization;

public class JoinGameRequest {
	int id;
	String color;
	
	public JoinGameRequest(int id, String color)
	{
		this.id = id;
		this.color = color;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getColor()
	{
		return color;
	}
}
