package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class FinishTurnRequest extends AbstractMovesRequest
{
	public FinishTurnRequest(int playerIndex)
	{
		super("finishTurn", playerIndex);
	}
}
