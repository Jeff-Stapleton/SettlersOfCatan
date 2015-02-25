package shared.comm.serialization;

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
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("title : ").append(title).append(",\n");
		string.append("id : ").append(id).append(",\n");
		
		string.append("players : [\n");
		for (PlayerResponse player : players) {
			string.append(player.toString()).append(",\n");
		}
		string.append("]");
		
		string.append("}");
		
		return string.toString();
	}
}
