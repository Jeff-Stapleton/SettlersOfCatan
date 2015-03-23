package shared;

import java.util.Observable;

/**
 * Tracks whose turn it currently is, and changes the possession of the Longest road and largest army cards each turn if neccessary.
 * @author JJ
 */
public class TurnTracker extends Observable
{
	// singleton
    private static TurnTracker instance = new TurnTracker();

    public static TurnTracker getInstance() 
    {
        return instance;
    }
    
	/** The current turn. */
	private int currentTurn = 0;
	
	/** The status. */
	private TurnType status = TurnType.PLAYING;
	
	/** The longest road. */
	private int longestRoad = -1;
	
	/** The largest army. */
	private int largestArmy = -1;
	
	/**
	 * Instantiates a new turn tracker in case of no variables to be input. 
	 */
	public TurnTracker() {}
	

	/**
	 * Instantiates a new turn tracker.
	 *
	 * @param status the status
	 * @param currentTurn the current turn
	 * @param longestRoad the longest road
	 * @param largestArmy the largest army
	 */
	public TurnTracker(TurnType status, int currentTurn, int longestRoad, int largestArmy) 
	{
		this.status = status;
		this.currentTurn = currentTurn;
		this.longestRoad = longestRoad;
		this.largestArmy = largestArmy;
	}

	/**
	 * Gets the current turn.
	 *
	 * @return the current turn
	 */
	public int getCurrentTurn() 
	{
		return currentTurn;
	}

	/**
	 * Sets the current turn.
	 *
	 * @param currentTurn the new current turn
	 */
	public void setCurrentTurn(int currentTurn) 
	{
		this.currentTurn = currentTurn;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public TurnType getStatus() 
	{
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(TurnType status) 
	{
		this.status = status;
	}

	/**
	 * Gets the longest road.
	 *
	 * @return the longest road
	 */
	public int getLongestRoad() 
	{
		// set the player with the longest road
		return longestRoad;
	}

	/**
	 * Sets the longest road.
	 *
	 * @param longestRoad the new longest road
	 */
	public void setLongestRoad(int longestRoad) 
	{
		// look over all player, who has the most roads >= 5
		this.longestRoad = longestRoad;
		// ?notify all players, who has the longest road
	}

	/**
	 * Gets the largest army.
	 *
	 * @return the largest army
	 */
	public int getLargestArmy() 
	{
		// set the player with the largest army
		return largestArmy;
		// ?notify player he now has it?
	}

	/**
	 * Sets the largest army.
	 *
	 * @param largestArmy the new largest army
	 */
	public void setLargestArmy(int largestArmy) 
	{
		// look over all player, who has the most soldiers >= 5
		this.largestArmy = largestArmy;
	}

	/**
	 * Next turn.
	 */
	public void nextTurn() {
		if (currentTurn == 0 && status.equals("SecondRound")) 
			status = TurnType.ROLLING;
		else if (currentTurn > 0 && currentTurn < 3 && status.equals("SecondRound")) 
			currentTurn--;
		else if (currentTurn == 3 && status.equals("FirstRound")) 
			status = TurnType.SECOND_ROUND;
		else if (currentTurn == 3 && status.equals("SecondRound")) 
			currentTurn = 2;
		else if (currentTurn < 3) 
			currentTurn++;
		else 
			currentTurn = 0;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("status : ").append(status.toString()).append(",\n");
		string.append("currentTurn : ").append(currentTurn).append(",\n");
		string.append("longestRoad : ").append(longestRoad).append(",\n");
		string.append("largestArmy : ").append(largestArmy).append("\n");
		
		string.append("}");
		
		return string.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentTurn;
		result = prime * result + largestArmy;
		result = prime * result + longestRoad;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TurnTracker other = (TurnTracker) obj;
		if (currentTurn != other.currentTurn)
			return false;
		if (largestArmy != other.largestArmy)
			return false;
		if (longestRoad != other.longestRoad)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	public boolean updateFrom(TurnTracker rhs)
	{
		boolean updated = false;
		
		if (currentTurn != rhs.currentTurn)
		{
			currentTurn = rhs.currentTurn;
			updated = true;
		}
		if (status != rhs.status)
		{
			status = rhs.status;
			updated = true;
		}
		if (longestRoad != rhs.longestRoad)
		{
			longestRoad = rhs.longestRoad;
			updated = true;
		}
		if (largestArmy != rhs.largestArmy)
		{
			largestArmy = rhs.largestArmy;
			updated = true;
		}
		
		if (updated)
		{
			setChanged();
			notifyObservers();
		}
		
		return updated;
	}


	public void reset() {
		currentTurn = 0;
		status = TurnType.PLAYING;
		longestRoad = -1;
		largestArmy = -1;
	}
}
