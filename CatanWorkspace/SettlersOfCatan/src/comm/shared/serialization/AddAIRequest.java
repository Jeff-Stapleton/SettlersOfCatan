package comm.shared.serialization;

public class AddAIRequest
{
	private String _AIType;

	public AddAIRequest(String aiType)
	{
		_AIType = aiType;
	}
	
	public String getAIType()
	{
		return _AIType;
	}

}
