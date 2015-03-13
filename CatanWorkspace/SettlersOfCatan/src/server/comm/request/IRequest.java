package server.comm.request;

import server.comm.response.IResponse;
import shared.comm.ServerException;

public interface IRequest<Response extends IResponse> {
	/**
	 * Generate a response from information in the database.
	 * Upon entering this function, the database has an open
	 * transaction. Upon leaving this function, the database
	 * changes will be commited and the transaction closed.
	 * @param db
	 * @return
	 */
	public Response getResponse() throws ServerException;
}
