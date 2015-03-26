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
	
	private Player player;
	private ResourceType give;
	private ResourceType get;
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
		
		giveToBank(request.getOutputResource(), request.getRatio());
		giveToPlayer(request.getInputResource());
		
		return catanModel;
	}

	public void initialize(CatanModel catanModel) {
		player = getPlayer(catanModel);
		bank = catanModel.getBank();
		inventory = player.getResources();
	}

	public Player getPlayer(CatanModel catanModel) {
		int index = request.getPlayerIndex();
		return catanModel.getPlayers()[index];
	}

	public void giveToBank(String giveResource, int ratio) {
		log.trace(giveResource);
		switch (giveResource) {
			case "wood": {
				inventory.setWood(bank.getWood() - request.getRatio());
				bank.setWood(bank.getWood() + request.getRatio());
				break;
			}
			case "brick": {
				inventory.setBrick(bank.getBrick() - request.getRatio());
				bank.setBrick(bank.getBrick() + request.getRatio());
				break;
			}
			case "sheep": {
				inventory.setSheep(bank.getSheep() - request.getRatio());
				bank.setSheep(bank.getSheep() + request.getRatio());
				break;
			}
			case "wheat": {
				inventory.setWheat(bank.getWheat() - request.getRatio());
				bank.setWheat(bank.getWheat() + request.getRatio());
				break;
			}
			case "ore": {
				inventory.setOre(bank.getOre() - request.getRatio());
				bank.setOre(bank.getOre() + request.getRatio());
				break;
			}
		}
	}

	public void giveToPlayer(String getResource) {
		log.trace(getResource);
		switch (getResource) {
			case "wood": {
				inventory.setWood(bank.getWood() + 1 );
				bank.setWood(bank.getWood() - 1);
				break;
			}
			case "brick": {
				inventory.setBrick(bank.getBrick() + 1);
				bank.setBrick(bank.getBrick() - 1);
				break;
			}
			case "sheep": {
				inventory.setSheep(bank.getSheep() + 1);
				bank.setSheep(bank.getSheep() - 1);
				break;
			}
			case "wheat": {
				inventory.setWheat(bank.getWheat() + 1);
				bank.setWheat(bank.getWheat() - 1);
				break;
			}
			case "ore": {
				inventory.setOre(bank.getOre() + 1);
				bank.setOre(bank.getOre() - 1);
				break;
			}
		}
	}

}
