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
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("username : ").append(username).append(",\n");
		string.append("password : ").append(password).append("\n");
		
		string.append("}");
		return string.toString();
	}

}
