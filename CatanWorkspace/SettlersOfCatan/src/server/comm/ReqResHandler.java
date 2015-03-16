package server.comm;

import java.io.IOException;

import server.comm.request.IRequest;
import server.comm.response.IResponse;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

// TODO: Auto-generated Javadoc
/**
 * An HttpHandler that uses a request and response object. One simply needs
 * to create Request and Response objects and pass them in as type parameters.
 * @author Cory
 * @param <Response> the response type to generate from the request
 * @param <Request> the request type for generating a response
 */
public class ReqResHandler <Response extends IResponse, Request extends IRequest<Response>>
		implements HttpHandler {

	/* (non-Javadoc)
	 * @see com.sun.net.httpserver.HttpHandler#handle(com.sun.net.httpserver.HttpExchange)
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		// TODO pass the httpExchange to the request to build it
		// TODO get a response from the request
		// TODO send the response's info to the client
	}

}
