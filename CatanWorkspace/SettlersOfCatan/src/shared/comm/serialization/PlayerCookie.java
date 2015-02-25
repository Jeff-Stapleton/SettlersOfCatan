package shared.comm.serialization;

public class PlayerCookie
{
	String name = null;
	String password = null;
	Integer playerID = null;
	
	public String getName()
	{
		return name;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public Integer getPlayerId()
	{
		return playerID;
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("name : ").append(name).append(",\n");
		string.append("password : ").append(password).append(",\n");
		string.append("playerID : ").append(playerID);
		
		string.append("}");
		
		return string.toString();
	}
}
