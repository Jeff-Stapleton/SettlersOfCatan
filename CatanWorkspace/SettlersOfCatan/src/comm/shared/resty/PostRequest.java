package comm.shared.resty;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class PostRequest extends Request {
//	String _entityBody = null;
//
//	public PostRequest(URL server, String path) throws RequestException
//	{
//		super("POST", server, path);
//		init();
//	}
//	public PostRequest(URL server, String path, Map<String, String> headers) throws RequestException
//	{
//		super("POST", server, path, headers);
//		init();
//	}
//	
//	public void init() throws RequestException
//	{
//		if (getURL().getQuery() != null)
//		{
//			throw new RequestException("POST requests cannot have queries in the url");
//		}
//	}
//	
//	public void setBody(String string)
//	{
//		_entityBody = string;
//	}
//	
//	public <T> void setJsonBody(T object)
//	{
//		_entityBody = _gson.toJson(object);
//		setContentType("application/json");
//	}
//	
//	
//	@Override
//	protected void _methodContraints(HttpsURLConnection con) throws IOException
//	{
//		if (_entityBody == null)
//		{
//			return;
//		}
//		
//		if (_entityBody instanceof String)
//		{
//			DataOutputStream wr = null;
//			try
//			{
//				// Send post request
//				con.setDoOutput(true);
//				wr = new DataOutputStream(con.getOutputStream());
//				wr.writeBytes(_entityBody);
//				wr.flush();
//			}
//			catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//			finally
//			{
//				if (wr != null)
//				{
//					wr.close();
//					wr = null;
//				}
//			}
//		}
//	}

}
