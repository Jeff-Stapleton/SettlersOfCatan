package server.comm.response;

import java.lang.reflect.Type;

import org.apache.log4j.Logger;

import client.view.data.GameInfo;
import client.view.data.PlayerInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

// TODOC: Auto-generated Javadoc
/**
 * The Class Response.
 */
public class JsonResponse extends AbstractResponse
{
	private static final Logger log = Logger.getLogger(JsonResponse.class);
	
    public static final Type GAME_INFO_TYPE = new TypeToken<GameInfo>(){}.getType();
    public static final Type GAME_INFO_ARRAY_TYPE = new TypeToken<GameInfo[]>(){}.getType();
	
	private Gson gson = new GsonBuilder()
								.registerTypeAdapter(GameInfo.class, new GameInfoSerializer())
								.registerTypeAdapter(GameInfo[].class, new GameInfoArraySerializer())
								.setPrettyPrinting().create();
	String jsonBody = null;
	
	public JsonResponse(int responseCode)
	{
		super(responseCode, -1);
		setContentType("application/json");
	}
	
	public JsonResponse(int responseCode, String jsonBody)
	{
		super(responseCode, jsonBody.length());
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
		setResponseLength(jsonBody.length());
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
		setResponseLength(jsonBody.length());
	}
	
	/**
	 * Sets the json body.
	 *
	 * @param <T> the generic type
	 * @param gson the gson
	 * @param obj the obj
	 */
	public <T> void setJsonBody(T obj, Type tType)
	{
		jsonBody = gson.toJson(obj, tType);
		setResponseLength(jsonBody.length());
	}
	
	@Override
	public byte[] getResponseBody()
	{
		log.trace("Returning byte response body. Was: \n" + jsonBody);
		return jsonBody.getBytes();
	}
	
	public class GameInfoSerializer implements JsonSerializer<GameInfo>
	{

		@Override
		public JsonElement serialize(GameInfo src, Type typeOfSrc, JsonSerializationContext context)
		{
	        // object (for which this serializer is registered)
	        JsonObject object = new JsonObject();

	        object.addProperty("title", src.getTitle());
	        object.addProperty("id", src.getId());
	        
	        JsonArray playerArray = new JsonArray();
	        for (PlayerInfo player : src.getPlayers().toArray(new PlayerInfo[4]))
	        {
	        	if (player == null)
	        	{
	        		playerArray.add(new JsonObject());
	        	}
	        	else
	        	{
	        		playerArray.add(context.serialize(player));
	        	}
	        }
	        
	        object.add("players", playerArray);
	        return object;
	        
	        //  Returned in the form:
			//  {
			//      "title": "Supergame",
			//      "id": 3,
			//      "players": [
			//          {},
			//          {},
			//          {},
			//          {}
			//      ]
			//  }
		}
		
	}

	public class GameInfoArraySerializer implements JsonSerializer<GameInfo[]>
	{

		@Override
		public JsonElement serialize(GameInfo[] src, Type typeOfSrc, JsonSerializationContext context)
		{
	        // object (for which this serializer is registered)
	        JsonArray object = new JsonArray();
	        
	        for (GameInfo game : src)
	        {
	        	object.add(context.serialize(game, GAME_INFO_TYPE));
	        }
	        
	        return object;
		}
		
	}
}
