package comm.shared;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;
import comm.shared.RestCom.SRequest;
import comm.shared.serialization.CredentialsRequest;

public class RestCom {
	private String _server = "localhost:8081";
	private Gson _gson = null;
	
	public RestCom(String serverURL)
	{
		_server = serverURL;
	}
	
	public RestCom(String serverURL, Gson gson)
	{
		_server = serverURL;
		_gson = gson;
	}
	
	public SRequest GET(String serverPath) throws IOException
	{
		return new SRequest(_server, serverPath, "GET");
	}
	
	public SRequest POST(String serverPath) throws IOException
	{
		return new SRequest(_server, serverPath, "POST");
	}
	
	public class SRequest
	{
		HttpsURLConnection _con = null;
		String _urlString = "";
		StringBuilder _requestBody = null;
		
		protected SRequest(String serverURL, String serverPath, String method) throws IOException
		{
			_urlString = serverURL + serverPath;
			URL url = new URL(_urlString);
			_con = (HttpsURLConnection) url.openConnection();
			
			_con.setRequestMethod(method);
		}
		
		public void addHeader(String key, String value)
		{
			_con.setRequestProperty(key, value);
		}
		
		public void addHeaders(Map<String, String> headers)
		{
			for (Map.Entry<String, String> entry : headers.entrySet())
			{
				_con.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}
		
		public HttpResponse getResponse()
		{
			HttpResponse response = null;
			try
			{
				_con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(_con.getOutputStream());
				wr.writeBytes(requestBody);
				wr.flush();
				wr.close();
				
				response = new HttpResponse(_urlString, _con.getResponseCode());
				
				BufferedReader in = new BufferedReader(new InputStreamReader(_con.getInputStream()));
				
				StringBuffer responseBody = new StringBuffer();
				String inputLine;
				while (null != (inputLine = in.readLine())) {
					responseBody.append(inputLine);
				}
				in.close();
				
				response.setResponseBody(responseBody.toString());
				
				return response;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return response;
			}
			finally
			{
				if (null != _con)
				{
					_con.disconnect();
					_con = null;
				}
			}
		}

		public <T> SRequest appendGsonBody(T obj)
		{
			_requestBody.append(_gson.toJson(obj));
			
			return this;
		}
	}
	
	public class HttpResponse
	{
		private String _url;
		private int _responseCode;
		private String _responseBody = "";
		
		protected HttpResponse(String urlString, int responseCode)
		{
			_url = urlString;
			_responseCode = responseCode;
		}
		
		protected void setResponseBody(String responseBody)
		{
			_responseBody = responseBody;
		}
		
		public String getUrl()
		{
			return _url;
		}
		
		public int getResponseCode()
		{
			return _responseCode;
		}
		
		public String getResponseBody()
		{
			return _responseBody;
		}
	}

}
