package shared.comm.serialization;

public class CredentialsRequest
{
	String username;
	String password;
	
	public CredentialsRequest(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}

}
