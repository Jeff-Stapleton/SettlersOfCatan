package server.command;

import java.util.ArrayList;
import java.util.List;

import shared.Building;
import shared.CatanModel;
import shared.Hex;
import shared.TurnType;
import shared.comm.serialization.RollNumberRequest;
import shared.definitions.HexType;
import shared.definitions.ResourceType;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class RollNumberCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Roll Number", Randomly choose 2 numbers between 1 and 6, then sums them together.
	 *
	 * @pre It is the correct turn/state
	 * @post completes the roll number
	 * 
	 * @param a PlayerIndex a RollNumber
	 */
	
	int number;
	
	public RollNumberCommand(RollNumberRequest request)
	{
		number = request.getNumber();
	}
	
	@Override
	public CatanModel execute(CatanModel catanModel) 
	{
		
		if(number==7){
			int player1TotResources = catanModel.getPlayers()[0].getResources().totalCount();
			int player2TotResources = catanModel.getPlayers()[1].getResources().totalCount();
			int player3TotResources = catanModel.getPlayers()[2].getResources().totalCount();
			int player4TotResources = catanModel.getPlayers()[3].getResources().totalCount();
			
			if (player1TotResources > 7 || player2TotResources > 7 || player3TotResources > 7 || player4TotResources > 7) {
				if (player1TotResources <= 7) {
					catanModel.getPlayers()[0].setDiscarded(true);
				}
				
				if (player2TotResources <= 7) {
					catanModel.getPlayers()[1].setDiscarded(true);
				}
				
				if (player3TotResources <= 7) {
					catanModel.getPlayers()[2].setDiscarded(true);
				}
				
				if (player4TotResources <= 7) {
					catanModel.getPlayers()[3].setDiscarded(true);
				}
				
				catanModel.getTurnTracker().setStatus(TurnType.DISCARDING);
			}
			else {
				catanModel.getTurnTracker().setStatus(TurnType.ROBBING);
			}
			
			return catanModel;
		}
		
		List<Building> cities = catanModel.getMap().getCities();
		List<Building> settlements = catanModel.getMap().getSettlements();
		List<Hex> hexes = catanModel.getMap().getHexes();
		List<Hex> withNumber=new ArrayList<Hex>();
		VertexLocation NE,E,SE,SW,W,NW,loc;
		int owner;
		HexType resource;
		
		//execute
		for(int i=0;i<hexes.size();i++){
			if(hexes.get(i).getNumber() == number && 
					catanModel.getMap().getRobber().getX() != hexes.get(i).getLocation().getX() &&
					catanModel.getMap().getRobber().getY() != hexes.get(i).getLocation().getY())
				withNumber.add(hexes.get(i));
		}
		
		for(int i=0;i<withNumber.size();i++){
			NE=new VertexLocation(withNumber.get(i).getLocation().getX(), withNumber.get(i).getLocation().getY(), VertexDirection.NorthEast).getNormalizedLocation();
			E=new VertexLocation(withNumber.get(i).getLocation().getX(), withNumber.get(i).getLocation().getY(), VertexDirection.East).getNormalizedLocation();
			SE=new VertexLocation(withNumber.get(i).getLocation().getX(), withNumber.get(i).getLocation().getY(), VertexDirection.SouthEast).getNormalizedLocation();
			SW=new VertexLocation(withNumber.get(i).getLocation().getX(), withNumber.get(i).getLocation().getY(), VertexDirection.SouthWest).getNormalizedLocation();
			W=new VertexLocation(withNumber.get(i).getLocation().getX(), withNumber.get(i).getLocation().getY(), VertexDirection.West).getNormalizedLocation();
			NW=new VertexLocation(withNumber.get(i).getLocation().getX(), withNumber.get(i).getLocation().getY(), VertexDirection.NorthWest).getNormalizedLocation();
			resource = withNumber.get(i).getResource(); 
			for(int c=0;c<cities.size();c++){
				loc=cities.get(c).getLocation().getNormalizedLocation();
				owner=cities.get(c).getOwner();
				if(loc.equals(NE))
				{
					MapChecks.incrementResources(catanModel,owner,resource,2);
				}
				if(loc.equals(E)){
					MapChecks.incrementResources(catanModel,owner,resource,2);
				}
				if(loc.equals(SE)){
					MapChecks.incrementResources(catanModel,owner,resource,2);
				}
				if(loc.equals(SW)){
					MapChecks.incrementResources(catanModel,owner,resource,2);
				}
				if(loc.equals(W)){
					MapChecks.incrementResources(catanModel,owner,resource,2);
				}
				if(loc.equals(NW)){
					MapChecks.incrementResources(catanModel,owner,resource,2);
				}
			}
			for(int s=0;s<settlements.size();s++){
				loc=settlements.get(s).getLocation().getNormalizedLocation();
				owner=settlements.get(s).getOwner();
				if(loc.equals(NE)){
					MapChecks.incrementResources(catanModel,owner,resource,1);
				}
				if(loc.equals(E)){
					MapChecks.incrementResources(catanModel,owner,resource,1);
				}
				if(loc.equals(SE)){
					MapChecks.incrementResources(catanModel,owner,resource,1);
				}
				if(loc.equals(SW)){
					MapChecks.incrementResources(catanModel,owner,resource,1);
				}
				if(loc.equals(W)){
					MapChecks.incrementResources(catanModel,owner,resource,1);
				}
				if(loc.equals(NW)){
					MapChecks.incrementResources(catanModel,owner,resource,1);
				}
			}
		}
		
		catanModel.getTurnTracker().setStatus(TurnType.PLAYING);
		
		return catanModel;
	}

}
