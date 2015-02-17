package shared.comm.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class RollNumberRequest extends AbstractMovesRequest
{
	int number;
	
	public RollNumberRequest(int playerIndex, int number)
	{
		super("rollNumber", playerIndex);
		
		this.number = number;
	}
	
	public int getNumber()
	{
		return number;
	}
}
