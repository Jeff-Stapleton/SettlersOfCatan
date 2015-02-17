package shared.comm.serialization;

import shared.ResourceList;

/**
 * Serialization pojo class for easy toJson using gson for sendChat requests
 * @author Cory Beutler
 *
 */
public class DiscardCardsRequest extends AbstractMovesRequest
{
	ResourceList discardedCards;
	
	public DiscardCardsRequest(int playerIndex, ResourceList discardedCards)
	{
		super("discardCards", playerIndex);
		
		this.discardedCards = discardedCards;
	}
	
	public ResourceList getDiscardedCards()
	{
		return discardedCards;
	}
}
