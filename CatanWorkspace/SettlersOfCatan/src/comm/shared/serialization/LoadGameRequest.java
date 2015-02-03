package comm.shared.serialization;

public class LoadGameRequest
{
	private String _name;

	public LoadGameRequest(String name)
	{
		_name = name;
	}

	public String getName()
	{
		return _name;
	}
}
