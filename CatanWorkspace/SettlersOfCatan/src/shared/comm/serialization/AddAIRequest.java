package shared.comm.serialization;

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
