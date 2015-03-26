package server.command;

import org.apache.log4j.Logger;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.comm.serialization.MaritimeTradeRequest;
import shared.definitions.ResourceType;
import shared.locations.VertexLocation;

public class MaritimeTradeCommand implements ICommand<CatanModel> {
	private static final Logger log = Logger.getLogger(OfferTradeCommand.class);
	
	private CatanModel model;
	private Player player;
	private ResourceList inventory;
	private ResourceList bank;
	private ResourceList trade;
	private MaritimeTradeRequest request;

	public MaritimeTradeCommand(MaritimeTradeRequest request) {
		this.request = request;
	}

	/**
	 * Executes "Maritime Trade", subtracts the given resources from the
	 * instigator and add the get resources to the instigator.
	 *
	 * @pre Bank and player have enough resources, players is built on a port
	 *      and it correct turn/state
	 * @post completes the maritime trade
	 * 
	 * @param a
	 *            PlayerIndex, a Ratio, a GetResource, a GiveResource
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		log.trace("Initiate Maritime Trade");
		initialize(catanModel);
		ResourceList.moveResources(inventory, bank, trade);
		return catanModel;
	}

	public void initialize(CatanModel catanModel) {
		model = catanModel;
		player = getPlayer();
		bank = catanModel.getBank();
		inventory = player.getResources();
		trade = getTrade();
	}

	public Player getPlayer() {
		int index = request.getPlayerIndex();
		return model.getPlayers()[index];
	}

	public ResourceList getTrade() {
		String giveResource = request.getOutputResource();
		String getResource = request.getInputResource();
		int ratio = request.getRatio();
		ResourceList offer = new ResourceList();
		setTradeGive(giveResource, ratio, offer);
		setTradeGet(getResource, offer);
		return offer;
	}

	public void setTradeGive(String giveResource, int ratio, ResourceList offer) {
		log.trace(giveResource);
		switch (giveResource) {
			case "wood": {
				offer.setWood(ratio);
				break;
			}
			case "brick": {
				offer.setBrick(ratio);
				break;
			}
			case "sheep": {
				offer.setSheep(ratio);
				break;
			}
			case "wheat": {
				offer.setWheat(ratio);
				break;
			}
			case "ore": {
				offer.setOre(ratio);
				break;
			}
		}
	}

	public void setTradeGet(String getResource, ResourceList offer) {
		log.trace(getResource);
		switch (getResource) {
			case "wood": {
				offer.setWood(-1);
				break;
			}
			case "brick": {
				offer.setBrick(-1);
				break;
			}
			case "sheep": {
				offer.setSheep(-1);
				break;
			}
			case "wheat": {
				offer.setWheat(-1);
				break;
			}
			case "ore": {
				offer.setOre(-1);
				break;
			}
		}
	}

}
