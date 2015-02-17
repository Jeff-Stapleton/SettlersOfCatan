package shared.comm.serialization;

public abstract class AbstractMovesRequest
{
	private String type;
	private int playerIndex;
	
	public AbstractMovesRequest(String type, int playerIndex)
	{
		this.type = type;
		this.playerIndex = playerIndex;
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getPlayerIndex()
	{
		return playerIndex;
	}
}
