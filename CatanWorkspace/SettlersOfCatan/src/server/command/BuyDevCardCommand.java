package server.command;

import shared.CanCan;
import shared.CatanModel;
import shared.DevCardList;
import shared.Player;
import shared.comm.serialization.BuyDevCardRequest;

public class BuyDevCardCommand implements ICommand<CatanModel>
{	
	private CatanModel model;
	private Player player;
	private BuyDevCardRequest request;

	public BuyDevCardCommand(BuyDevCardRequest request) {
		this.request = request;
	}

	/**
	 * Executes "Buy Dev Card", subtracts the resources from the instigator,
	 * adds a dev card to the players hand, and decrease from the bank of dev cards
	 *
	 * @pre The player has enough resources, enough card and is the proper phase/turn
	 * @post completes the buy dev card
	 * 
	 * @param a PlayerIndex
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		initialize(catanModel);
		player.buyDevCard(model.getBank(), model.getDeck());
		return model;
	}
	
	public void initialize(CatanModel catanModel) {
		model = catanModel;
		player = getPlayer();
	}

	public Player getPlayer() {
		int index = request.getPlayerIndex();
		return model.getPlayers()[index];
	}

}
