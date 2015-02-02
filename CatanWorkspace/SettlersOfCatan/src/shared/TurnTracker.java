package shared;

/**
 * Tracks whose turn it currently is, and changes the possession of the Longest road and largest army cards each turn if neccessary.
 * @author JJ
 */
public class TurnTracker 
{
	// singleton
    private static TurnTracker instance = new TurnTracker();

    public static TurnTracker getInstance() 
    {
        return instance;
    }
    
	/** The current turn. */
	private int currentTurn;
	
	/** The status. */
	private TurnType status;
	
	/** The longest road. */
	private int longestRoad;
	
	/** The largest army. */
	private int largestArmy;
	
	/**
	 * Instantiates a new turn tracker in case of no variables to be input. 
	 */
	public TurnTracker() 
	{
		status = TurnType.PLAYING;
		currentTurn = 0;
		longestRoad=-1;
		largestArmy=-1;
	}
	

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
}
