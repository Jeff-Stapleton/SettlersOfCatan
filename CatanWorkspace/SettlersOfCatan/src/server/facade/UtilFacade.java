package server.facade;

import org.apache.log4j.Level;

/**
 * The Class UtilFacade implements the changeLogLevel command
 *
 */
import server.Server;

public class UtilFacade
{
	Server server;
	
	public UtilFacade(Server server)
	{
		this.server = server;
	}

	/**
	 * Sets the server's logging level.
	 * 
	 * @pre 
	 * 	The caller specifies a valid logging level. Valid values include: SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
	 * 
	 * @post
	 * 	The server returns an HTTP 200 success response with 'Success' in the body.
	 * 	The Server is using the specified logging level
	 * 
	 */
	public void changeLogLevel(String logLevel)
	{
		server.setLogLevel(stringToLevel(logLevel));
	}
	
	/**
	 * Convert java logging levels to log4j levels
	 * @param logLevel the java logging level
	 * @return The log4j equivalent for the string
	 */
	private static Level stringToLevel(String logLevel)
	{
		Level level = null;
		switch(logLevel)
		{
		case "ALL":
			level = Level.ALL;
			break;
		case "SEVERE":
			level = Level.ERROR;
			break;
		case "WARNING":
			level = Level.WARN;
			break;
		case "INFO":
			level = Level.INFO;
			break;
		case "CONFIG":
			level = Level.DEBUG;
			break;
		case "FINE":
		case "FINER":
		case "FINEST":
			level = Level.TRACE;
			break;
		case "OFF":
			level = Level.OFF;
			break;
		}
		return level;
	}
}
