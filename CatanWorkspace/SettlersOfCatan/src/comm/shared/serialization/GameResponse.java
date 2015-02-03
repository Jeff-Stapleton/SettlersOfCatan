package comm.shared.serialization;

public class GameResponse
{
	String _title;
	int _id;
	PlayerResponse[] _players;
	
	public GameResponse(String title, int id, PlayerResponse[] players)
	{
		_title = title;
		_id = id;
		_players = players;
	}
	
	public String getTitle()
	{
		return _title;
	}
	
	public int getId()
	{
		return _id;
	}
	
	public PlayerResponse[] getPlayers()
	{
		return _players;
	}
}
