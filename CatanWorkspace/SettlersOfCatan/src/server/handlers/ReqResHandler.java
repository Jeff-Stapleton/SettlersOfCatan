package server.handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.apache.log4j.Logger;

import server.Server;
import server.comm.request.AbstractRequest;
import server.comm.response.IResponse;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ReqResHandler<Resp extends IResponse, Req extends AbstractRequest<Resp>> implements HttpHandler
{
	private static final Logger log = Logger.getLogger(ReqResHandler.class);
	
	Server server = null;
	private Class<Req> clazz = null;
	
	public ReqResHandler(Server server, Class<Req> clazz)
	{
		this.server = server;
		this.clazz = clazz;
	}

	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		try
		{
			log.trace("Creating empty request");
			Req request = clazz.newInstance();
			request.setServer(server);
			
			log.trace("Loading request data from exchange");
			request.loadFromExchange(exchange);
			
			log.trace("Executing and getting a response");
			Resp response = request.getResponse();
			assert(response != null);
			
			OutputStream os = null;
			try
			{
				log.trace("Sending \"" + response.getContentType() + "\" " + response.getResponseCode() + " length: " + response.getResponseLength());

				exchange.sendResponseHeaders(response.getResponseCode(), response.getResponseLength());
				
				log.trace("There are " + response.getHeaders().size() + " headers to send");
				if (response.getHeaders().size() > 0)
				{
					log.trace("Adding " + response.getHeaders().size() + " headers");
					for (Map.Entry<String,String> e : response.getHeaders().entrySet())
					{
						log.trace("Header: " + e.getKey() + " = " + e.getValue() + ";");
						exchange.getResponseHeaders().add(e.toString(), e.toString());
					}
				}

				log.trace("Writing response body:\n" + new String(response.getResponseBody()));
				os = exchange.getResponseBody();
				os.write(response.getResponseBody());
				
				log.trace("sent response");
			}
			catch(IOException e)
			{
				log.error("Error in sending response to client");
				e.printStackTrace();
			}
			finally
			{
				if (os != null)
				{
					log.trace("Closing output stream");
					os.close();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
