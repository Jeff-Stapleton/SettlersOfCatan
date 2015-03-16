package server.facade;


/**
 * The Class UtilFacade implements the changeLogLevel command
 *
 */
public class UtilFacade {

	/**
	 * Sets the server�s logging level.
	 * 
	 * @pre 
	 * 	The caller specifies a valid logging level. Valid values include: SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
	 * 
	 * @post
	 * 	The server returns an HTTP 200 success response with �Success� in the body.
	 * 	The Server is using the specified logging level
	 * 
	 */
	public void changeLogLevel(String logLevel){
		
	}
}
