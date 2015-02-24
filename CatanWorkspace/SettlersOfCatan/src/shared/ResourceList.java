package shared;

import shared.definitions.ResourceType;

public class ResourceList {

	private int brick;
	private int wood;
	private int wheat;
	private int sheep;
	private int ore;
	
	public ResourceList(){}
	

	/**
	 * @param brick
	 * @param wood
	 * @param wheat
	 * @param sheep
	 * @param ore
	 */
	public ResourceList(int brick, int wood, int wheat, int sheep, int ore){
		setBrick(brick);
		setWood(wood);
		setWheat(wheat);
		setSheep(sheep);
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
		sender.setWheat(sender.getWheat() - trade.getWheat());
		sender.setSheep(sender.getSheep() - trade.getSheep());
		sender.setOre(sender.getOre() - trade.getOre());
		
		receiver.setBrick(receiver.getBrick() + trade.getBrick());
		receiver.setWood(receiver.getWood() + trade.getWood());
		receiver.setWheat(receiver.getWheat() + trade.getWheat());
		receiver.setSheep(receiver.getSheep() + trade.getSheep());
		receiver.setOre(receiver.getOre() + trade.getOre());
	}
	
	public static boolean hasResourcesCheck(ResourceList listInQuestion, ResourceList needed){
		if (listInQuestion.getBrick() < needed.getBrick())
			return false;
		if (listInQuestion.getWheat() < needed.getWheat())
			return false;
		if (listInQuestion.getOre() < needed.getOre())
			return false;
		if (listInQuestion.getWood() < needed.getWood())
			return false;
		if (listInQuestion.getSheep() < needed.getSheep())
			return false;
				
		return true;
	}
	
	public static ResourceList invertResources(ResourceList list){
		ResourceList newList = new ResourceList();
		newList.setBrick(list.getBrick()*-1);
		newList.setWheat(list.getWheat()*-1);
		newList.setOre(list.getOre()*-1);
		newList.setWood(list.getWood()*-1);
		newList.setSheep(list.getSheep()*-1);
		return newList;
	}
	
	/**
	 * Get total number of resource cards
	 * @return
	 */
	public int totalCount(){
		return getBrick()+getWood()+getWheat()+getSheep()+getOre();
	}
	
	public int getResource(ResourceType type){
		switch(type){
		case BRICK:
			return getBrick();
		case ORE:
			return getOre();
		case SHEEP:
			return getSheep();
		case WHEAT:
			return getWheat();
		case WOOD:
			return getWood();
		default:
			return 0;
		}
	}
	
	public void setResource(ResourceType type, int count){
		switch(type){
		case BRICK:
			setBrick(count);
		case ORE:
			setOre(count);
		case SHEEP:
			setSheep(count);
		case WHEAT:
			setWheat(count);
		case WOOD:
			setWood(count);
		}
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
	 * Get number of sheep
	 * @return
	 */
	public int getSheep() {
		return sheep;
	}
	/**
	 * Set number of sheep
	 * @param sheep
	 */
	public void setSheep(int sheep) {
		this.sheep = sheep;
	}
	/**
	 * Get number of wheat
	 * @return
	 */
	public int getWheat() {
		return wheat;
	}
	/**
	 * Set number of wheat
	 * @param wheat
	 */
	public void setWheat(int wheat) {
		this.wheat = wheat;
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
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder("{\n");
		
		string.append("brick : ").append(brick).append(",\n");
		string.append("wood : ").append(wood).append(",\n");
		string.append("sheep : ").append(sheep).append(",\n");
		string.append("wheat : ").append(wheat).append(",\n");
		string.append("ore : ").append(ore).append(",\n");

		string.append("}");
		
		return string.toString();
	}
	
}
