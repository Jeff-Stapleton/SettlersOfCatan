package shared;

public class TradeOffer 
{
	private int sender;
	private int receiver;
	private ResourceList offer;
	
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
		return sender;
	}

	/**
	 * Set the sender of the trade
	 * @param sender
	 */
	public void setSender(int sender) 
	{
		this.sender = sender;
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
}
