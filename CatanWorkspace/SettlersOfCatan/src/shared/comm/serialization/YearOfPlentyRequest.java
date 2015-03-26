package shared.comm.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class YearOfPlentyRequest extends AbstractMovesRequest
{
	String resource1;
	String resource2;
	
	public YearOfPlentyRequest(int playerIndex, String resource1, String resource2)
	{
		super("Year_of_Plenty", playerIndex);
		
		this.resource1 = resource1.toString();
		this.resource2 = resource2.toString();
	}
	
	public String getResource1()
	{
		return resource1;
	}
	
	public String getResource2()
	{
		return resource2;
	}
	
	public String toString()
	{
		StringBuilder string = new StringBuilder();
		
		string.append("{\n")
			.append("\"resource1\" : ").append(resource1).append(",\n")
			.append("\"resource2\" : ").append(resource2).append("\n")
			.append("}\n");
		
		return string.toString();
	}
}
