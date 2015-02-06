package comm.shared.serialization;

public class SaveGameRequest
{
	private int id;
	private String name;
	
	public SaveGameRequest(int id, String name)
	{
		this.id = id;
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}
}
