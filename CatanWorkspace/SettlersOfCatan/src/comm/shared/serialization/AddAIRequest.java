package comm.shared.serialization;

public class AddAIRequest {
	String AIType;
	
	public AddAIRequest(String aiType) {
		AIType = aiType;
	}

	public String getAIType()
	{
		return AIType;
	}

}
