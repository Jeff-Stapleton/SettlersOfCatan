package shared;

public class FirstRoundState extends GameState
{

	public FirstRoundState(TurnTracker tracker)
	{
		super(tracker);
	}

	@Override
	public void endTurn()
	{
		assert (getTracker().getCurrentTurn() >= 0 && getTracker().getCurrentTurn() <= 3);
		if (getTracker().getCurrentTurn() < 3)
		{
			getTracker().setCurrentTurn(getTracker().getCurrentTurn() + 1);
		}
		else
		{
			getTracker().setStatus(TurnType.SECOND_ROUND);
			getTracker().setState(new SecondRoundState(getTracker()));
		}
	}

}
