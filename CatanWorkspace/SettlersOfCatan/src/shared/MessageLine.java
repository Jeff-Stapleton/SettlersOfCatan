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
	private String source;
	
	/**
	 * Instantiates a new message line.
	 *
	 * @param source the source
	 * @param message the message
	 */
	public MessageLine(String source, String message) 
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
	public String getSource()
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
	public void setSource(String source)
	{
		this.source = source;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("source : ").append(source).append(",\n");
		string.append("message : ").append(message).append("\n");
		
		string.append("}");
		
		return string.toString();
	}
}
