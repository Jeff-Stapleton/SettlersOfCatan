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
		if (request.getWillAccept()) 
		{

			//execute
			int sender = catanModel.getTradeOffer().getSender();
			int receiver = catanModel.getTradeOffer().getReceiver();
			int brick = catanModel.getTradeOffer().getOffer().getBrick();
			int ore = catanModel.getTradeOffer().getOffer().getOre();
			int sheep = catanModel.getTradeOffer().getOffer().getSheep();
			int wheat = catanModel.getTradeOffer().getOffer().getWheat();
			int wood = catanModel.getTradeOffer().getOffer().getWood();
			
			int senderBrick = catanModel.getPlayers()[sender].getResources().getBrick() - brick;
			int senderOre = catanModel.getPlayers()[sender].getResources().getOre() - ore;
			int senderSheep = catanModel.getPlayers()[sender].getResources().getSheep() - sheep;
			int senderWheat = catanModel.getPlayers()[sender].getResources().getWheat() - wheat;
			int senderWood = catanModel.getPlayers()[sender].getResources().getWood() - wood;
			
			catanModel.getPlayers()[sender].getResources().setBrick(senderBrick);
			catanModel.getPlayers()[sender].getResources().setOre(senderOre);
			catanModel.getPlayers()[sender].getResources().setSheep(senderSheep);
			catanModel.getPlayers()[sender].getResources().setWheat(senderWheat);
			catanModel.getPlayers()[sender].getResources().setWood(senderWood);
			
			int receiverBrick = catanModel.getPlayers()[receiver].getResources().getBrick() + brick;
			int receiverOre = catanModel.getPlayers()[receiver].getResources().getOre() + ore;
			int receiverSheep = catanModel.getPlayers()[receiver].getResources().getSheep() + sheep;
			int receiverWheat = catanModel.getPlayers()[receiver].getResources().getWheat() + wheat;
			int receiverWood = catanModel.getPlayers()[receiver].getResources().getWood() + wood;
			
			catanModel.getPlayers()[receiver].getResources().setBrick(receiverBrick);	
			catanModel.getPlayers()[receiver].getResources().setOre(receiverOre);
			catanModel.getPlayers()[receiver].getResources().setSheep(receiverSheep);
			catanModel.getPlayers()[receiver].getResources().setWheat(receiverWheat);
			catanModel.getPlayers()[receiver].getResources().setWood(receiverWood);
		
		}
		
		catanModel.setTradeOffer(null);
		return catanModel;
	}

}
