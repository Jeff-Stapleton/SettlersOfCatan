package client.controller.maritime;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import shared.CanCan;
import shared.Map;
import shared.Player;
import shared.ResourceList;
import shared.TradeOffer;
import shared.definitions.ResourceType;
import shared.TurnTracker;
import shared.definitions.*;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import client.CatanGame;
import client.view.base.*;
import client.view.maritime.IMaritimeTradeOverlay;
import client.view.maritime.IMaritimeTradeView;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, Observer {

	private IMaritimeTradeOverlay tradeOverlay;
	private CatanGame catanGame;
	private ResourceType giveResourceType;
	private ResourceType getResourceType;
	private int giveResourceAmount;
	private int getResourceAmount;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay, CatanGame catanGame) {
		
		super(tradeView);
		catanGame.addObserver(this);
		this.catanGame = catanGame;
		setTradeOverlay(tradeOverlay);
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	@Override
	public void startTrade() {
		
		getTradeOverlay().showModal();
		handleGiveOptions();
		//handleGetOptions();
	}
	
	public void handleGiveOptions()
	{
		ResourceType[] enabledResources = new ResourceType[5];
		int id = catanGame.getPlayerInfo().getPlayerIndex();
		Player[] players = catanGame.getModel().getPlayers();
		ResourceList bank = catanGame.getModel().getBank();
		TurnTracker turn = catanGame.getModel().getTurnTracker();
		Map map = catanGame.getModel().getMap();
		ResourceList resources = players[id].getResources();
		
		for (int i = 0; i < enabledResources.length; i++)
		{
			if (i == 0) //wood
			{
				ResourceList list = new ResourceList();
				list.setWood(resources.getWood());	
				if (CanCan.canMaritimeTrade(players[id], turn, list, bank, map.getPorts(), map))
				{
					enabledResources[0] = ResourceType.WOOD;
				}

			}
			else if (i == 1) //brick
			{
				ResourceList list = new ResourceList();
				list.setBrick(resources.getBrick());	
				if (CanCan.canMaritimeTrade(players[id], turn, list, bank, map.getPorts(), map))
				{
					enabledResources[1] = ResourceType.BRICK;
				}
			}
			else if (i == 2) //wool
			{
				ResourceList list = new ResourceList();
				list.setSheep(resources.getSheep());
				if (CanCan.canMaritimeTrade(players[id], turn, list, bank, map.getPorts(), map))
				{
					enabledResources[2] = ResourceType.SHEEP;
				}
			}
			else if (i == 3) //wheat
			{
				ResourceList list = new ResourceList();
				list.setWheat(resources.getWheat());
				if (CanCan.canMaritimeTrade(players[id], turn, list, bank, map.getPorts(), map))
				{
					enabledResources[3] = ResourceType.WHEAT;
				}
			}
			else //ore
			{
				ResourceList list = new ResourceList();
				list.setOre(resources.getOre());
				if (CanCan.canMaritimeTrade(players[id], turn, list, bank, map.getPorts(), map))
				{
					enabledResources[4] = ResourceType.ORE;
				}
			}
			
		}
		getTradeOverlay().showGiveOptions(enabledResources);
	}
	
	public void handleGetOptions()
	{
		ResourceType[] enabledResources = new ResourceType[5];
		enabledResources[0] = ResourceType.WOOD;
		enabledResources[1] = ResourceType.BRICK;
		enabledResources[2] = ResourceType.SHEEP;
		enabledResources[3] = ResourceType.WHEAT;
		enabledResources[4] = ResourceType.ORE;
		getTradeOverlay().showGiveOptions(enabledResources);
	}

	@Override
	public void makeTrade() {
		int id = catanGame.getPlayerInfo().getPlayerIndex();
				
		ResourceList trade = new ResourceList();
		ResourcesToGet(trade);

		getTradeOverlay().closeModal();
		
		try {
			catanGame.updateModel(catanGame.getProxy().movesMaritimeTrade(id, 3, giveResourceType, getResourceType));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ResourceList ResourcesToGet(ResourceList trade)
	{
		if (getResourceType.equals(ResourceType.WOOD))
		{
			trade.setWood(getResourceAmount);
			trade = ResourcesToGive(trade);
		}
		else if (getResourceType.equals(ResourceType.BRICK))
		{
			trade.setBrick(getResourceAmount);
			trade = ResourcesToGive(trade);
		}
		else if (getResourceType.equals(ResourceType.SHEEP))
		{
			trade.setSheep(getResourceAmount);
			trade = ResourcesToGive(trade);
		}
		else if (getResourceType.equals(ResourceType.WHEAT))
		{
			trade.setWheat(getResourceAmount);
			trade = ResourcesToGive(trade);
		}
		else
		{
			trade.setOre(getResourceAmount);
			trade = ResourcesToGive(trade);
		}
		return trade;
	}
	
	public ResourceList ResourcesToGive(ResourceList trade)
	{
		if (giveResourceType.equals(ResourceType.WOOD))
		{
			trade.setWood(-1 * getResourceAmount);
		}
		else if (giveResourceType.equals(ResourceType.BRICK))
		{
			trade.setBrick(-1 * getResourceAmount);
		}
		else if (giveResourceType.equals(ResourceType.SHEEP))
		{
			trade.setSheep(-1 * getResourceAmount);
		}
		else if (giveResourceType.equals(ResourceType.WHEAT))
		{
			trade.setWheat(-1 * getResourceAmount);
		}
		else
		{
			trade.setOre(-1 * getResourceAmount);
		}
		return trade;
	}

	@Override
	public void cancelTrade() {
		unsetGetValue();
		unsetGiveValue(); 
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		getResourceType = resource;
		//need to determine the best ratio from the port on which the player currently is built
		getResourceAmount = 3;
		getTradeOverlay().selectGetOption(getResourceType, getResourceAmount);
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		giveResourceType = resource;
		//need to determine the best ratio from the port on which the player currently is built
		giveResourceAmount = 3;
		getTradeOverlay().selectGiveOption(getResourceType, giveResourceAmount);
	}

	@Override
	public void unsetGetValue() {
		getResourceType = null;
	}

	@Override
	public void unsetGiveValue() {
		giveResourceType = null;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof CatanGame) {
			catanGame = (CatanGame) o;
			
		}
	}
	
	protected void updateFromModel()
	{
		//does this ever need to update from model?
	}

}

