package server.command;

import shared.CatanModel;
import shared.Map;
import shared.Player;
import shared.Road;
import shared.comm.serialization.RoadBuildingRequest;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;

public class RoadBuildingCommand implements ICommand<CatanModel>
{
	
	private int owner;
	private EdgeLocation edge1;
	private EdgeLocation edge2;

	public RoadBuildingCommand(RoadBuildingRequest request) 
	{
		this.edge1 = new EdgeLocation(request.getSpot1().getX(), request.getSpot1().getY(), EdgeDirection.fromString(request.getSpot1().getDirection()));
		this.edge2 = new EdgeLocation(request.getSpot2().getX(), request.getSpot2().getY(), EdgeDirection.fromString(request.getSpot2().getDirection()));
		this.owner = request.getPlayerIndex();
	}

	/**
	 * Executes "Road Building Dev Card", removes the dev card from the players hand.  Awards the 
	 * player with the ability to place 2 free roads
	 *
	 * @pre The player does in deed have the card and it the correct turn/state and has enough roads
	 * @post completes the play road building card
	 * 
	 * @param a PlayerIndex, a LocationOne, a LocationTwo
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) 
	{
		Player player = catanModel.getPlayers()[owner];
	
		if (!player.hasPlayedDevCard()) 
		{
			Road road1 = new Road(owner, edge1);
			Road road2 = new Road(owner, edge2);
			
			
			Map map = catanModel.getMap();			
			map.getRoads().add(road1);
			map.getRoads().add(road2);
			
			player.setRoads(player.getRoads()-2);
			
			player.getOldDevCards().setRoadBuilding(player.getOldDevCards().getRoadBuilding() - 1);
			
			MapChecks.checkForLongestRoad(catanModel, owner);
			
			player.setPlayedDevCard(true);
		}
		//serverGameModel.setMap(map);
		return catanModel;
		
		
		
		
		
		
		/*
		// should be receiving a player index and two road edgeLocations here
		
		// can only play dev cards on your own turn, so whoevers turn it is, is the player playing the card
		Player thisPlayer = catanModel.getPlayers()[owner];
//		Player thisPlayer = catanModel.getPlayers()[playerIndex];
		
		// subtract 2 roads from the player
		thisPlayer.setRoads(thisPlayer.getRoads() - 2);
		
		// place roads into correct locations on the map
//		catanModel.getMap().buildRoad(thisPlayer.getPlayerIndex(), firstRoadLoc);
//		catanModel.getMap().buildRoad(thisPlayer.getPlayerIndex(), secondRoadLoc);
		
		// remove card from player
		thisPlayer.getOldDevCards().setRoadBuilding(thisPlayer.getOldDevCards().getRoadBuilding() - 1);
		thisPlayer.setPlayedDevCard(true);
		return catanModel;
		*/
	}

}
