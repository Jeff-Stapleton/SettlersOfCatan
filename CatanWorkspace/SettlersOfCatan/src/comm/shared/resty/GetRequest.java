package comm.shared.resty;

import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class GetRequest extends Request {

	public GetRequest(URL server, String path) throws RequestException
	{
		super("GET", server, path);
	}
	public GetRequest(URL server, String path, Map<String, String> queries) throws RequestException
	{
		super("GET", server, path + mapToQueryString(queries));
	}
	public GetRequest(URL server, String path, Map<String, String> queries, Map<String, String> headers) throws RequestException
	{
		super("GET", server, path + mapToQueryString(queries), headers);
	}
	
	private static String mapToQueryString(Map<String, String> queries)
	{
		StringBuilder query = new StringBuilder("?");
		
		for (Map.Entry<String, String> entry : queries.entrySet())
		{
			query.append(entry.getKey()).append("=").append(entry.getValue());
			query.append("&");
		}
		query.deleteCharAt(query.length() - 1);
		
		return query.toString();
	}
	
	
	@Override
	protected void _methodContraints(HttpsURLConnection con) {
		// Currently no additional constraints for a Get Request
	}

}
