package shared.player;

public class TradeOffer {

	private int sender;
	private int receiver;
	private ResourceList offer;
	
	/**
	 * Accepts the offered trade
	 */
	public void accept(){}
	
	/**
	 * Declines the offered trade
	 */
	public void decline(){}

	/**
	 * Get the sender of the trade
	 * @return
	 */
	public int getSender() {
		return sender;
	}

	/**
	 * Set the sender of the trade
	 * @param sender
	 */
	public void setSender(int sender) {
		this.sender = sender;
	}

	/**
	 * Get the receiver of the trade
	 * @return
	 */
	public int getReceiver() {
		return receiver;
	}

	/**
	 * Set the receiver of the trade
	 * @param receiver
	 */
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}

	/**
	 * Get the offered trade
	 * @return
	 */
	public ResourceList getOffer() {
		return offer;
	}

	/**
	 * Set the offered trade
	 * @param offer
	 */
	public void setOffer(ResourceList offer) {
		this.offer = offer;
	}

}
