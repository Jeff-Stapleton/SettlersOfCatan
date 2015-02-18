package shared;

import com.google.gson.annotations.SerializedName;

/**
 * Enum to determine what state the map is currently in.
 *
 * @author JJ
 */
public enum TurnType 
{
	/** The rolling. */
	@SerializedName("Rolling")
	ROLLING,
	
	/** The robbing. */
	@SerializedName("Robbing")
	ROBBING,
	
	/** The playing. */
	@SerializedName("Playing")
	PLAYING,
	
	/** The discarding. */
	@SerializedName("Discarding")
	DISCARDING,
	
	/** The first round. */
	@SerializedName("FirstRound")
	FIRST_ROUND,
	
	/** The second round. */
	@SerializedName("SecondRound")
	SECOND_ROUND
}
