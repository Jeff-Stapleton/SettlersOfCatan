package client.controller.domestic;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import shared.Player;
import shared.ResourceList;
import shared.TradeOffer;
import shared.definitions.*;
import client.CatanGame;
import client.view.base.*;
import client.view.domestic.IAcceptTradeOverlay;
import client.view.domestic.IDomesticTradeOverlay;
import client.view.domestic.IDomesticTradeView;
import client.view.misc.*;


/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, Observer {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private CatanGame catanGame;
	private ResourceList trade;
	private int instigator;
	private int investigator;
	private Player players[];
	private ResourceType resourcesToGet[];
	private ResourceType resourcesToGive[];

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay, CatanGame catanGame) {

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		catanGame.addObserver(this);
		
		this.catanGame = catanGame;
		resourcesToGet = new ResourceType[4];
		resourcesToGive = new ResourceType[4];
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {
		players = catanGame.getModel().getPlayers();
		instigator = catanGame.getPlayerInfo().getPlayerIndex();
		
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		if (resource.equals(ResourceType.WOOD))
		{
			trade.setWood(trade.getWood() - 1);
		}
		else if (resource.equals(ResourceType.BRICK))
		{
			trade.setBrick(trade.getBrick() - 1);
		}
		else if (resource.equals(ResourceType.SHEEP))
		{
			trade.setSheep(trade.getSheep() - 1);
		}
		else if (resource.equals(ResourceType.WHEAT))
		{
			trade.setWheat(trade.getWheat() - 1);
		}
		else
		{
			trade.setOre(trade.getOre() - 1);
		}
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		if (resource.equals(ResourceType.WOOD))
		{
			trade.setWood(trade.getWood() + 1);
		}
		else if (resource.equals(ResourceType.BRICK))
		{
			trade.setBrick(trade.getBrick() + 1);
		}
		else if (resource.equals(ResourceType.SHEEP))
		{
			trade.setSheep(trade.getSheep() + 1);
		}
		else if (resource.equals(ResourceType.WHEAT))
		{
			trade.setWheat(trade.getWheat() + 1);
		}
		else
		{
			trade.setOre(trade.getOre() + 1);
		}
	}

	@Override
	public void sendTradeOffer() {
		getTradeOverlay().closeModal();
		getWaitOverlay().showModal();
		
		TradeOffer offer = new TradeOffer(instigator, trade, investigator);
		try {
			catanGame.setModel(catanGame.getProxy().movesOfferTrade(offer));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		investigator = playerIndex;
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		if (resource.equals(ResourceType.WOOD))
		{
			resourcesToGet[0] = ResourceType.WOOD;
		}
		else if (resource.equals(ResourceType.BRICK))
		{
			resourcesToGet[1] = ResourceType.BRICK;
		}
		else if (resource.equals(ResourceType.SHEEP))
		{
			resourcesToGet[2] = ResourceType.SHEEP;
		}
		else if (resource.equals(ResourceType.WHEAT))
		{
			resourcesToGet[3] = ResourceType.WHEAT;
		}
		else
		{
			resourcesToGet[4] = ResourceType.ORE;
		}
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		if (resource.equals(ResourceType.WOOD))
		{
			resourcesToGive[0] = ResourceType.WOOD;
		}
		else if (resource.equals(ResourceType.BRICK))
		{
			resourcesToGive[1] = ResourceType.BRICK;
		}
		else if (resource.equals(ResourceType.SHEEP))
		{
			resourcesToGive[2] = ResourceType.SHEEP;
		}
		else if (resource.equals(ResourceType.WHEAT))
		{
			resourcesToGive[3] = ResourceType.WHEAT;
		}
		else
		{
			resourcesToGive[4] = ResourceType.ORE;
		}
	}

	@Override
	public void unsetResource(ResourceType resource) {
		int none = 0;
		if (resource.equals(ResourceType.WOOD))
		{
			trade.setWood(none);
		}
		else if (resource.equals(ResourceType.BRICK))
		{
			trade.setBrick(none);
		}
		else if (resource.equals(ResourceType.SHEEP))
		{
			trade.setSheep(none);
		}
		else if (resource.equals(ResourceType.WHEAT))
		{
			trade.setWheat(none);
		}
		else
		{
			trade.setOre(none);
		}
	}

	@Override
	public void cancelTrade() {
		trade.clear();
		investigator = -1;
		resourcesToGet = new ResourceType[4];
		resourcesToGive = new ResourceType[4];
		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		if (willAccept == true)
		{
			ResourceList.moveResources(players[instigator].getResources(), players[investigator].getResources(), trade);
		}
		else
		{
			System.out.println("screw you!");
		}
		
		getAcceptOverlay().closeModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof CatanGame) {
			catanGame = (CatanGame) o;
			
		}		
	}

}

