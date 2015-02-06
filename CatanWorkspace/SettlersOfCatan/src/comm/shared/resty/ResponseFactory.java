package comm.shared.resty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class ResponseFactory {

//	public static Response buildResponse(HttpsURLConnection con) throws IOException
//	{
//		Response response = null;
//		
//		int responseCode = con.getResponseCode();
//		String responseMessage = con.getResponseMessage();
//		Map<String, List<String>> headers = con.getHeaderFields();
//		
//		
//		if (responseCode == 200)
//		{
//			response = new Response(con);
//		}
//		else
//		{
//			String errorMessage = readStringBody(con);
//			
//			response = new ErrorResponse(responseCode, responseMessage, headers, errorMessage);
//		}
//		
//		return response;
//	}
//	
//	public static String readStringBody(HttpsURLConnection con) throws IOException
//	{
//		StringBuffer response = new StringBuffer();
//		BufferedReader in = null;
//		try
//		{
//			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		
//			String inputLine;
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//			throw new IOException("Error while reading response body");
//		}
//		finally
//		{
//			in.close();
//		}
//		
//		return response.toString();
//	}

}
