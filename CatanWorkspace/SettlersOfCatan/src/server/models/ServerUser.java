package server.models;

public class ServerUser
{
	// TODO: Make sure this is loaded when a server is brought up again!!
	private static int currentID = 0;
	private String username;
	private String password;
	private Integer id;
	
	public ServerUser(String username, String password)
	{
		this.username = username;
		this.password = password;
		id = currentID++;
	}

	public Object getPassword()
	{
		return password;
	}

	public Object getUsername()
	{
		return username;
	}

	public int getID()
	{
		return id;
	}
	
}
