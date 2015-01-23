package shared;
/**
 * Represents individual message lines that have the text of the message along with the owner.
 *
 * @author JJ
 */
public class MessageLine 
{
	/** The message. */
	private String message;
	
	/** The source. */
	private int source;
	
	/**
	 * Instantiates a new message line.
	 *
	 * @param source the source
	 * @param message the message
	 */
	public MessageLine(int source, String message) 
	{
		this.source = source;
		this.message = message;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage()
	{
		return message;
	}

	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public int getSource()
	{
		return source;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Sets the source.
	 *
	 * @param source the new source
	 */
	public void setSource(int source)
	{
		this.source = source;
	}
}
