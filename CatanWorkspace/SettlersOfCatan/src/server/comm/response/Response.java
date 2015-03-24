package server.comm.response;

import java.util.Map;

import com.sun.net.httpserver.Headers;

// TODOC: Auto-generated Javadoc
/**
 * The Class Response.
 */
public abstract class Response implements IResponse
{
	int responseCode = 500;
	long responseLength = 0;
	String contentType = "text/plain";
	
	protected Response(int code, long length)
	{
		responseCode = code;
		responseLength = length;
	}
	
	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public Map<String, String> getHeaders()
	{
		// TODO: ("Set-Cookie", URLEncoder.encode(getCookie().toString(), "UTF-8"));

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
	
//	/**
//	 * Gets the cookies.
//	 *
//	 * @return the cookies
//	 */
//	public List<ICookie> getCookies()
//	{
//		return null;
//	}
//	
//	/**
//	 * Adds the cookie.
//	 *
//	 * @param cookie the cookie
//	 */
//	public void addCookie(ICookie cookie)
//	{
//		
//	}
	
	public void setResponseCode(int responseCode)
	{
		this.responseCode = responseCode;
	}

	/**
	 * Get the response code
	 * @return the response code
	 */
	@Override
	public int getResponseCode()
	{
		return responseCode;
	}

	/**
	 * Get the bit length of the response
	 * @return the bit-length response
	 */
	@Override
	public long getResponseLength()
	{
		return responseLength;
	}

	@Override
	public String getContentType()
	{
		return contentType;
	}

}
