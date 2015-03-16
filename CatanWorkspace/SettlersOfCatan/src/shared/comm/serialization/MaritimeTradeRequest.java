package shared.comm.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class MaritimeTradeRequest extends AbstractMovesRequest
{
	int ratio;
	String inputResource;
	String outputResource;
	
	public MaritimeTradeRequest(int playerIndex, int ratio, String inputResource, String outputResource)
	{
		super("maritimeTrade", playerIndex);
		
		this.ratio = ratio;
		this.inputResource = inputResource;
		this.outputResource = outputResource;
	}
	
	public int getRatio()
	{
		return ratio;
	}
	
	public String getInputResource()
	{
		return inputResource;
	}
	
	public String getOutputResource()
	{
		return outputResource;
	}
}
