package shared.comm.cookie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PlayerCookie implements ICookie
{
	String name = null;
	String password = null;
	Integer playerID = null;
	
	public PlayerCookie(String username, String password, int playerID)
	{
		name = username;
		this.password = password;
		this.playerID = playerID;
	}

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

	public String getCookie()
	{
		StringBuilder cookie = new StringBuilder();
		try
		{
			StringBuilder catanUser = new StringBuilder();
			catanUser.append("{\"name\":\"").append(name).append("\",")
					 .append("\"password\":\"").append(password).append("\",")
					 .append("\"playerID\":").append(playerID).append("}");
			
			cookie.append("catan.user=").append(URLEncoder.encode(catanUser.toString(), "utf-8")).append(";");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return cookie.toString();
	}
}
