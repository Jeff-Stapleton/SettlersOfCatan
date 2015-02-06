package comm.shared.serialization;

public class GameResponse
{
	String title;
	int id;
	PlayerResponse[] players;
	
	public GameResponse(String title, int id, PlayerResponse[] players)
	{
		this.title = title;
		this.id = id;
		this.players = players;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public int getId()
	{
		return id;
	}
	
	public PlayerResponse[] getPlayers()
	{
		return players;
	}
}
