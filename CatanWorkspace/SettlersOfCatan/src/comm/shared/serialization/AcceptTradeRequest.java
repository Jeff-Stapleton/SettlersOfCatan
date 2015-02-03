package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class AcceptTradeRequest extends AbstractMovesRequest
{
	boolean _willAccept;
	
	public AcceptTradeRequest(int playerIndex, boolean willAccept)
	{
		super("acceptTrade", playerIndex);
		
		_willAccept = willAccept;
	}
	
	public boolean willAccept() { return _willAccept; }
	public boolean getWillAccept()
	{
		return _willAccept;
	}
}
