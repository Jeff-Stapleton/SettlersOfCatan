package server.comm.response;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import server.comm.cookie.ICookie;

// TODO: Auto-generated Javadoc
/**
 * The Class Response.
 */
public abstract class JsonResponse extends Response {
		
	/**
	 * Sets the json body.
	 *
	 * @param <T> the generic type
	 * @param obj the new json body
	 */
	public <T> void setJsonBody(T obj)
	{
		
	}
	
	/**
	 * Sets the json body.
	 *
	 * @param <T> the generic type
	 * @param gson the gson
	 * @param obj the obj
	 */
	public <T> void setJsonBody(Gson gson, T obj)
	{
		
	}

}
