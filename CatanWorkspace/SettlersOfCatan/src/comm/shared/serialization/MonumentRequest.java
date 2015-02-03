package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class MonumentRequest extends AbstractMovesRequest
{
	public MonumentRequest(int playerIndex)
	{
		super("Monument", playerIndex);
	}
}
