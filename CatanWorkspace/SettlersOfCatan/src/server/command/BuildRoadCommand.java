package server.command;

import shared.CatanModel;
import shared.Player;
import shared.Road;
import shared.comm.serialization.BuildRoadRequest;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class BuildRoadCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Build Road", subtracts the resources from the instigator
	 * subtracts a road from unbuilt road pool and places it on a edge location
	 *
	 * @pre The player has enough resources, enough roads and is a valid edge location
	 * @post completes the build road
	 * 
	 * @param a PlayerIndex, a Location, a isFreeBoolean
	 */
	
	private int playerIndex;
	private int x;
	private int y;
	private EdgeDirection direction;
	private boolean isFree;
	
	
	public BuildRoadCommand(BuildRoadRequest request)
	{
		this.playerIndex = request.getPlayerIndex();
		this.x = request.getRoadLocation().getX();
		this.y = request.getRoadLocation().getY();
		this.direction = EdgeDirection.fromString(request.getRoadLocation().getDirection());
		this.isFree = request.getFree();
	}
	
	@Override
	public CatanModel execute(CatanModel catanModel) 
	{
		Player player = catanModel.getPlayers()[playerIndex];
		
		Road road = new Road(playerIndex, new EdgeLocation(x, y , direction));
		
		catanModel.getMap().getRoads().add(road);

		catanModel.setVersion(catanModel.getVersion() + 1);
		
		player.setRoads(player.getRoads() - 1);
		
		if (!isFree) {
			player.getResources().setBrick(player.getResources().getBrick() - 1);
			player.getResources().setWood(player.getResources().getWood() - 1);
			
			catanModel.getBank().setBrick(catanModel.getBank().getBrick() + 1);
			catanModel.getBank().setWood(catanModel.getBank().getWood() + 1);
		}

		MapChecks.checkForLongestRoad(catanModel, playerIndex);
		
		return catanModel;
	}

}
