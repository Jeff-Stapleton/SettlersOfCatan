package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class YearOfPlentyRequest extends AbstractMovesRequest
{
	String _resource1;
	String _resource2;
	
	public YearOfPlentyRequest(int playerIndex, String resource1, String resource2)
	{
		super("Year_of_Plenty", playerIndex);
		
		_resource1 = resource1.toString();
		_resource2 = resource2.toString();
	}
	
	public String getResource1()
	{
		return _resource1;
	}
	
	public String getResource2()
	{
		return _resource2;
	}
}
