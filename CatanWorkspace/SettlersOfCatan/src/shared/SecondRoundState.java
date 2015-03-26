package shared;

import org.apache.log4j.Logger;

public class SecondRoundState extends GameState
{
	private static final Logger log = Logger.getLogger(SecondRoundState.class);
	
	public SecondRoundState(TurnTracker tracker)
	{
		super(tracker);
	}

	@Override
	public void endTurn()
	{
		log.trace("Ending player " + getTracker().getCurrentTurn() + "'s second turn");
		assert (getTracker().getCurrentTurn() >= 0 && getTracker().getCurrentTurn() <= 3);
		if (getTracker().getCurrentTurn() > 0)
		{
			log.trace("Starting player " + (getTracker().getCurrentTurn() - 1) + "'s second turn");
			getTracker().setCurrentTurn(getTracker().getCurrentTurn() - 1);
		}
		else
		{
			log.trace("Starting player " + getTracker().getCurrentTurn() + "'s turn");
			getTracker().setStatus(TurnType.ROLLING);
			getTracker().setState(new NormalTurnState(getTracker()));
		}
	}

}
