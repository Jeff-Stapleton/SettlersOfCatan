package comm.shared.serialization;

import shared.ResourceList;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class OfferTradeRequest extends AbstractMovesRequest
{
	ResourceList _offer;
	int _receiver;
	
	public OfferTradeRequest(int playerIndex, ResourceList offer, int receiver)
	{
		super("offerTrade", playerIndex);
		
		_offer = offer;
		_receiver = receiver;
	}
	
	public ResourceList getOffer()
	{
		return _offer;
	}
	
	public int getReceiver()
	{
		return _receiver;
	}
}
