package comm.shared.serialization;

public abstract class AbstractMovesRequest
{
	private String _type;
	private int _playerIndex;
	
	public AbstractMovesRequest(String type, int playerIndex)
	{
		_type = type;
		_playerIndex = playerIndex;
	}
	
	public String getType()
	{
		return _type;
	}
	
	public int getPlayerIndex()
	{
		return _playerIndex;
	}
}
