package server.handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import server.Server;
import server.comm.response.MessageResponse;
import shared.Util;
import shared.comm.serialization.CredentialsRequest;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class LoginHandler implements HttpHandler
{
	private static final Logger log = Logger.getLogger(LoginHandler.class);
	Gson gson = new Gson();
	Server server;

	public LoginHandler(Server server)
	{
		this.server = server;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		log.trace("Handling login exhange");
		InputStreamReader isr = null;
		JsonReader jreader = null;
		MessageResponse response = null;
		try
		{
			isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
			jreader = new JsonReader(isr);
			CredentialsRequest request = gson.fromJson(jreader, CredentialsRequest.class);
			
			log.trace("Verifying user credentials");
			response = server.getServerLobby().getUserFacade().login(request);
			log.trace("User validation result : " + (response != null));
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
			exchange.getResponseHeaders().set("Content-Type", "text/plain");
			if (response != null)
			{
				log.trace("Sending " + response.getCode() + ":\"" + response.getMessage() + "\" (" + response.getResponseLength() + ")");
				exchange.sendResponseHeaders(response.getCode(), response.getResponseLength());
				response.writeTo(exchange.getResponseBody());
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

}
