package client.controller.maritime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import shared.CanCan;
import shared.CatanModel;
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
import client.controller.domestic.DomesticTradeController;
import client.view.base.*;
import client.view.maritime.IMaritimeTradeOverlay;
import client.view.maritime.IMaritimeTradeView;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, Observer {

	private static final Logger log = Logger.getLogger(MaritimeTradeController.class.getName());
	
	private IMaritimeTradeOverlay tradeOverlay;
	private CatanGame catanGame;
	private int giveResourceAmount;
	private int getResourceAmount;
	
	private ResourceType getResource;
	private ResourceType giveResource;
	private ResourceType[] enabledResources;
	private ResourceType[] allResources = {ResourceType.WOOD, ResourceType.BRICK, ResourceType.SHEEP, 
			ResourceType.WHEAT, ResourceType.ORE};
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay, CatanGame catanGame) {
		
		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		catanGame.addObserver(this);
		
		this.catanGame = catanGame;
		
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
	public void startTrade()
	{
		log.trace("Showing maritime trade modal --\\");
		getTradeOverlay().showModal();
		handleGiveOptions();
	}
	
	public ResourceType[] getEnabledResources(ResourceList resources, Player player, ResourceList bank, Map map, TurnTracker turn)
	{
		ResourceType[] enabledResources = new ResourceType[5];
		
		for (int i = 0; i < enabledResources.length; i++)
		{
			if (i == 0) //wood
			{
				ResourceList list = new ResourceList();
				list.setWood(resources.getWood());	
				if (CanCan.canMaritimeTrade(player, turn, list, ResourceType.WOOD, bank, map.getPorts(), map))
				{
					enabledResources[0] = ResourceType.WOOD;
				}

			}
			else if (i == 1) //brick
			{
				ResourceList list = new ResourceList();
				list.setBrick(resources.getBrick());	
				if (CanCan.canMaritimeTrade(player, turn, list, ResourceType.BRICK, bank, map.getPorts(), map))
				{
					enabledResources[1] = ResourceType.BRICK;
				}
			}
			else if (i == 2) //wool
			{
				ResourceList list = new ResourceList();
				list.setSheep(resources.getSheep());
				if (CanCan.canMaritimeTrade(player, turn, list, ResourceType.SHEEP, bank, map.getPorts(), map))
				{
					enabledResources[2] = ResourceType.SHEEP;
				}
			}
			else if (i == 3) //wheat
			{
				ResourceList list = new ResourceList();
				list.setWheat(resources.getWheat());
				if (CanCan.canMaritimeTrade(player, turn, list, ResourceType.WHEAT, bank, map.getPorts(), map))
				{
					enabledResources[3] = ResourceType.WHEAT;
				}
			}
			else //ore
			{
				ResourceList list = new ResourceList();
				list.setOre(resources.getOre());
				if (CanCan.canMaritimeTrade(player, turn, list, ResourceType.ORE, bank, map.getPorts(), map))
				{
					enabledResources[4] = ResourceType.ORE;
				}
			}
		}
		return enabledResources;
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
		
		if(id != turn.getCurrentTurn()){
			getTradeOverlay().setStateMessage("Not your turn.");
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().hideGetOptions();
		}
		else
		{
			enabledResources = getEnabledResources(resources, players[id], bank, map, turn);
			getTradeOverlay().showGiveOptions(enabledResources);
		}
//		log.trace("Showing maritime trade modal --\\");
		//getTradeOverlay().showModal();
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
	public void makeTrade() 
	{
		int id = catanGame.getPlayerInfo().getPlayerIndex();
		getTradeOverlay().closeModal();
		log.trace("Closed maritime trade modal --/");
		
		try {
			catanGame.updateModel(catanGame.getProxy().movesMaritimeTrade(id, giveResourceAmount, giveResource, getResource));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		unsetGetValue();
		unsetGiveValue();
		getTradeOverlay().closeModal();
		log.trace("Closed maritime trade modal --/");
	}

	@Override
	public void cancelTrade() {
		unsetGetValue();
		unsetGiveValue();
		getTradeOverlay().closeModal();
		log.trace("Closed maritime trade modal --/");
	}

	@Override
	public void setGetResource(ResourceType resource) {
		getResource = resource;
		getTradeOverlay().selectGetOption(getResource, 1);
		getTradeOverlay().setTradeEnabled(true);
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		
		int id = catanGame.getPlayerInfo().getPlayerIndex();
		Player[] players = catanGame.getModel().getPlayers();
		Player player = players[id];
		Map map = catanGame.getModel().getMap();
		giveResource = resource;

		switch(giveResource){
			case WOOD:	
				giveResourceAmount = CanCan.bestRatio(player, map.getPorts(), player.getResources().getWood(), map, PortType.WOOD);
				break;
			case BRICK: 
				giveResourceAmount = CanCan.bestRatio(player, map.getPorts(), player.getResources().getBrick(), map, PortType.BRICK);
				break;
			case SHEEP:	
				giveResourceAmount = CanCan.bestRatio(player, map.getPorts(), player.getResources().getSheep(), map, PortType.SHEEP);
				break;
			case WHEAT:	
				giveResourceAmount = CanCan.bestRatio(player, map.getPorts(), player.getResources().getWheat(), map, PortType.WHEAT);
				break;
			case ORE:	
				giveResourceAmount = CanCan.bestRatio(player, map.getPorts(), player.getResources().getOre(), map, PortType.ORE);
				break;
			default:	
				giveResourceAmount = 4;
				break;
		}
		
		getTradeOverlay().selectGiveOption(giveResource, giveResourceAmount);
		getTradeOverlay().showGetOptions(allResources);
	}

	@Override
	public void unsetGetValue() {
		//getTradeOverlay().showGetOptions(allResources);
		getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void unsetGiveValue() {
		//getTradeOverlay().showGiveOptions(getEnabledResources(catanGame.getModel().getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].getResources(), catanGame.getModel().getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()], catanGame.getModel().getBank(), catanGame.getModel().getMap(), catanGame.getModel().getTurnTracker()));
		getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof CatanGame) {
			catanGame = (CatanGame) o;
		}
	}
	
	protected void updateFromModel()
	{

	}

}

