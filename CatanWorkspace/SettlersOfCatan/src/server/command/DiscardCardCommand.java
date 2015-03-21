package server.command;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.comm.serialization.DiscardCardsRequest;

public class DiscardCardCommand implements ICommand<CatanModel> {
	private CatanModel model;
	private ResourceList inventory;
	private ResourceList bank;
	private ResourceList discard;
	private DiscardCardsRequest request;

	public DiscardCardCommand(DiscardCardsRequest request) {
		this.request = request;
	}

	/**
	 * Executes "Discard Card", removes the assigned card(s) from the players
	 * hand
	 *
	 * @pre The player has the assigned cards to discard
	 * @post completes the discard card
	 * 
	 * @param a PlayerIndex, Cards
	 */

	@Override
	public CatanModel execute(CatanModel catanModel) {
		initialize(catanModel);
		ResourceList.moveResources(inventory, bank, discard);
		return model;
	}

	public void initialize(CatanModel catanModel) {
		model = catanModel;
		bank = catanModel.getBank();
		inventory = getInventory();
		discard = getDiscard();
	}

	public ResourceList getInventory() {
		int index = request.getPlayerIndex();
		Player player = model.getPlayers()[index];
		return player.getResources();
	}

	public ResourceList getDiscard() {
		ResourceList offer = request.getDiscardedCards();
		ResourceList.invertResources(offer);
		return offer;
	}

}
