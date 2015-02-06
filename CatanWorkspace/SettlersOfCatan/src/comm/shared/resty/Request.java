package comm.shared.resty;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public abstract class Request {
	String _method = "";
	URL _url;
	Map<String, String> _headers = new HashMap<String, String>();
	
	Gson _gson = new Gson();
	
	public Request(String method, URL server, String path) throws RequestException
	{
		init(method, server, path, null);
	}
	
	public Request(String method, URL server, String path, Map<String, String> headers) throws RequestException
	{
		init(method, server, path, headers);
	}
	
	private void init(String method, URL server, String path, Map<String, String> headers) throws RequestException
	{
		if (method.equals("GET") || method.equals("POST"))
		{
			throw new RequestException("Invalid method type \"" + method + "\"");
		}
		try
		{
			_url = new URL(server, path);
		}
		catch (MalformedURLException e)
		{
			throw new RequestException("Malformed URL");
		}
		if (headers != null)
		{
			_headers = headers;
		}
	}
	
	public Response getResponse()
	{
		HttpsURLConnection con = null;
		Response response = null;
		
		try
		{
			con = (HttpsURLConnection) _url.openConnection();
			con.setRequestMethod(_method);
			
			if (null != _headers)
			{
				for (Map.Entry<String, String> header : _headers.entrySet())
				{
					con.setRequestProperty(header.getKey(), header.getValue());
				}
			}
			
			_methodContraints(con);
			
			response = ResponseFactory.buildResponse(con);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (null != con)
			{
				con.disconnect();
				con = null;
			}
		}
		
		return response;
	}
	
	protected abstract void _methodContraints(HttpsURLConnection con) throws IOException;
	
	public class RequestException extends Exception
	{
		private static final long serialVersionUID = -6838394595703407949L;
		public RequestException(String message)
		{
			super(message);
		}
	}

	public Gson getGson()
	{
		return _gson;
	}
	
	public void setGson(Gson gson)
	{
		_gson = gson;
	}
	
	public String getMethod()
	{
		return _method;
	}
	
	public URL getURL()
	{
		return _url;
	}
	
	public Request addHeader(String key, String value)
	{
		_headers.put(key, value);
		return this;
	}
	
	public void setHeaders(Map<String, String> headers)
	{
		_headers = headers;
	}
	
	public Map<String, String> getHeaders()
	{
		return _headers;
	}
	
	public void setContentType(String contentType)
	{
		_headers.put("Content-Type", contentType);
	}
	
}
