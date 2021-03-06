package client.view.data;

import java.util.*;

/**
 * Used to pass game information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique game ID</li>
 * <li>Title: Game title (non-empty string)</li>
 * <li>Players: List of players who have joined the game (can be empty)</li>
 * </ul>
 * 
 */
public class GameInfo
{
	private int id;
	private String title;
	private List<PlayerInfo> players;
	
	public GameInfo()
	{
		setId(-1);
		setTitle("");
		players = new ArrayList<PlayerInfo>();
	}
	
	public GameInfo(int id, String title, List<PlayerInfo> players)
	{
		setId(id);
		setTitle(title);
		this.players = players;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void addPlayer(PlayerInfo newPlayer)
	{
		players.add(newPlayer);
	}
	
	public List<PlayerInfo> getPlayers()
	{
		return Collections.unmodifiableList(players);
	}
	
	public PlayerInfo getPlayerWithName(String playerName)
	{
		for (PlayerInfo player : players)
		{
			if (player.getName().equals(playerName))
			{
				return player;
			}
		}
		return null;
	}
	
	public PlayerInfo getPlayerWithId(int playerId)
	{
		for (PlayerInfo player : players)
		{
			if (player.getId() == playerId)
			{
				return player;
			}
		}
		return null;
	}
	
	public PlayerInfo getPlayerWithIndex(int playerIndex)
	{
		for (PlayerInfo player : players)
		{
			if (player.getPlayerIndex() == playerIndex)
			{
				return player;
			}
		}
		return null;
	}

	public void setPlayers(List<PlayerInfo> players)
	{
		this.players = players;
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("title : ").append(title).append(",\n");
		string.append("id : ").append(id).append(",\n");
		
		string.append("players : [\n");
		for (PlayerInfo player : players) {
			string.append(player.toString()).append(",\n");
		}
		string.append("]");
		
		string.append("}");
		
		return string.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((players == null) ? 0 : players.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameInfo other = (GameInfo) obj;
		if (id != other.id)
			return false;
		if (players == null) {
			if (other.players != null)
				return false;
		} else if (!players.equals(other.players))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}

