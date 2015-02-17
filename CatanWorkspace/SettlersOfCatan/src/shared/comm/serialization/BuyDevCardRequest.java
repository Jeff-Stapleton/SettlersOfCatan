package shared.comm.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class BuyDevCardRequest extends AbstractMovesRequest
{
	public BuyDevCardRequest(int playerIndex)
	{
		super("buyDevCard", playerIndex);
	}
}
