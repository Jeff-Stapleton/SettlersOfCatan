package comm.shared.serialization;

public class SaveGameRequest
{
	private int _id;
	private String _name;
	
	public SaveGameRequest(int id, String name)
	{
		_id = id;
		_name = name;
	}
	
	public int getId()
	{
		return _id;
	}

	public String getName()
	{
		return _name;
	}
}
