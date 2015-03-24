package server.comm.response;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import server.comm.cookie.ICookie;

// TODOC: Auto-generated Javadoc
/**
 * The Class Response.
 */
public abstract class JsonResponse extends Response
{
	Gson gson = new Gson();
	String jsonBody = null;
	
	protected JsonResponse(String jsonBody)
	{
		super(200, jsonBody.length());
		setJsonBody(jsonBody);
	}

	/**
	 * Sets the json body.
	 *
	 * @param obj the new json body
	 */
	public void setJsonBody(String obj)
	{
		jsonBody = obj;
	}

	/**
	 * Sets the json body.
	 *
	 * @param <T> the generic type
	 * @param obj the new json body
	 */
	public <T> void setJsonBody(T obj)
	{
		jsonBody = gson.toJson(obj);
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
		jsonBody = gson.toJson(obj);
	}
	
	@Override
	public byte[] getResponseBody()
	{
		return jsonBody.getBytes();
	}

}
