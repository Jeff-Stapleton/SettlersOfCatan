package shared.comm.serialization;

import shared.ResourceList;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class OfferTradeRequest extends AbstractMovesRequest
{
	ResourceList offer;
	int receiver;
	
	public OfferTradeRequest(int playerIndex, ResourceList offer, int receiver)
	{
		super("offerTrade", playerIndex);
		
		this.offer = offer;
		this.receiver = receiver;
	}
	
	public ResourceList getOffer()
	{
		return offer;
	}
	
	public int getReceiver()
	{
		return receiver;
	}
}
