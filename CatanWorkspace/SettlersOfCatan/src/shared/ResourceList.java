package shared;

public class ResourceList {

	private int brick;
	private int wood;
	private int grain;
	private int wool;
	private int ore;
	
	ResourceList(){}
	
	ResourceList(int brick, int wood, int grain, int wool, int ore){
		setBrick(brick);
		setWood(wood);
		setGrain(grain);
		setWool(wool);
		setOre(ore);
	}
	
	/**
	 * Move resources (trade) to given player
	 * @param player
	 * @param trade
	 */
	public void moveResources(Player receiver, ResourceList trade){
		setBrick(getBrick() - trade.getBrick());
		setWood(getWood() - trade.getWood());
		setGrain(getGrain() - trade.getGrain());
		setWool(getWool() - trade.getWool());
		setOre(getOre() - trade.getOre());
		
		receiver.getResources().setBrick(getBrick() + trade.getBrick());
		receiver.getResources().setWood(getWood() + trade.getWood());
		receiver.getResources().setGrain(getGrain() + trade.getGrain());
		receiver.getResources().setWool(getWool() + trade.getWool());
		receiver.getResources().setOre(getOre() + trade.getOre());
	}
	
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
