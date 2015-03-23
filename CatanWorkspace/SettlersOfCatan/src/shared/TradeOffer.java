package shared;

public class TradeOffer
{
	private String type;
	private int playerIndex;
	private int receiver;
	private ResourceList offer;
	
	public TradeOffer(int playerIndex, ResourceList offer, int receiver) {
		this.type = "offerTrade";
		this.playerIndex = playerIndex;
		this.receiver = receiver;
		this.offer = offer;
	}
	
	/**
	 * Accepts the offered trade
	 */
	public void accept()
	{
		// This function doesn't belong in here.  This object should just store the data 
		// while the logic happens in the CatanModel
	}
	
	/**
	 * Declines the offered trade
	 */
	public void decline()
	{
		// This function doesn't belong in here.  This object should just store the data 
		// while the logic happens in the CatanModel
	}

	/**
	 * Get the sender of the trade
	 * @return
	 */
	public int getSender() 
	{
		return playerIndex;
	}

	/**
	 * Set the sender of the trade
	 * @param sender
	 */
	public void setSender(int playerIndex) 
	{
		this.playerIndex = playerIndex;
	}

	/**
	 * Get the receiver of the trade
	 * @return
	 */
	public int getReceiver() 
	{
		return receiver;
	}

	/**
	 * Set the receiver of the trade
	 * @param receiver
	 */
	public void setReceiver(int receiver) 
	{
		this.receiver = receiver;
	}

	/**
	 * Get the offered trade
	 * @return
	 */
	public ResourceList getOffer() 
	{
		return offer;
	}

	/**
	 * Set the offered trade
	 * @param offer
	 */
	public void setOffer(ResourceList offer) 
	{
		this.offer = offer;
	}
	
	@Override
	public String toString()
	{
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("type : ").append(type).append(",\n");
		string.append("playerIndex : ").append(playerIndex).append(",\n");
		string.append("receiver : ").append(receiver).append(",\n");
		string.append("offer : ").append(offer).append("\n");
		
		string.append("}");
		
		return string.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((offer == null) ? 0 : offer.hashCode());
		result = prime * result + playerIndex;
		result = prime * result + receiver;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TradeOffer other = (TradeOffer) obj;
		if (offer == null) {
			if (other.offer != null)
				return false;
		} else if (!offer.equals(other.offer))
			return false;
		if (playerIndex != other.playerIndex)
			return false;
		if (receiver != other.receiver)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public void reset() {
		type = "";
		playerIndex = -1;
		receiver = -1;
		offer = null;
	}
}
