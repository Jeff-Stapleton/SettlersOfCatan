package server.comm.response;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.Headers;

import server.comm.cookie.ICookie;

/**
 * The Interface IResponse.
 */
public interface IResponse
{
	// TODO: Old methods. These are not really used. Remove them later
	public Map<String, String> getHeaders();
//	public void setHeader(String key, String value);
//	public List<ICookie> getCookies();
//	public void addCookie(ICookie cookie);

	// New methods
	public int getResponseCode();
	public long getResponseLength();
//	public void writeTo(OutputStream responseBody) throws IOException;
//	public void addHeadersTo(Headers responseHeaders);
	public String getContentType();
	public byte[] getResponseBody();
	
}
