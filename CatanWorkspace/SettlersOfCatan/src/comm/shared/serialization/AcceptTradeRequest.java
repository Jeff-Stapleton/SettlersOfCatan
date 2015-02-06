package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class AcceptTradeRequest extends AbstractMovesRequest
{
	boolean willAccept;
	
	public AcceptTradeRequest(int playerIndex, boolean willAccept)
	{
		super("acceptTrade", playerIndex);
		
		this.willAccept = willAccept;
	}
	
	public boolean willAccept() { return willAccept; }
	public boolean getWillAccept()
	{
		return willAccept;
	}
}
