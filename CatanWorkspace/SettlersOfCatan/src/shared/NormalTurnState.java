package shared;

import org.apache.log4j.Logger;

public class NormalTurnState extends GameState
{
	private static final Logger log = Logger.getLogger(NormalTurnState.class);

	public NormalTurnState(TurnTracker tracker)
	{
		super(tracker);
	}

	@Override
	public void endTurn()
	{
		log.trace("Ending player " + getTracker().getCurrentTurn() + "'s turn");
		getTracker().setCurrentTurn((getTracker().getCurrentTurn() + 1) % 4);
		getTracker().setStatus(TurnType.ROLLING);
		log.trace("Starting player " + getTracker().getCurrentTurn() + "'s turn");
	}

}
