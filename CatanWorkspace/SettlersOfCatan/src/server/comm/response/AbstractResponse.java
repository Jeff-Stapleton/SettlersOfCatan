package server.comm.response;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import shared.comm.cookie.ICookie;

// TODOC: Auto-generated Javadoc
/**
 * The Class Response.
 */
public abstract class AbstractResponse implements IResponse
{
	private static final Logger log = Logger.getLogger(AbstractResponse.class);
	
	private static final String PATH = "Path=/;";
	
	private int responseCode = 500;
	private long responseLength = 0;
	
	private Map<String, String> headers = new HashMap<String, String>();
	
	protected AbstractResponse(int code, long length)
	{
		responseCode = code;
		responseLength = length;
		setContentType("text/plain");
	}

	@Override
	public String getContentType()
	{
		return headers.get("Content-Type");
	}
	
	public void setContentType(String contentType)
	{
		headers.put("Content-Type", contentType);
	}
	
	public void addCookie(ICookie cookie)
	{
		if (headers.get("Set-Cookie") == null)
		{
			headers.put("Set-Cookie", cookie.getCookie() + PATH);
		}
		else
		{
			String cookieString = headers.get("Set-Cookie");
			cookieString = cookieString.substring(0, cookieString.length() - PATH.length());
			cookieString = cookieString + cookie.getCookie() + PATH;
			headers.put("Set-Cookie", cookieString);
		}
		log.debug("Set-Cookie:" + headers.get("Set-Cookie"));
	}
	
	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public Map<String, String> getHeaders()
	{
		return headers;
	}
	
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

}
