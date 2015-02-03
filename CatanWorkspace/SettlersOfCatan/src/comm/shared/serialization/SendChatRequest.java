package comm.shared.serialization;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class SendChatRequest extends AbstractMovesRequest
{
	String _content;
	
	public SendChatRequest(int playerIndex, String content)
	{
		super("sendChat", playerIndex);
		
		_content = content;
	}
	
	public String getContent()
	{
		return _content;
	}
}
