package shared;

public class ResourceList {

	private int brick;
	private int wood;
	private int grain;
	private int wool;
	private int ore;
	
	ResourceList(){}
	

	/**
	 * @param brick
	 * @param wood
	 * @param grain
	 * @param wool
	 * @param ore
	 */
	ResourceList(int brick, int wood, int grain, int wool, int ore){
		setBrick(brick);
		setWood(wood);
		setGrain(grain);
		setWool(wool);
		setOre(ore);
	}
	
	/**
	 * Move resources (trade) from one ResourceList to another ResourceList
	 * @param sender
	 * @param receiver
	 * @param trade
	 */
	public static void moveResources(ResourceList sender, ResourceList receiver, ResourceList trade){
		sender.setBrick(sender.getBrick() - trade.getBrick());
		sender.setWood(sender.getWood() - trade.getWood());
		sender.setGrain(sender.getGrain() - trade.getGrain());
		sender.setWool(sender.getWool() - trade.getWool());
		sender.setOre(sender.getOre() - trade.getOre());
		
		receiver.setBrick(receiver.getBrick() + trade.getBrick());
		receiver.setWood(receiver.getWood() + trade.getWood());
		receiver.setGrain(receiver.getGrain() + trade.getGrain());
		receiver.setWool(receiver.getWool() + trade.getWool());
		receiver.setOre(receiver.getOre() + trade.getOre());
	}
	
	/**
	 * Get total number of resource cards
	 * @return
	 */
	public int totalCount(){
		return getBrick()+getWood()+getGrain()+getWool()+getOre();
	}
	
	/**
	 * Get number of brick
	 * @return
	 */
	public int getBrick() {
		return brick;
	}
	/**
	 * Set number of brick
	 * @param brick
	 */
	public void setBrick(int brick) {
		this.brick = brick;
	}
	/**
	 * Get number of ore
	 * @return
	 */
	public int getOre() {
		return ore;
	}
	/**
	 * Set number of ore
	 * @param ore
	 */
	public void setOre(int ore) {
		this.ore = ore;
	}
	/**
	 * Get number of wool
	 * @return
	 */
	public int getWool() {
		return wool;
	}
	/**
	 * Set number of wool
	 * @param wool
	 */
	public void setWool(int wool) {
		this.wool = wool;
	}
	/**
	 * Get number of grain
	 * @return
	 */
	public int getGrain() {
		return grain;
	}
	/**
	 * Set number of grain
	 * @param grain
	 */
	public void setGrain(int grain) {
		this.grain = grain;
	}
	/**
	 * Get number of wood
	 * @return
	 */
	public int getWood() {
		return wood;
	}
	/**
	 * Set number of wood
	 * @param wood
	 */
	public void setWood(int wood) {
		this.wood = wood;
	}
	
	
	
}
