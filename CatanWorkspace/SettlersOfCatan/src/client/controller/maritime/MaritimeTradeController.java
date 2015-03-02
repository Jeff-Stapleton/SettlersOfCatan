package client.controller.maritime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

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
	
	private int woodRatio;
	private int brickRatio;
	private int sheepRatio;
	private int wheatRatio;
	private int oreRatio;
	private int generalRatio;
	private int currentRatio;
	private ResourceType getResource;
	private ResourceType giveResource;
	private ResourceType[] enabledResources;
	private ResourceType[] allResources = {ResourceType.WOOD, ResourceType.BRICK, ResourceType.SHEEP, 
			ResourceType.WHEAT, ResourceType.ORE};
	
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
				if (CanCan.canMaritimeTrade(player, turn, list, ResourceType.ORE, bank, map.getPorts(), map))
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
		Player player = players[id];
		ResourceList bank = catanGame.getModel().getBank();
		TurnTracker turn = catanGame.getModel().getTurnTracker();
		Map map = catanGame.getModel().getMap();
		ResourceList resources = players[id].getResources();
		
		woodRatio = 4;
		brickRatio = 4;
		sheepRatio = 4;
		wheatRatio = 4;
		oreRatio = 4;
		generalRatio = 4;
		currentRatio = -1;
		if(id != turn.getCurrentTurn()){
			getTradeOverlay().setStateMessage("Not your turn.");
			getTradeOverlay().setTradeEnabled(false);
			getTradeOverlay().hideGetOptions();
		}
		else
		{
			enabledResources = getEnabledResources(resources, players[id], bank, map, turn);
			
			List<ResourceType> tempResourceList = new ArrayList<ResourceType>();
			if (player.getResources().getWood() >= woodRatio && (generalRatio == 4 || woodRatio == 2)) {
				tempResourceList.add(ResourceType.WOOD);
			} else if (player.getResources().getWood() >= generalRatio) {
				woodRatio = generalRatio;
				tempResourceList.add(ResourceType.WOOD);
			}
			if (player.getResources().getBrick() >= brickRatio && (generalRatio == 4 || brickRatio == 2)) {
				tempResourceList.add(ResourceType.BRICK);
			} else if (player.getResources().getBrick() >= generalRatio) {
				brickRatio = generalRatio;
				tempResourceList.add(ResourceType.BRICK);
			}
			if (player.getResources().getSheep() >= sheepRatio && (generalRatio == 4 || sheepRatio == 2)) {
				tempResourceList.add(ResourceType.SHEEP);
			} else if (player.getResources().getSheep() >= generalRatio) {
				sheepRatio = generalRatio;
				tempResourceList.add(ResourceType.SHEEP);
			}
			if (player.getResources().getWheat() >= wheatRatio && (generalRatio == 4 || wheatRatio == 2)) {
				tempResourceList.add(ResourceType.WHEAT);
			} else if (player.getResources().getWheat() >= generalRatio) {
				wheatRatio = generalRatio;
				tempResourceList.add(ResourceType.WHEAT);
			}
			if (player.getResources().getOre() >= oreRatio && (generalRatio == 4 || oreRatio == 2)) {
				tempResourceList.add(ResourceType.ORE);
			} else if (player.getResources().getOre() >= generalRatio) {
				oreRatio = generalRatio;
				tempResourceList.add(ResourceType.ORE);
			}
			
			//Build array of resources to enable based on ratios and player's resources
			enabledResources = new ResourceType[tempResourceList.size()];
			enabledResources = tempResourceList.toArray(enabledResources);
			getTradeOverlay().showGiveOptions(enabledResources);
			
		}
		
		getTradeOverlay().showModal();
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
		
		try {
			catanGame.updateModel(catanGame.getProxy().movesMaritimeTrade(id, 3, giveResourceType, getResourceType));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {
		//unsetGetValue();
		//unsetGiveValue(); 
		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		getResource = resource;
		getTradeOverlay().selectGetOption(resource, 1);
		getTradeOverlay().setTradeEnabled(true);//Now they can trade
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		ResourceType[] enabledResources = new ResourceType[5];
		int id = catanGame.getPlayerInfo().getPlayerIndex();
		Player[] players = catanGame.getModel().getPlayers();
		Player player = players[id];
		ResourceList bank = catanGame.getModel().getBank();
		TurnTracker turn = catanGame.getModel().getTurnTracker();
		Map map = catanGame.getModel().getMap();
		ResourceList resources = players[id].getResources();
		giveResource = resource;
		//Grab resource ratio
		switch(resource){
			case WOOD:	currentRatio = CanCan.bestRatio(player, map.getPorts(), player.getResources().getWood(), map, PortType.WOOD);
						break;
			case BRICK: currentRatio = CanCan.bestRatio(player, map.getPorts(), player.getResources().getBrick(), map, PortType.BRICK);
						break;
			case SHEEP:	currentRatio = CanCan.bestRatio(player, map.getPorts(), player.getResources().getSheep(), map, PortType.SHEEP);
						break;
			case WHEAT:	currentRatio = CanCan.bestRatio(player, map.getPorts(), player.getResources().getWheat(), map, PortType.WHEAT);
						break;
			case ORE:	currentRatio = CanCan.bestRatio(player, map.getPorts(), player.getResources().getOre(), map, PortType.ORE);
						break;
			default:	currentRatio = -1;
						break;
		}
		
		getTradeOverlay().selectGiveOption(resource, currentRatio);
		getTradeOverlay().showGetOptions(allResources);
	}

	@Override
	public void unsetGetValue() {
		//getTradeOverlay().hideGetOptions();
		//getTradeOverlay().hideGiveOptions();
		//getTradeOverlay().showGiveOptions(getEnabledResources(catanGame.getModel().getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].getResources(), catanGame.getModel().getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()], catanGame.getModel().getBank(), catanGame.getModel().getMap(), catanGame.getModel().getTurnTracker()));
		getTradeOverlay().showGetOptions(allResources);
		getTradeOverlay().setTradeEnabled(false);
	}

	@Override
	public void unsetGiveValue() {
		getTradeOverlay().showGiveOptions(getEnabledResources(catanGame.getModel().getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].getResources(), catanGame.getModel().getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()], catanGame.getModel().getBank(), catanGame.getModel().getMap(), catanGame.getModel().getTurnTracker()));
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
		//does this ever need to update from model?
	}

}

