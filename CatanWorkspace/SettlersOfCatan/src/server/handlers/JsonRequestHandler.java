package server.handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpExchange;

import server.comm.response.MessageResponse;
import shared.Util;
import shared.comm.ServerException;
import shared.comm.cookie.ICookie;

public abstract class JsonRequestHandler<T> extends AbstractHandler
{
	private static final Logger log = Logger.getLogger(JsonRequestHandler.class);
	
	Gson gson = new Gson();
	Class<T> clazz = null;
	
	protected JsonRequestHandler(Class<T> clazz)
	{
		this.clazz = clazz;
	}

	@Override
	protected void handleRequest(HttpExchange exchange, Map<String, ICookie> cookies) throws ServerException
	{
		log.trace("Executing create game");
		InputStreamReader isr = null;
		JsonReader jreader = null;
		try
		{
			isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
			jreader = new JsonReader(isr);
			T request = gson.fromJson(jreader, clazz);
			
			executeRequest(request, cookies);
		}
		catch (ServerException e)
		{
			e.printStackTrace();
			setResponse(new MessageResponse(400, e.getMessage()));
		}
		catch (IOException e)
		{
			log.error("Could not read the request body");
			e.printStackTrace();
			setResponse(new MessageResponse(400, "Invalid request"));
		}
		finally
		{
			Util.safeClose(jreader);
			Util.safeClose(isr);
		}
	}
	
	protected abstract void executeRequest(T request, Map<String, ICookie> cookies) throws ServerException;

}
