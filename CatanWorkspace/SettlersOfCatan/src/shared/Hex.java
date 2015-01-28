package shared;

import shared.definitions.HexType;
import shared.locations.HexLocation;

/**
 * The Class Hex.
 */
public class Hex {
	
	/** The location. */
	private HexLocation location;
	
	/** The type. */
	private HexType type;
	
	/** The number. */
	private Integer number;
	
	/** The has robber. */
	private Boolean hasRobber;
	
	/**
	 * Instantiates a new hex.
	 *
	 * @param location the location
	 * @param type the type
	 * @param number the number
	 */
	public Hex(HexLocation location, HexType type, Integer number)
	{
		this.location = location;
		this.type = type;
		this.number = number;
		hasRobber = false;
	}
	
	/*
	 * Get the location of the Hex
	 * 
	 * @Return The hex location
	 */
	public HexLocation getLocation() {
		return location;
	}

	/*
	 * Sets the location of the hex 
	 * 
	 * @Return void
	 */
	public void setLocation(HexLocation location) {
		this.location = location;
	}

	/*
	 * Get the resource type of the hex
	 * 
	 * @Return the resource type
	 */
	public HexType getType() {
		return type;
	}

	/*
	 * Set the resource type of the hex
	 * 
	 * @Return void
	 */
	public void setType(HexType type) {
		this.type = type;
	}

	/*
	 * Return the roll number of the Hex
	 * 
	 * @Return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/*
	 * Set the roll number of the Hex
	 * 
	 * @Return void
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}

	/*
	 * check if the specified Hex has the robber placed on it
	 * 
	 * @Return hasRobber
	 */
	public Boolean hasRobber()
	{
		return hasRobber;
	}
	
	/**
	 * Give robber.
	 */
	public void giveRobber()
	{
		hasRobber = true;
	}
	
	/**
	 * Take robber.
	 */
	public void takeRobber()
	{
		hasRobber = false;
	}
	
	/**
	 * Can give robber.
	 *
	 * @return the boolean
	 */
	public Boolean canGiveRobber()
	{
		if (hasRobber)
			return false;
		else 
			return true;
	}
}
