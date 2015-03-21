package server.command;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.TradeOffer;
import shared.comm.serialization.OfferTradeRequest;

public class OfferTradeCommand implements ICommand<CatanModel> {
	private CatanModel model;
	private int instigator;
	private int investigator;
	private TradeOffer offer;
	private ResourceList trade;
	private OfferTradeRequest request;
	
	public OfferTradeCommand(OfferTradeRequest request) {
		this.request = request;
	}

	/**
	 * Executes the "Offer Trade", Player specifies what he/she is will to give
	 * in exchanged for his/her specified offer. Player identifies which player
	 * to trade with.
	 *
	 * @pre The player has the resources in questions and is the correct
	 *      turn/state
	 * @post completes the offer trade
	 * 
	 * @param a TradeOffer
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		initialize(catanModel);
		model.setTradeOffer(offer);
		return model;
	}
	
	public void initialize(CatanModel catanModel) {
		model = catanModel;
		instigator = request.getPlayerIndex();
		investigator = request.getReceiver();
		trade = request.getOffer();
		offer = getOffer(investigator, trade, instigator);
	}

	public int getPlayer() {
		return model.getTurnTracker().getCurrentTurn();
	}
	
	public TradeOffer getOffer(int investigator, ResourceList trade, int instigator) {
		TradeOffer tradeOffer = new TradeOffer(investigator, trade, investigator);
		return tradeOffer;
	}

}
