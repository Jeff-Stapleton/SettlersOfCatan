package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class MaritimeTradeRequest extends AbstractMovesRequest
{
	int _ratio;
	String _inputResource;
	String _outputResource;
	
	public MaritimeTradeRequest(int playerIndex, int ratio, String inputResource, String outputResource)
	{
		super("maritimeTrade", playerIndex);
		
		_ratio = ratio;
		_inputResource = inputResource;
		_outputResource = outputResource;
	}
	
	public int getRatio()
	{
		return _ratio;
	}
	
	public String getInputResource()
	{
		return _inputResource;
	}
	
	public String getOutputResource()
	{
		return _outputResource;
	}
}
