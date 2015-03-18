package server.comm.request;

import server.comm.response.CatanModelResponse;
import shared.Building;
import shared.CatanModel;
import shared.Hex;
import shared.Player;
import shared.comm.ServerException;
import shared.definitions.HexType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

/**
 * The Class MovesRollNumberRequest.
 * 
 * @pre
 * 	It is your turn
 * 	The client model�s status is �Rolling�
 * 
 * @post
 * 	The client model�s status is now in �Discarding� or �Robbing� or �Playing�
 * 	
 * 
 */
public class MovesBuildSettlementRequest extends Request<CatanModelResponse> {
	
	public MovesBuildSettlementRequest(CatanModel catanModel, VertexLocation vertexLocation, boolean free)
	{

		//execute
		int playerIndex = catanModel.getTurnTracker().getCurrentTurn();
		Player player = catanModel.getPlayers()[playerIndex];
		int x = vertexLocation.getX();
		int y = vertexLocation.getY(); 
		VertexDirection direction = vertexLocation.getDirection();
		Building settlement = new Building(playerIndex, new VertexLocation(x, y , direction));
		catanModel.getMap().getSettlements().add(settlement);
		catanModel.setVersion(catanModel.getVersion() + 1);
		player.setSettlements(player.getSettlements() - 1);
		player.setVictoryPoints(player.getVictoryPoints() + 1);
		
		if (!free) {
			player.getResources().setBrick(player.getResources().getBrick() - 1);
			player.getResources().setSheep(player.getResources().getSheep() - 1);
			player.getResources().setWood(player.getResources().getWood() - 1);
			player.getResources().setWheat(player.getResources().getWheat() - 1);
			
			catanModel.getBank().setBrick(catanModel.getBank().getBrick() + 1);
			catanModel.getBank().setSheep(catanModel.getBank().getSheep() + 1);
			catanModel.getBank().setWood(catanModel.getBank().getWood() + 1);
			catanModel.getBank().setWheat(catanModel.getBank().getWheat() + 1);
		}
		
		if (catanModel.getTurnTracker().getStatus().equals("SecondRound")) {
			
			HexLocation hexLoc = new HexLocation(x, y);
			HexLocation neighbor1 = null;
			HexLocation neighbor2 = null;
			
			switch(direction) {
			case NorthWest:
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.NorthWest);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.North);
				break;
			case NorthEast:
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.NorthEast);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.North);
				break;
			case East:
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.NorthEast);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.SouthEast);
				break;
			case SouthEast:
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.SouthEast);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.South);
				break;
			case SouthWest:
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.SouthWest);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.South);
				break;
			case West:
				neighbor1 = hexLoc.getNeighborLoc(EdgeDirection.NorthWest);
				neighbor2 = hexLoc.getNeighborLoc(EdgeDirection.SouthWest);
				break;
			}
			
			for (Hex hex : catanModel.getMap().getHexes()) {
				int hexX = hex.getLocation().getX();
				int hexY = hex.getLocation().getY();
				
				if ((hexX == hexLoc.getX() && hexY == hexLoc.getY()) ||
						(hexX == neighbor1.getX() && hexY == neighbor1.getY()) ||
						(hexX == neighbor2.getX() && hexY == neighbor2.getY())) {
					
					HexType resource = hex.getResource();
					
					if (resource != null) {
					
						switch (resource) {
						case BRICK:
								player.getResources().setBrick(player.getResources().getBrick()+1);
								catanModel.getBank().setBrick(catanModel.getBank().getBrick()-1);
							break;
						case ORE:
							player.getResources().setOre(player.getResources().getOre()+1);
							catanModel.getBank().setOre(catanModel.getBank().getOre()-1);
							break;
						case SHEEP:
								player.getResources().setSheep(player.getResources().getSheep()+1);
								catanModel.getBank().setSheep(catanModel.getBank().getSheep()-1);
							break;
						case WHEAT:
								player.getResources().setWheat(player.getResources().getWheat()+1);
								catanModel.getBank().setWheat(catanModel.getBank().getWheat()-1);
							break;
						case WOOD:
								player.getResources().setWood(player.getResources().getWood()+1);
								catanModel.getBank().setWood(catanModel.getBank().getWood()-1);
							break;
						}
					}
				}
			}
			
		}
		
		//checkForWinner(catanModel,playerIndex);
		
		//return catanModel;

	}

	@Override
	public CatanModelResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
