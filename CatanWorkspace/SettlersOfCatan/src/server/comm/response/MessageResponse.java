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
	int code = 200;
	String msg = "Success";
	public MessageResponse(int code, String msg)
	{
		this.code = code;
		this.msg = msg;
	}
	
		
	/**
	 * Sets the json body.
	 *
	 * @param <T> the generic type
	 * @param message the new message
	 */
	public <T> void setMessage(String message)
	{
		
	}


	/**
	 * Get the response code
	 * @return the response code
	 */
	public int getCode()
	{
		return code;
	}

	/**
	 * Get the bit length of the response
	 * @return the bit-length response
	 */
	public long getResponseLength()
	{
		return msg.getBytes().length;
	}


	public void writeTo(OutputStream os) throws IOException
	{
		os.write(msg.getBytes());
	}


	public String getMessage()
	{
		return msg;
	}

}
