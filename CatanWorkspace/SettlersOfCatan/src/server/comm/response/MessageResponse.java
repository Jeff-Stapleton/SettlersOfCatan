package server.comm.response;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * The Class MessageResponse.
 */
public class MessageResponse extends Response
{
	private static final Logger log = Logger.getLogger(MessageResponse.class);
	
	String msg = null;
	
	public MessageResponse(int code, String msg)
	{
		super(code, msg.length());
		this.msg = msg;
	}
		
	/**
	 * Sets the string message body.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message)
	{
		msg = message;
	}

	public String getMessage()
	{
		return msg;
	}
	
//	public void writeTo(OutputStream os) throws IOException
//	{
//		log.trace("Writing message to response body");
//		os.write(msg.getBytes());
//	}

	@Override
	public byte[] getResponseBody()
	{
		return msg.getBytes();
	}

}
