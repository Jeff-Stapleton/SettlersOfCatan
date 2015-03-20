package server.command;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.comm.serialization.AcceptTradeRequest;
import shared.comm.serialization.OfferTradeRequest;

public class AcceptTradeCommand implements ICommand<CatanModel> {
	private CatanModel model;
	private boolean willAccept;
	private Player player;
	private ResourceList sender;
	private ResourceList receiver;
	private ResourceList trade;
	private AcceptTradeRequest request;

	public AcceptTradeCommand(AcceptTradeRequest request) {
		this.request = request;
		this.willAccept = request.getWillAccept();
	}

	/**
	 * Executes the "Accept Trade", exchanges the resources between the
	 * instigator and the investigator
	 *
	 * @pre is a valid trade offer
	 * @post completes the domestic trade
	 * 
	 * @param a
	 *            PlayerIndex, a willAcceptBoolean
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		if (willAccept == true) {
			initialize(catanModel);
			ResourceList.moveResources(sender, receiver, trade);
			return null;
		} 
		else {
			return null;
		}
	}

	public void initialize(CatanModel catanModel) {
		model = catanModel;
		player = getPlayer();
		receiver = player.getResources();
		sender = getSender();
		trade = getTrade();
	}

	public Player getPlayer() {
		int index = request.getPlayerIndex();
		return model.getPlayers()[index];
	}

	public ResourceList getSender() {
		int index = model.getTradeOffer().getSender();
		Player players[] = model.getPlayers();
		return players[index].getResources();
	}

	public ResourceList getTrade() {
		return model.getTradeOffer().getOffer();
	}

}
