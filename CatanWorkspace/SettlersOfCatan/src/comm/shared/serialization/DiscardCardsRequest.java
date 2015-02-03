package comm.shared.serialization;

import shared.ResourceList;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class DiscardCardsRequest extends AbstractMovesRequest
{
	ResourceList _discardedCards;
	
	public DiscardCardsRequest(int playerIndex, ResourceList offer)
	{
		super("discardCards", playerIndex);
		
		_discardedCards = offer;
	}
	
	public ResourceList getDiscardedCards()
	{
		return _discardedCards;
	}
}
