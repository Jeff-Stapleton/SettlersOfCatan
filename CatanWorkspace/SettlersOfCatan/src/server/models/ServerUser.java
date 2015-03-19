package server.models;

public class ServerUser
{
	private String username;
	private String password;
	
	public ServerUser(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public Object getPassword()
	{
		return password;
	}

	public Object getUsername()
	{
		return username;
	}
	
}
