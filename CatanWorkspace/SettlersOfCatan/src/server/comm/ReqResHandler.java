package server.comm;

import java.io.IOException;

import org.apache.log4j.Logger;

import server.Server;
import server.comm.request.AbstractRequest;
import server.comm.response.IResponse;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * An HttpHandler that uses a request and response object. One simply needs
 * to create Request and Response objects and pass them in as type parameters.
 * @author Cory
 * @param <Response> the response type to generate from the request
 * @param <AbstractRequest> the request type for generating a response
 */
public class ReqResHandler <Res extends IResponse, Req extends AbstractRequest<Res>>
		implements HttpHandler
{
	private static final Logger log = Logger.getLogger(ReqResHandler.class.getName());
	
	private Server server = null;
	private Class<Req> clazz = null;

	public ReqResHandler(Server server, Class<Req> clazz)
	{
		this.server = server;
		this.clazz = clazz;
	}

	/* (non-Javadoc)
	 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException
	{
		log.trace("Recieved request");
		AbstractRequest<Res> request = null;
		try
		{
			// Create our new request
			request = clazz.newInstance();
			request.setServer(server);
			request.loadFromExchange(exchange);
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		Res response = request.getResponse();
		// TODO send the response's info to the client
	}

}
