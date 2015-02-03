package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class MonopolyRequest extends AbstractMovesRequest
{
	String _resource;
	
	public MonopolyRequest(int playerIndex, String resource)
	{
		super("Monopoly", playerIndex);
		
		_resource = resource;
	}
	
	public String getResource()
	{
		return _resource;
	}
}
