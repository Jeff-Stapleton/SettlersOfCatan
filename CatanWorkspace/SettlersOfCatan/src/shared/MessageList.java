package shared;
/**
 * The chat window class that maintains all the messages
 *
 * @author JJ
 */

import java.util.ArrayList;
import java.util.Observable;

public class MessageList extends Observable
{
	
	/** The list of messages. */
	ArrayList<MessageLine> lines;
	
	public MessageList()
	{
		lines = new ArrayList<MessageLine>();
	}
	
	/**
	 * Adds a message.
	 *
	 * @param newLine the new message
	 */
	public void addLine(MessageLine newLine)
	{
		lines.add(newLine);
	}
	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	public ArrayList<MessageLine> getLines()
	{
		return lines;
	}
	
	/**
	 * Sets the messages.
	 *
	 * @param listOfMessages the new messages
	 */
	public void setLines(ArrayList<MessageLine> lines)
	{
		this.lines = lines;
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("lines : [\n");
		for (MessageLine ml : lines) {
			string.append(ml.toString()).append(",\n");
		}
		string.append("]\n");
		
		string.append("}");
		
		return string.toString();
	}

	public boolean updateFrom(MessageList rhs)
	{
		boolean updated = false;
		
		if (lines.size() != rhs.getLines().size())
		{
			System.out.println("First rhs : " + rhs.toString());
			System.out.println("First this : " + this.toString());
			lines = rhs.getLines();
			updated = true;
		}
		else
		{
			System.out.println("Second rhs : " + rhs.toString());
			System.out.println("Second this : " + this.toString());
			
			for (int i = 0; i < rhs.getLines().size(); i++)
			{
				if (!lines.get(i).equals(rhs.getLines().get(i)))
				{
					lines.get(i).setSource(rhs.getLines().get(i).getSource());
					lines.get(i).setMessage(rhs.getLines().get(i).getMessage());
					updated = true;
				}
			}
		}
		
		if (updated)
		{
			setChanged();
			notifyObservers();
		}

		return updated;
	}

}
