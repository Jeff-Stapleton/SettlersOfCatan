package server.command;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.comm.serialization.YearOfPlentyRequest;
import shared.definitions.ResourceType;

public class YearOfPlentyCommand implements ICommand<CatanModel>{
	private CatanModel model;
	private Player player;
	private ResourceList inventory;
	private ResourceList bank;
	private ResourceType resourceOne;
	private ResourceType resourceTwo;
	private YearOfPlentyRequest request;
	
	public YearOfPlentyCommand(YearOfPlentyRequest request) {
		this.request = request;
	}

	/**
	 * Executes "Year of Plenty Dev Card", removes the dev card from the players hand.  Awards 
	 * the player any two resources of his/her choosing
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play year of plenty card
	 * 
	 * @param a PlayerIndex, a ResourceOne, a ResourceTwo
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		initialize(catanModel);

		inventory.setResource(resourceOne, inventory.getResource(resourceOne) + 1);
		inventory.setResource(resourceTwo, inventory.getResource(resourceTwo) + 1);

		bank.setResource(resourceOne, bank.getResource(resourceOne) - 1);
		bank.setResource(resourceTwo, bank.getResource(resourceTwo) - 1);
		
		player.getOldDevCards().setYearOfPlenty(player.getOldDevCards().getYearOfPlenty() - 1);
		player.setPlayedDevCard(true);
		
		return catanModel;
	}
	
	public void initialize(CatanModel catanModel) {
		model = catanModel;
		player = getPlayer();
		inventory = player.getResources();
		bank = catanModel.getBank();
		resourceOne = getResourceType(request.getResource1());
		resourceTwo = getResourceType(request.getResource2());
	}
	
	public Player getPlayer() {
		int index = request.getPlayerIndex();
		return model.getPlayers()[index];
	}
	
	public ResourceType getResourceType(String resource) {
		ResourceType resourceType = null;
		switch (resource) {
			case "wood": {
				resourceType = ResourceType.WOOD;
				break; 
			}
			case "brick": {
				resourceType = ResourceType.BRICK;
				break;
			}
			case "sheep": {
				resourceType = ResourceType.SHEEP;
				break;
			}
			case "wheat": {
				resourceType = ResourceType.WHEAT;
				break;
			}
			case "ore": {
				resourceType = ResourceType.ORE;
				break;
			}
		}
		return resourceType;
	}

}
