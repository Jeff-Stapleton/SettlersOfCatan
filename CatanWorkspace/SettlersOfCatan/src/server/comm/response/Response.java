package server.comm.response;

import java.util.List;
import java.util.Map;

import server.comm.cookie.ICookie;

// TODOC: Auto-generated Javadoc
/**
 * The Class Response.
 */
public abstract class Response implements IResponse {
	
	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public Map<String, String> getHeaders()
	{
		return null;
	}
	
	/**
	 * Sets the header.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public void setHeader(String key, String value)
	{
		
	}
	
	/**
	 * Gets the cookies.
	 *
	 * @return the cookies
	 */
	public List<ICookie> getCookies()
	{
		return null;
	}
	
	/**
	 * Adds the cookie.
	 *
	 * @param cookie the cookie
	 */
	public void addCookie(ICookie cookie)
	{
		
	}

}
