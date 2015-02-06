package comm.shared;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.google.gson.Gson;
import comm.shared.resty.GetRequest;
import comm.shared.resty.PostRequest;
import comm.shared.resty.Request.RequestException;

public class RestCom {
	private static URL _localhost;

	static {
		try
		{
			_localhost = new URL("http://localhost:8081");
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
			_localhost = null;
		}
	}
	
	private URL _server;
	private Gson _gson;
	private Map<String, String> _headers;
	
	public RestCom()
	{
		init(_localhost, null, null);
	}
	
	public RestCom(URL server)
	{
		init(server, null, null);
	}
	
	public RestCom(URL server, Gson gson)
	{
		init(server, gson, null);
	}
	
	public RestCom(URL server, Gson gson, Map<String, String> headers)
	{
		init(server, gson, headers);
	}
	
	private void init(URL server, Gson gson, Map<String, String> headers)
	{
		_server = server;
		_gson = gson;
		_headers = headers;
	}
	
	public GetRequest createGetRequest(String path) throws RequestException
	{
		GetRequest request = new GetRequest(_server, path, _headers);
		if (_gson != null)
		{
			request.setGson(_gson);
		}
		return request;
	}
	
	public PostRequest createPostRequest(String path) throws RequestException
	{
		PostRequest request = new PostRequest(_server, path, _headers);
		if (_gson != null)
		{
			request.setGson(_gson);
		}
		return request;
	}

	public URL getServer()
	{
		return _server;
	}
	
	public Gson getGson()
	{
		return _gson;
	}
	
	public RestCom addHeader(String key, String value)
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
}
