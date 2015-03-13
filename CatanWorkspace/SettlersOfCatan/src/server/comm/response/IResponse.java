package server.comm.response;

import java.util.List;
import java.util.Map;

import server.comm.cookie.ICookie;

/**
 * The Interface IResponse.
 */
public interface IResponse {
	public static Map<String, String> getHeaders()
	{
		return null;
	}
	
	public static void setHeader(String key, String value)
	{
		
	}
	
	public static List<ICookie> getCookies()
	{
		return null;
	}
	
	public static void addCookie(ICookie cookie)
	{
		
	}
	
}
