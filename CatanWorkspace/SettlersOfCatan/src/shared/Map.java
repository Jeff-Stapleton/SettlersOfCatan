package shared;
import java.util.Random;

public class Map 
{
	private Hex[][] hexes;
	private Port ports;
	private Road roads;
	private Building settlements;
	private Building cities;
	private Integer radius;
	private Robber robber;
	
	
	public Map()
	{
		
		
		
	}
	
	/*
	 * This method is called after the dice are rolled.  
	 * The 'chit' is checked and if a resource has the rolled that
	 * value resources are distributed to all players built there
	 * 
	 * @Param ArrayList<Player> playerList
	 * 
	 * @Return void
	 */
	public void handOutResources(int roll)
	{
		
	}
	
	/*
	 * When a 7 is rolled this method is called and allows the 
	 * user to place the robber on any Hex on the map
	 * 
	 * @Param Hex location
	 * 
	 * @Return void
	 */
	public void moveRobber()
	{
		 
	}
	 
	/*
	 * This method is a precursor for the buildSettlement method call.  Check to make sure 
	 * the edge or the vertex is a valid build location  
	 * 
	 * @Param VertexLocation location
	 * 
	 * @Return Can Build
	 */
	public boolean canBuildSettlement()
	{
		return true;
	}
	
	/*
	 * Build the Settlement at the designated location
	 * 
	 * @Param VertexLocation location
	 * 
	 * @Return void
	 */
	public void buildSettlement()
	{
		 
	}
	 
	/*
	 * This method is a precursor for the buildCity method call.  Check to make sure 
	 * the edge or the vertex is a valid build location
	 * 
	 * @Param VertexLocation location
	 * 
	 * @Return Can Build
	 */
	public boolean canBuildCity()
	{
		return true;
	}
	 
	/*
	 * Build the City at the designated location
	 * 
	 * @Param VertexLocation location
	 * 
	 * @Return void
	 */
	public void buildCity()
	{
		 
	}
	
	/*
	 * This method is a precursor for the buildRoad method call.  Check to make sure 
	 * the edge is a valid build location
	 * 
	 * @Param EdgeLocation location
	 * 
	 * @Return Can Build
	 */
	public boolean canBuildRoad(int player)
	{
		
		return true;
	}
	 
	/*
	 * Build the road at the designated location
	 * 
	 * @Param EdgeLocation location
	 * 
	 * @Return void
	 */
	public void buildRoad()
	{
		 
	}
	 
	/*
	 * The method scans the map to calculate the longest road and awards 
	 * "longest road" to who owns it
	 * 
	 * @Return void
	 */
	public void calculateLongestRoad()
	{
		 
	}
}
