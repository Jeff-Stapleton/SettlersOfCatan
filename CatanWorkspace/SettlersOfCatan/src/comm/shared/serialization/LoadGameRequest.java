package comm.shared.serialization;

public class LoadGameRequest
{
	private String name;

	public LoadGameRequest(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}
}
