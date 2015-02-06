package comm.shared.resty;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class Response {

	int _code;
	String _message;

	Map<String, String> _headers = new HashMap<String, String>();
	
	Gson _gson = new Gson();
	
	protected Response(int responseCode, String responseMessage, Map<String, String> headers)
	{
		_code = responseCode;
		_message = responseMessage;
	}
	
	public int getResponseCode()
	{
		return _code;
	}
	
	public String getResponseMessage()
	{
		return _message;
	}
	
}
