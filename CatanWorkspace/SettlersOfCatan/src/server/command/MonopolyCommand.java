package server.command;

import shared.CatanModel;
import shared.DevCardList;
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
		
		int owner = request.getPlayerIndex();
		Player player=catanModel.getPlayers()[owner];
		Player other;
		
		if (!player.hasPlayedDevCard()) {
			String resource = request.getResource();
			int amount=0;
			for(int i=0;i<catanModel.getPlayers().length;i++){
				if(i!=owner){
					other=catanModel.getPlayers()[i];
					switch(resource){
					case "ore":
						if(other.getResources().getOre()>0){
							amount=other.getResources().getOre();
							player.getResources().setOre(player.getResources().getOre()+amount);
							other.getResources().setOre(0);
						}
						break;
					case "sheep":
						if(other.getResources().getSheep()>0){
							amount=other.getResources().getSheep();
							player.getResources().setSheep(player.getResources().getSheep()+amount);
							other.getResources().setSheep(0);
						}
						break;
					case "wood":
						if(other.getResources().getWood()>0){
							amount=other.getResources().getWood();
							player.getResources().setWood(player.getResources().getWood()+amount);
							other.getResources().setWood(0);
						}
						break;
					case "wheat":
						if(other.getResources().getWheat()>0){
							amount=other.getResources().getWheat();
							player.getResources().setWheat(player.getResources().getWheat()+amount);
							other.getResources().setWheat(0);
						}
						break;
					case "brick":
						if(other.getResources().getBrick()>0){
							amount=other.getResources().getBrick();
							player.getResources().setBrick(player.getResources().getBrick()+amount);
							other.getResources().setBrick(0);
						}
						break;
					}
				}
			}
			player.setPlayedDevCard(true);
		}
		
		DevCardList cards = player.getOldDevCards();
		cards.setMonopoly(cards.getMonopoly()-1);
		catanModel.getPlayers()[owner].setOldDevCards(cards);
		
		return catanModel;
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
