package comm.shared.serialization;

public class CredentialsRequest
{
	String _username;
	String _password;
	
	public CredentialsRequest(String username, String password)
	{
		_username = username;
		_password = password;
	}
	
	public String getUsername()
	{
		return _username;
	}
	
	public String getPassword()
	{
		return _password;
	}

}
