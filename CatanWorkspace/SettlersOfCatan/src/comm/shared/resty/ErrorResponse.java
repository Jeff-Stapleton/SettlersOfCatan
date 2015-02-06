package comm.shared.resty;

import java.util.Map;

public class ErrorResponse extends Response {
	String _errorMessage = null;
	
	protected ErrorResponse(int responseCode, String responseMessage, Map<String, String> headers, String errorMessage)
	{
		super(responseCode, responseMessage, headers);
		_errorMessage = errorMessage;
	}
	
	public String errorMessage()
	{
		return _errorMessage;
	}

}
