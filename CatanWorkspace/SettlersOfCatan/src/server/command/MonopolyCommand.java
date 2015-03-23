package server.command;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.comm.serialization.MonopolyRequest;
import shared.definitions.ResourceType;

public class MonopolyCommand implements ICommand<CatanModel> {
	private CatanModel model;
	private Player player;
	private ResourceType resource;
	private ResourceList inventory;
	private MonopolyRequest request;
	
	public MonopolyCommand(MonopolyRequest request) {
		this.request = request;
	}

	/**
	 * Executes "Monopoly Dev Card", removes the dev card from the players hand.  Takes
	 * every resource, of a specified type, from all other players and adds them to the player
	 * hand
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play monopoly card
	 * 
	 * @param a PlayerIndex, a Resource
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		initialize(catanModel);	
		
		int count = 0;
		for(Player p : model.getPlayers()) {
			if (p.getPlayerID() != player.getPlayerID()) {
				count += p.getResources().getResource(resource);
				p.getResources().setResource(resource, 0);
			}
		}
		inventory.setResource(resource, inventory.getResource(resource) + count);

		player.getOldDevCards().setMonopoly(player.getOldDevCards().getMonopoly() - 1);
		player.setPlayedDevCard(true);
		return model;
	}
	
	public void initialize(CatanModel catanModel) {
		model = catanModel;
		player = getPlayer();
		inventory = player.getResources();
		resource = getResource(request.getResource());
	}

	public Player getPlayer() {
		int index = request.getPlayerIndex();
		return model.getPlayers()[index];
	}

	public ResourceType getResource(String resource) {
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
