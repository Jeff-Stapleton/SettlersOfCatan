package server.command;

import java.util.ArrayList;

import shared.Building;
import shared.CatanModel;
import shared.Player;
import shared.comm.serialization.BuildCityRequest;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class BuildCityCommand implements ICommand<CatanModel>
{

	/**
	 * Executes "Build City", subtracts the resources from the instigator
	 * subtracts a city from unbuilt city pool and places it on a vertex location
	 *
	 * @pre The player has enough resources, enough cities and is a valid vertex location
	 * @post completes the build city
	 * 
	 * @param a PlayerIndex, a Location, a isFreeBoolean
	 */
	private int playerIndex;
	private int x;
	private int y;
	private VertexDirection direction;
	
	
	public BuildCityCommand(BuildCityRequest request)
	{
		this.playerIndex = request.getPlayerIndex();
		this.x = request.getVertexLocation().getX();
		this.y = request.getVertexLocation().getY();
		this.direction = VertexDirection.fromString(request.getVertexLocation().getDirection());
	}
	
	@Override
	public CatanModel execute(CatanModel catanModel) {
		
		//execute
		Player player = catanModel.getPlayers()[playerIndex];
		
		Building city = new Building(playerIndex, new VertexLocation(x, y , direction));
		catanModel.getMap().getCities().add(city);
		catanModel.setVersion(catanModel.getVersion() + 1);
		
		ArrayList<Building> settlements = (ArrayList<Building>) catanModel.getMap().getSettlements();
		
		for (int i = 0; i < settlements.size(); i++) {
			VertexLocation loc = settlements.get(i).getLocation().getNormalizedLocation();
			if (settlements.get(i).getLocation().getX() == x &&
					settlements.get(i).getLocation().getY() == y &&
					settlements.get(i).getLocation().getDirection().equals(direction)) {
				settlements.remove(i);
				break;
			}
			
			if (loc.getX() == x &&
					loc.getY() == y &&
					loc.getDirection().equals(direction)) {
				settlements.remove(i);
				break;
			}
				
		}
		
		player.setCities(player.getCities() - 1);
		player.setSettlements(player.getSettlements() + 1);

		//3 ore 2wheat
		int newOre = player.getResources().getOre() - 3;
		int newWheat = player.getResources().getWheat() - 2;
		player.getResources().setOre(newOre);
		player.getResources().setWheat(newWheat);
		
		catanModel.getBank().setOre(catanModel.getBank().getOre() + 3);
		catanModel.getBank().setWheat(catanModel.getBank().getWheat() + 3);
		
		player.setVictoryPoints(player.getVictoryPoints() + 1);
		
		MapChecks.checkForWinner(catanModel,playerIndex);
		
		return catanModel;
	}

}
