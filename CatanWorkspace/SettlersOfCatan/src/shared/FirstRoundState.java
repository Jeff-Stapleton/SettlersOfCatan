package shared;

import org.apache.log4j.Logger;

public class FirstRoundState extends GameState
{
	private static final Logger log = Logger.getLogger(FirstRoundState.class);

	public FirstRoundState(TurnTracker tracker)
	{
		super(tracker);
	}

	@Override
	public void endTurn()
	{
		log.trace("Ending player " + getTracker().getCurrentTurn() + "'s first turn");
		assert (getTracker().getCurrentTurn() >= 0 && getTracker().getCurrentTurn() <= 3);
		if (getTracker().getCurrentTurn() < 3)
		{
			log.trace("Starting player " + (getTracker().getCurrentTurn() + 1) + "'s first turn");
			getTracker().setCurrentTurn(getTracker().getCurrentTurn() + 1);
		}
		else
		{
			log.trace("Starting player " + getTracker().getCurrentTurn() + "'s second turn");
			getTracker().setStatus(TurnType.SECOND_ROUND);
			getTracker().setState(new SecondRoundState(getTracker()));
		}
	}

}
