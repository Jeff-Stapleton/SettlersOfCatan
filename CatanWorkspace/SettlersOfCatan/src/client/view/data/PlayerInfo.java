package client.view.data;

import shared.definitions.*;

/**
 * Used to pass player information into views<br>
 * <br>
 * PROPERTIES:<br>
 * <ul>
 * <li>Id: Unique player ID</li>
 * <li>PlayerIndex: Player's order in the game [0-3]</li>
 * <li>Name: Player's name (non-empty string)</li>
 * <li>Color: Player's color (cannot be null)</li>
 * </ul>
 * 
 */
public class PlayerInfo
{
	
	private int id;
	private transient int playerIndex;
	private String name;
	private String color;
	
	public PlayerInfo()
	{
		setId(-1);
		setPlayerIndex(-1);
		setName("");
		setColor(CatanColor.WHITE);
	}
	
	public PlayerInfo(int id, int index, String name, CatanColor color)
	{
		setId(id);
		setPlayerIndex(index);
		setName(name);
		setColor(color);
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	
	public void setPlayerIndex(int playerIndex)
	{
		this.playerIndex = playerIndex;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public CatanColor getColor()
	{
		return color != null ? CatanColor.fromString(color) : null;
	}
	
	public void setColor(CatanColor color)
	{
		this.color = color != null ? color.toString() : null;
	}

	@Override
	public int hashCode()
	{
		return 31 * this.id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		final PlayerInfo other = (PlayerInfo) obj;
		
		return this.id == other.id;
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("id : ").append(id).append(",\n");
		string.append("index : ").append(playerIndex).append(",\n");
		string.append("name : ").append(name).append(",\n");
		string.append("color : ").append(color);
		
		string.append("}");
		
		return string.toString();
	}
}

