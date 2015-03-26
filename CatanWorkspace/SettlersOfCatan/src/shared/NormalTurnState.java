package shared;

public class NormalTurnState extends GameState
{

	public NormalTurnState(TurnTracker tracker)
	{
		super(tracker);
	}

	@Override
	public void endTurn()
	{
		getTracker().setCurrentTurn((getTracker().getCurrentTurn() + 1) % 4);
		getTracker().setStatus(TurnType.ROLLING);
	}

}
