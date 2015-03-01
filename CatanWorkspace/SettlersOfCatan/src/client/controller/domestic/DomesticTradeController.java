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
	private Boolean sendingWood;
	private Boolean sendingBrick;
	private Boolean sendingSheep;
	private Boolean sendingWheat;
	private Boolean sendingOre;

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
		trade = new ResourceList();
		resourcesToGet = new ResourceType[5];
		resourcesToGive = new ResourceType[5];
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
	
	public void handleEnablingResources(ResourceType resource)
	{
		switch (resource)
		{
			case WOOD:
			{
				if (sendingWood.equals(true))
				{
					if (trade.getWood() < players[instigator].getResources().getWood())
					{
						if (trade.getWood() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, true, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
							break;
						}
					}
					else
					{
						if (trade.getWood() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, false, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, false, false);
							break;
						}
					}
				}
				else
				{
					if (trade.getWood() > 0)
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, true, true);
						break;
					}
					else
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
						break;
					}
				}
			}
			case BRICK:
			{
				if (sendingBrick.equals(true))
				{
					if (trade.getBrick() < players[instigator].getResources().getBrick())
					{
						if (trade.getBrick() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, true, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, true, false);
							break;
						}
					}
					else
					{
						if (trade.getBrick() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, false, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, false, false);
							break;
						}
					}
				}
				else
				{
					if (trade.getBrick() > 0)
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, true, true);
						break;
					}
					else
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.BRICK, true, false);
						break;
					}
				}
			}
			case SHEEP:
			{
				if (sendingSheep.equals(true))
				{
					if (trade.getSheep() < players[instigator].getResources().getSheep())
					{
						if (trade.getSheep() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
							break;
						}
					}
					else
					{
						if (trade.getSheep() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, false);
							break;
						}
					}
				}
				else
				{
					if (trade.getSheep() > 0)
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, true);
						break;
					}
					else
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
						break;
					}
				}
			}
			case WHEAT:
			{
				if (sendingWheat.equals(true))
				{
					if (trade.getWheat() < players[instigator].getResources().getWheat())
					{
						if (trade.getWheat() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
							break;
						}
					}
					else
					{
						if (trade.getWheat() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, false);
							break;
						}
					}
				}
				else
				{
					if (trade.getWheat() > 0)
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, true);
						break;
					}
					else
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
						break;
					}
				}
			}
			case ORE:
			{
				if (sendingOre.equals(true))
				{
					if (trade.getOre() < players[instigator].getResources().getOre())
					{
						if (trade.getOre() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, true, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, true, false);
							break;
						}
					}
					else
					{
						if (trade.getOre() > 0)
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, false, true);
							break;
						}
						else
						{
							getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, false, false);
							break;
						}
					}
				}
				else
				{
					if (trade.getOre() > 0)
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, true, true);
						break;
					}
					else
					{
						getTradeOverlay().setResourceAmountChangeEnabled(ResourceType.ORE, true, false);
						break;
					}
				}
			}
		}
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				trade.setWood(trade.getWood() - 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case BRICK:
			{
				trade.setBrick(trade.getBrick() - 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case SHEEP:
			{
				trade.setSheep(trade.getSheep() - 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case WHEAT:
			{
				trade.setWheat(trade.getWheat() - 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case ORE:
			{
				trade.setOre(trade.getOre() - 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
		}
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
			switch(resource) {
			case WOOD:
			{
				trade.setWood(trade.getWood() + 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case BRICK:
			{
				trade.setBrick(trade.getBrick() + 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case SHEEP:
			{
				trade.setSheep(trade.getSheep() + 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case WHEAT:
			{
				trade.setWheat(trade.getWheat() + 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case ORE:
			{
				trade.setOre(trade.getOre() + 1);
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
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
		switch(resource) {
			case WOOD:
			{
				sendingWood = false;
				resourcesToGet[0] = ResourceType.WOOD;
				handleEnablingResources(resource);
				break;
			}
			case BRICK:
			{
				sendingBrick = false;
				resourcesToGet[1] = ResourceType.BRICK;
				handleEnablingResources(resource);
				break;
			}
			case SHEEP:
			{
				sendingSheep = false;
				resourcesToGet[2] = ResourceType.SHEEP;
				handleEnablingResources(resource);
				break;
			}
			case WHEAT:
			{
				sendingWheat = false;
				resourcesToGet[3] = ResourceType.WHEAT;
				handleEnablingResources(resource);
				break;
			}
			case ORE:
			{
				sendingOre = false;
				resourcesToGet[4] = ResourceType.ORE;
				handleEnablingResources(resource);
				break;
			}
		}
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				sendingWood = true;
				resourcesToGive[0] = ResourceType.WOOD;
				handleEnablingResources(resource);
				break;
			}
			case BRICK:
			{
				sendingBrick = true;
				resourcesToGive[1] = ResourceType.BRICK;
				handleEnablingResources(resource);
				break;
			}
			case SHEEP:
			{
				sendingSheep = true;
				resourcesToGive[2] = ResourceType.SHEEP;
				handleEnablingResources(resource);
				break;
			}
			case WHEAT:
			{
				sendingWheat = true;
				resourcesToGive[3] = ResourceType.WHEAT;
				handleEnablingResources(resource);
				break;
			}
			case ORE:
			{
				sendingOre = true;
				resourcesToGive[4] = ResourceType.ORE;
				handleEnablingResources(resource);
				break;
			}
		}
	}

	@Override
	public void unsetResource(ResourceType resource) {
		int none = 0;
		switch(resource) {
			case WOOD:
			{
				sendingWood = null;
				trade.setWood(none);
				break;
			}
			case BRICK:
			{
				sendingBrick = null;
				trade.setBrick(none);
				break;
			}
			case SHEEP:
			{
				sendingSheep = null;
				trade.setSheep(none);
				break;
			}
			case WHEAT:
			{
				sendingWheat = null;
				trade.setWheat(none);
				break;
			}
			case ORE:
			{
				sendingOre = null;
				trade.setOre(none);
				break;
			}
		}
	}

	@Override
	public void cancelTrade() {
		trade.clear();
		investigator = -1;
		resourcesToGet = new ResourceType[5];
		resourcesToGive = new ResourceType[5];
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

	public void isValidTrade()
	{
		boolean validGet = false;
		boolean validGive = false;
		for (int i = 0; i < resourcesToGet.length; i++)
		{
			if (resourcesToGet[i] != null)
			{
				validGet = true;
			}
		}
		for (int i = 0; i < resourcesToGive.length; i++)
		{
			if (resourcesToGet[i] != null)
			{
				validGive = true;
			}
		}
		
		if (validGet == true && validGive == true)
		{
			getTradeOverlay().setTradeEnabled(true);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof CatanGame) {
			catanGame = (CatanGame) o;
			
		}		
	}

}

