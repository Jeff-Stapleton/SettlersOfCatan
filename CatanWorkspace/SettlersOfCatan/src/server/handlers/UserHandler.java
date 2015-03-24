package server.handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import server.Server;
import server.comm.response.MessageResponse;
import shared.Util;
import shared.comm.cookie.PlayerCookie;
import shared.comm.serialization.CredentialsRequest;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class UserHandler implements HttpHandler
{
	private static final Logger log = Logger.getLogger(UserHandler.class);
	Gson gson = new Gson();
	Server server;

	public UserHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		log.trace("Handling user exhange");
		InputStreamReader isr = null;
		JsonReader jreader = null;
		MessageResponse response = null;
		CredentialsRequest request = null;
		try
		{
			isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
			jreader = new JsonReader(isr);
			request = gson.fromJson(jreader, CredentialsRequest.class);
			
			response = handleCredentials(request);
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
		
		OutputStream os = null;
		try
		{
			// Set the content type
			exchange.getResponseHeaders().set("Content-Type", "text/plain");
			// Add the player cookie if we succeeded
			if (response != null && response.getResponseCode() == 200 && request != null)
			{
				log.trace("Setting cookie for player");
				PlayerCookie cookie = new PlayerCookie(request.getUsername(), request.getPassword(), server.getServerLobby().getUserID(request.getUsername()));
				String cookieString = cookie.getCookie() + " Path=/;";
				log.trace("Set-Cookie: " + cookieString);
				exchange.getResponseHeaders().set("Set-Cookie", cookieString);
			}
			if (response != null)
			{
				log.trace("Sending " + response.getResponseCode() + ":\"" + response.getMessage() + "\" (" + response.getResponseLength() + ")");
				exchange.sendResponseHeaders(response.getResponseCode(), response.getResponseLength());
//				response.writeTo(exchange.getResponseBody());
				exchange.getResponseBody().write(response.getResponseBody());
				exchange.getResponseBody().close();
			}
			else
			{
				log.trace("sending response error message");
				exchange.sendResponseHeaders(400, "Failed to login - bad username or password..".getBytes().length);
				os = exchange.getResponseBody();
				os.write("Failed to login - bad username or password..".getBytes());
			}
			log.trace("sent response");
		}
		catch(IOException e)
		{
			System.err.println("Error in sending response to client");
			e.printStackTrace();
		}
		finally
		{
			if (os != null)
			{
				os.close();
			}
		}
	}

	protected abstract MessageResponse handleCredentials(CredentialsRequest request);

}
