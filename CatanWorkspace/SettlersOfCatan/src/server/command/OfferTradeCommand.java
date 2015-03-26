package server.command;

import org.apache.log4j.Logger;

import server.handlers.MovesFinishTurnHandler;
import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.TradeOffer;
import shared.comm.serialization.OfferTradeRequest;

public class OfferTradeCommand implements ICommand<CatanModel> {
	private static final Logger log = Logger.getLogger(OfferTradeCommand.class);
	
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
		
		//execute
		int brick = request.getOffer().getBrick();
		int ore = request.getOffer().getOre();
		int sheep = request.getOffer().getSheep();
		int wheat = request.getOffer().getWheat();
		int wood = request.getOffer().getWood();
		
		TradeOffer tradeOffer = new TradeOffer(request.getPlayerIndex(), new ResourceList(brick, wood, wheat, sheep, ore), request.getReceiver());
		catanModel.setTradeOffer(tradeOffer);
		return catanModel;
	}

}
