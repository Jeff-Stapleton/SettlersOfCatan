package server.comm.response;

import java.util.HashMap;
import java.util.Map;

// TODOC: Auto-generated Javadoc
/**
 * The Class Response.
 */
public abstract class AbstractResponse implements IResponse
{
	private int responseCode = 500;
	private long responseLength = 0;
	private String contentType = "text/plain";
	
	private Map<String, String> headers = new HashMap<String, String>();
	
	protected AbstractResponse(int code, long length)
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
		return headers;
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

	protected void setResponseLength(int length) {
		responseLength = length;
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
	
	public void setContentType(String contentType)
	{
		this.contentType = contentType;
	}

}
