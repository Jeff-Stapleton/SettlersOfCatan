package server.comm.request;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import server.Server;
//import server.comm.cookie.ICookie;
import server.comm.response.IResponse;
import server.comm.response.MessageResponse;
import shared.Util;
import shared.comm.cookie.CookieFactory;
import shared.comm.cookie.ICookie;
import shared.comm.serialization.CredentialsRequest;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpExchange;

/**
 * The Class Request.
 */
public abstract class JsonRequest<Resp extends IResponse, ReqBodyType> extends AbstractRequest<Resp>
{
	private static final Logger log = Logger.getLogger(JsonRequest.class.getName());
	
	private static final Gson gson = new Gson();
	
	private Server server;
	private HashMap<String, ICookie> cookies = new HashMap<String, ICookie>();
	private Map<String, List<String>> headers = null;
	private ReqBodyType requestBody = null;
	
	/**
	 * Load from exchange.
	 *
	 * @param exchange the exchange
	 */
	public void loadFromExchange(HttpExchange exchange)
	{
		// Load the cookies and headers and things in super
		super.loadFromExchange(exchange);

		log.trace("Getting json request body object");
		InputStreamReader isr = null;
		JsonReader jreader = null;
		try
		{
			isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
			jreader = new JsonReader(isr);
			requestBody = gson.fromJson(jreader, getBodyClass());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			Util.safeClose(jreader);
			Util.safeClose(isr);
		}
	}
	
	public void setServer(Server server)
	{
		this.server = server;
	}
	
	protected Server getServer()
	{
		return server;
	}
	
	protected ReqBodyType getRequestObject()
	{
		return requestBody;
	}
	
	protected abstract Class<ReqBodyType> getBodyClass();

}
