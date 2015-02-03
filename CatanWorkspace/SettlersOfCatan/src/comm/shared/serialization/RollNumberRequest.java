package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class RollNumberRequest extends AbstractMovesRequest
{
	int _number;
	
	public RollNumberRequest(int playerIndex, int number)
	{
		super("rollNumber", playerIndex);
		
		_number = number;
	}
	
	public int getNumber()
	{
		return _number;
	}
}
