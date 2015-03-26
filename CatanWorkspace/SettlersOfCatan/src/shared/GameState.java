package shared;

public abstract class GameState
{
	private TurnTracker tracker = null;
	
	public GameState(TurnTracker tracker)
	{
		this.tracker = tracker;
	}
	
	public abstract void endTurn();
	
	public TurnTracker getTracker()
	{
		return tracker;
	}
}
