package server.command;

import org.apache.log4j.Logger;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.comm.serialization.AcceptTradeRequest;
import shared.comm.serialization.OfferTradeRequest;

public class AcceptTradeCommand implements ICommand<CatanModel> {
	private static final Logger log = Logger.getLogger(OfferTradeCommand.class);
	
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
	 * @param a PlayerIndex, a willAcceptBoolean
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		if (willAccept == true) {
			log.trace("Initiate Accept Trade");
			initialize(catanModel);
			ResourceList.moveResources(sender, receiver, trade);
			return catanModel;
		} 
		else {
			log.trace("Initiate Decline Trade");
			return catanModel;
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
