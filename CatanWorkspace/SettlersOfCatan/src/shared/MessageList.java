package shared;
/**
 * The chat window class that maintains all the messages
 *
 * @author JJ
 */

import java.util.ArrayList;

public class MessageList {
	
	/** The list of messages. */
	ArrayList<MessageLine> listOfMessages;
	
	public MessageList()
	{
		listOfMessages = new ArrayList<MessageLine>();
	}
	
	/**
	 * Adds a message.
	 *
	 * @param newMessage the new message
	 */
	public void addMessage(MessageLine newMessage)
	{
		listOfMessages.add(newMessage);
	}
	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public ArrayList<MessageLine> getMessages()
	{
		return listOfMessages;
	}
	
	/**
	 * Sets the messages.
	 *
	 * @param listOfMessages the new messages
	 */
	public void setMessages(ArrayList<MessageLine> listOfMessages)
	{
		this.listOfMessages = listOfMessages;
	}

}
