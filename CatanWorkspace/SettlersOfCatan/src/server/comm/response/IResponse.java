package server.comm.response;

import java.util.List;
import java.util.Map;

import server.comm.cookie.ICookie;

/**
 * The Interface IResponse.
 */
public interface IResponse {
	public Map<String, String> getHeaders();
	
	public void setHeader(String key, String value);
	
	public List<ICookie> getCookies();
	
	public void addCookie(ICookie cookie);
	
}
