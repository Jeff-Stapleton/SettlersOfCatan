package shared;

public class SecondRoundState extends GameState
{

	public SecondRoundState(TurnTracker tracker)
	{
		super(tracker);
	}

	@Override
	public void endTurn()
	{
		assert (getTracker().getCurrentTurn() >= 0 && getTracker().getCurrentTurn() <= 3);
		if (getTracker().getCurrentTurn() > 0)
		{
			getTracker().setCurrentTurn(getTracker().getCurrentTurn() - 1);
		}
		else
		{
			getTracker().setStatus(TurnType.ROLLING);
			getTracker().setState(new NormalTurnState(getTracker()));
		}
	}

}
