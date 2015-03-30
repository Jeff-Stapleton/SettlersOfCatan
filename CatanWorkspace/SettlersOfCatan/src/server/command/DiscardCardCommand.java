package server.command;

import org.apache.log4j.Logger;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.TurnType;
import shared.comm.serialization.DiscardCardsRequest;

public class DiscardCardCommand implements ICommand<CatanModel> {
	private static final Logger log = Logger.getLogger(DiscardCardCommand.class);
	
	private CatanModel model;
	private Player player;
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
		if (catanModel.getPlayers()[request.getPlayerIndex()].hasDiscarded() ==  false)
		{
			ResourceList.moveResources(catanModel.getPlayers()[request.getPlayerIndex()].getResources(), catanModel.getBank(), request.getDiscardedCards());
			catanModel.getPlayers()[request.getPlayerIndex()].setDiscarded(true);
		}
		int count = 0;
		for (Player p : catanModel.getPlayers())
		{
			if (p.hasDiscarded() == true)
			{
				count++;
			}
		}
		
		if (count == 4)
		{
			catanModel.getTurnTracker().setStatus(TurnType.ROBBING);
			for (Player p : catanModel.getPlayers())
			{
				p.setDiscarded(false);
			}

		}
		return catanModel;
	}

	public void initialize(CatanModel catanModel) {
		model = catanModel;
		player = getPlayer();
		bank = catanModel.getBank();
		inventory = getInventory();
		discard = getDiscard();
	}
	
	public Player getPlayer() {
		int index = request.getPlayerIndex();
		return model.getPlayers()[index];
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
