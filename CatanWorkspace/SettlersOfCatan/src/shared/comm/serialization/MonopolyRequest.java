package shared.comm.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class MonopolyRequest extends AbstractMovesRequest
{
	String resource;
	
	public MonopolyRequest(int playerIndex, String resource)
	{
		super("Monopoly", playerIndex);
		
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
