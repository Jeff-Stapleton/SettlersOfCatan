package server.comm.response;

import java.util.List;
import java.util.Map;

import server.comm.cookie.ICookie;

public abstract class Response implements IResponse {
	
	public Map<String, String> getHeaders()
	{
		return null;
	}
	
	public void setHeader(String key, String value)
	{
		
	}
	
	public List<ICookie> getCookies()
	{
		return null;
	}
	
	public void addCookie(ICookie cookie)
	{
		
	}

}
