package client.controller.domestic;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

import shared.CanCan;
import shared.Player;
import shared.ResourceList;
import shared.TradeOffer;
import shared.definitions.*;
import client.CatanGame;
import client.view.base.*;
import client.view.data.PlayerInfo;
import client.view.domestic.IAcceptTradeOverlay;
import client.view.domestic.IDomesticTradeOverlay;
import client.view.domestic.IDomesticTradeView;
import client.view.misc.*;


/**
 * Domestic tradeSend controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, Observer
{

	private static final Logger log = Logger.getLogger(DomesticTradeController.class.getName());
	
	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private CatanGame catanGame;
	private ResourceList tradeSend;
	private ResourceList tradeRecieve;
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
	 * @param tradeView Domestic tradeSend view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic tradeSend overlay (i.e., view that lets the user propose a domestic tradeSend)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a tradeSend
	 * @param acceptOverlay Accept tradeSend overlay which lets the user accept or reject a proposed tradeSend
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay, CatanGame catanGame)
	{

		super(tradeView);
		
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
		catanGame.addObserver(this);
		
		this.catanGame = catanGame;
		tradeSend = new ResourceList();
		tradeRecieve = new ResourceList();
		investigator = -1;
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
		getTradeOverlay().reset();
		resourcesToGet = new ResourceType[5];
		resourcesToGive = new ResourceType[5];
		tradeSend.clear();
		tradeRecieve.clear();
		investigator = -1;
		
		getTradeOverlay().setTradeEnabled(false);
		players = catanGame.getModel().getPlayers();
		instigator = catanGame.getPlayerInfo().getPlayerIndex();
		
		int offSetIndex = 0;
		PlayerInfo playerInfo[] = new PlayerInfo [3];
		for (int i = 0; i <= playerInfo.length; i++)
		{
			if (players[i].getPlayerIndex() != instigator)
			{
				PlayerInfo player = new PlayerInfo();
				player.setId(players[i].getPlayerID());
				player.setPlayerIndex(players[i].getPlayerIndex());
				player.setName(players[i].getName());
				player.setColor(players[i].getColor());
				playerInfo[i-offSetIndex] = player;
			}
			else
			{
				offSetIndex++;
			}
		}
		
		log.trace("Showing domestic trade modal --\\");
		getTradeOverlay().showModal();	
		getTradeOverlay().setPlayerSelectionEnabled(true);
		getTradeOverlay().setPlayers(playerInfo);
	}
	
	public void handleEnablingResources(ResourceType resource)
	{
		switch (resource)
		{
			case WOOD:
			{
				if (sendingWood.equals(true))
				{
					if (tradeSend.getWood() < players[instigator].getResources().getWood())
					{
						if (tradeSend.getWood() > 0)
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
						if (tradeSend.getWood() > 0)
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
					if (tradeRecieve.getWood() > 0)
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
					if (tradeSend.getBrick() < players[instigator].getResources().getBrick())
					{
						if (tradeSend.getBrick() > 0)
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
						if (tradeSend.getBrick() > 0)
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
					if (tradeRecieve.getBrick() > 0)
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
					if (tradeSend.getSheep() < players[instigator].getResources().getSheep())
					{
						if (tradeSend.getSheep() > 0)
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
						if (tradeSend.getSheep() > 0)
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
					if (tradeRecieve.getSheep() > 0)
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
					if (tradeSend.getWheat() < players[instigator].getResources().getWheat())
					{
						if (tradeSend.getWheat() > 0)
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
						if (tradeSend.getWheat() > 0)
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
					if (tradeRecieve.getWheat() > 0)
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
					if (tradeSend.getOre() < players[instigator].getResources().getOre())
					{
						if (tradeSend.getOre() > 0)
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
						if (tradeSend.getOre() > 0)
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
					if (tradeRecieve.getOre() > 0)
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
				if (sendingWood == true)
				{
					tradeSend.setWood(tradeSend.getWood() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setWood(tradeRecieve.getWood() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
			case BRICK:
			{
				if (sendingBrick == true)
				{
					tradeSend.setBrick(tradeSend.getBrick() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setBrick(tradeRecieve.getBrick() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
			case SHEEP:
			{
				if (sendingSheep == true)
				{
					tradeSend.setSheep(tradeSend.getSheep() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setSheep(tradeRecieve.getSheep() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
			case WHEAT:
			{
				if (sendingWheat == true)
				{
					tradeSend.setWheat(tradeSend.getWheat() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setWheat(tradeRecieve.getWheat() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
			case ORE:
			{
				if (sendingOre == true)
				{
					tradeSend.setWood(tradeSend.getOre() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setOre(tradeRecieve.getOre() - 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
		}
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				if (sendingWood == true)
				{
					tradeSend.setWood(tradeSend.getWood() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setWood(tradeRecieve.getWood() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
			case BRICK:
			{
				if (sendingBrick == true)
				{
					tradeSend.setBrick(tradeSend.getBrick() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setBrick(tradeRecieve.getBrick() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
			case SHEEP:
			{
				if (sendingSheep == true)
				{
					tradeSend.setSheep(tradeSend.getSheep() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setSheep(tradeRecieve.getSheep() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
			case WHEAT:
			{
				if (sendingWheat == true)
				{
					tradeSend.setWheat(tradeSend.getWheat() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setWheat(tradeRecieve.getWheat() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
			case ORE:
			{
				if (sendingOre == true)
				{
					tradeSend.setOre(tradeSend.getOre() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				else
				{
					tradeRecieve.setOre(tradeRecieve.getOre() + 1);
					handleEnablingResources(resource);
					isValidTrade();
				}
				break;
			}
		}
	}

	@Override
	public void sendTradeOffer() {
		getTradeOverlay().closeModal();
		log.trace("Closed domestic trade modal --/");
		log.trace("Showing wait overlay modal --\\");
		getWaitOverlay().showModal();
		
		ResourceList theTrade = new ResourceList();
		for (int i = 0; i < 5; i++)
		{
			if (i == 0)
			{
				if (tradeSend.getWood() > 0)
				{
					theTrade.setWood(tradeSend.getWood());
				}
				else
				{
					theTrade.setWood(tradeRecieve.getWood() * -1);
				}
			}
			else if (i == 1)
			{
				if (tradeSend.getBrick() > 0)
				{
					theTrade.setBrick(tradeSend.getBrick());
				}
				else
				{
					theTrade.setBrick(tradeRecieve.getBrick() * -1);
				}
			}
			else if (i == 2)
			{
				if (tradeSend.getSheep() > 0)
				{
					theTrade.setSheep(tradeSend.getSheep());
				}
				else
				{
					theTrade.setSheep(tradeRecieve.getSheep() * -1);
				}
			}
			else if (i == 3)
			{
				if (tradeSend.getWheat() > 0)
				{
					theTrade.setWheat(tradeSend.getWheat());
				}
				else
				{
					theTrade.setWheat(tradeRecieve.getWheat() * -1);
				}
			}
			else
			{
				if (tradeSend.getOre() > 0)
				{
					theTrade.setOre(tradeSend.getOre());
				}
				else
				{
					theTrade.setOre(tradeRecieve.getOre() * -1);
				}
			}
		}
		
		TradeOffer offer = new TradeOffer(instigator, theTrade, investigator);
		try {
			catanGame.updateModel(catanGame.getProxy().movesOfferTrade(offer));
			getTradeOverlay().reset();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		investigator = playerIndex;
		isValidTrade();
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setWood(0);
				tradeRecieve.setWood(0);
				sendingWood = false;
				resourcesToGive[0] = null;
				resourcesToGet[0] = ResourceType.WOOD;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case BRICK:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setBrick(0);
				tradeRecieve.setBrick(0);
				sendingBrick = false;
				resourcesToGive[1] = null;
				resourcesToGet[1] = ResourceType.BRICK;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case SHEEP:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setSheep(0);
				tradeRecieve.setSheep(0);
				sendingSheep = false;
				resourcesToGive[2] = null;
				resourcesToGet[2] = ResourceType.SHEEP;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case WHEAT:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setWheat(0);
				tradeRecieve.setWheat(0);
				sendingWheat = false;
				resourcesToGive[3] = null;
				resourcesToGet[3] = ResourceType.WHEAT;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case ORE:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setOre(0);
				tradeRecieve.setOre(0);
				sendingOre = false;
				resourcesToGive[4] = null;
				resourcesToGet[4] = ResourceType.ORE;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
		}
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setWood(0);
				sendingWood = true;
				resourcesToGet[0] = null;
				resourcesToGive[0] = ResourceType.WOOD;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case BRICK:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setBrick(0);
				sendingBrick = true;
				resourcesToGet[1] = null;
				resourcesToGive[1] = ResourceType.BRICK;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case SHEEP:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setSheep(0);
				sendingSheep = true;
				resourcesToGet[2] = null;
				resourcesToGive[2] = ResourceType.SHEEP;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case WHEAT:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setWheat(0);
				sendingWheat = true;
				resourcesToGet[3] = null;
				resourcesToGive[3] = ResourceType.WHEAT;
				handleEnablingResources(resource);
				isValidTrade();
				break;
			}
			case ORE:
			{
				getTradeOverlay().setResourceAmount(resource, "0");
				tradeSend.setOre(0);
				sendingOre = true;
				resourcesToGet[4] = null;
				resourcesToGive[4] = ResourceType.ORE;
				handleEnablingResources(resource);
				isValidTrade();
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
				tradeSend.setWood(none);
				resourcesToGet[0] = null;
				resourcesToGive[0] = null;
				isValidTrade();
				break;
			}
			case BRICK:
			{
				sendingBrick = null;
				tradeSend.setBrick(none);
				resourcesToGet[1] = null;
				resourcesToGive[1] = null;
				isValidTrade();
				break;
			}
			case SHEEP:
			{
				sendingSheep = null;
				tradeSend.setSheep(none);
				resourcesToGet[2] = null;
				resourcesToGive[2] = null;
				isValidTrade();
				break;
			}
			case WHEAT:
			{
				sendingWheat = null;
				tradeSend.setWheat(none);
				resourcesToGet[3] = null;
				resourcesToGive[3] = null;
				isValidTrade();
				break;
			}
			case ORE:
			{
				sendingOre = null;
				tradeSend.setOre(none);
				resourcesToGet[4] = null;
				resourcesToGive[4] = null;
				isValidTrade();
				break;
			}
		}
	}

	@Override
	public void cancelTrade() {
		tradeSend.clear();
		tradeRecieve.clear();
		investigator = -1;
		resourcesToGet = new ResourceType[5];
		resourcesToGive = new ResourceType[5];
		getTradeOverlay().reset();
		getTradeOverlay().closeModal();
		log.trace("Closed domestic trade modal --/");
	}

	@Override
	public void acceptTrade(boolean willAccept) {		
		players = catanGame.getModel().getPlayers();
		if (CanCan.canOfferTrade(players[catanGame.getModel().getTradeOffer().getSender()], players[catanGame.getModel().getTradeOffer().getReceiver()], catanGame.getModel().getTurnTracker(), catanGame.getModel().getTradeOffer().getOffer()))
		{
			try {
				catanGame.updateModel(catanGame.getProxy().movesAcceptTrade(catanGame.getModel().getTradeOffer().getSender(), willAccept));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			try {
				catanGame.updateModel(catanGame.getProxy().movesAcceptTrade(catanGame.getModel().getTradeOffer().getSender(), false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		getAcceptOverlay().reset();
		getAcceptOverlay().closeModal();
		log.trace("Closed accept trade modal --/");
	}

	public boolean isValidAmount(ResourceType resource, ResourceList checkList)
	{
		switch(resource)
		{
			case WOOD:
			{
				if (checkList.getWood() != 0)
				{
					return true;
				}
				return false;
			}
			case ORE:
			{
				if (checkList.getOre() != 0)
				{
					return true;
				}
				return false;
			}
			case SHEEP:
			{
				if (checkList.getSheep() != 0)
				{
					return true;
				}
				return false;
			}
			case WHEAT:
			{
				if (checkList.getWheat() != 0)
				{
					return true;
				}
				return false;
			}
			case BRICK:
			{
				if (checkList.getBrick() != 0)
				{
					return true;
				}
				return false;
			}
		}
		return false;
	}
	
	public void isValidTrade()
	{
		boolean validGet = false;
		boolean validGive = false;
		for (int i = 0; i < resourcesToGet.length; i++)
		{
			if (resourcesToGet[i] != null && isValidAmount(resourcesToGet[i], tradeRecieve))
			{
				validGet = true;
			}
		}
		for (int i = 0; i < resourcesToGive.length; i++)
		{
			if (resourcesToGive[i] != null && isValidAmount(resourcesToGive[i], tradeSend))
			{
				validGive = true;
			}
		}
		
		if (validGet == true && validGive == true)
		{
			String message = "Select a Player";
			getTradeOverlay().setStateMessage(message);
			if (investigator != -1)
			{
				message = "Trade";
				getTradeOverlay().setStateMessage(message);
				getTradeOverlay().setTradeEnabled(true);
				return;
			}
			getTradeOverlay().setTradeEnabled(false);
			return;
		}
		String message = "set the tradeSend you want to make";
		getTradeOverlay().setStateMessage(message);
		getTradeOverlay().setTradeEnabled(false);
		return;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof CatanGame) {
			
			catanGame = (CatanGame) o;
			TradeOffer offer = catanGame.getModel().getTradeOffer();
			
			if (catanGame.getPlayerInfo().getPlayerIndex() == catanGame.getModel().getTurnTracker().getCurrentTurn()){
				getTradeView().enableDomesticTrade(true);
			}
			else{
				getTradeView().enableDomesticTrade(false);
			}
			
			if(offer != null) 
			{
				if(offer.getReceiver() == catanGame.getPlayerInfo().getPlayerIndex() && !this.getAcceptOverlay().isModalShowing()) 
				{
					ResourceList resources = offer.getOffer();
					boolean canAccept = true;
					Player players[] = catanGame.getModel().getPlayers();
					Player receiver = players[catanGame.getPlayerInfo().getPlayerIndex()];
					
					int brick = resources.getBrick();	
					if(brick > 0)
					{
						this.getAcceptOverlay().addGetResource(ResourceType.BRICK, brick);
					}
					else if(brick < 0) 
					{
						brick = brick * -1;
						this.getAcceptOverlay().addGiveResource(ResourceType.BRICK, brick);
						canAccept = (receiver.getResources().getBrick() + brick > -1) && canAccept;
					}
					
					int sheep = resources.getSheep();
					if(sheep > 0) 
					{
						this.getAcceptOverlay().addGetResource(ResourceType.SHEEP, sheep);
					}
					else if(sheep < 0) 
					{
						sheep = sheep * -1;
						this.getAcceptOverlay().addGiveResource(ResourceType.SHEEP, sheep);
						canAccept = (receiver.getResources().getSheep() + sheep > -1) && canAccept;
					}
					
					int ore = resources.getOre();
					if(ore > 0) 
					{
						this.getAcceptOverlay().addGetResource(ResourceType.ORE, ore);
					}
					else if(ore < 0) 
					{
						ore = ore * -1;
						this.getAcceptOverlay().addGiveResource(ResourceType.ORE, ore);
						canAccept = (receiver.getResources().getOre() + ore > -1) && canAccept;
					}
					
					int wheat = resources.getWheat();
					if(wheat > 0) 
					{
						this.getAcceptOverlay().addGetResource(ResourceType.WHEAT, wheat);
					}
					else if(wheat < 0) 
					{
						wheat = wheat * -1;
						this.getAcceptOverlay().addGiveResource(ResourceType.WHEAT, wheat);
						canAccept = (receiver.getResources().getWheat() + wheat > -1) && canAccept;
					}
					
					int wood = resources.getWood();
					if(wood > 0) 
					{
						this.getAcceptOverlay().addGetResource(ResourceType.WOOD, wood);
					}
					else if(wood < 0) 
					{
						wood = wood * -1;
						this.getAcceptOverlay().addGiveResource(ResourceType.WOOD, wood);
						canAccept = (receiver.getResources().getWood() + wood > -1) && canAccept;
					}
					
					String senderName = players[offer.getSender()].getName();
					this.getAcceptOverlay().setPlayerName(senderName);
					this.getAcceptOverlay().setAcceptEnabled(canAccept);
					this.getAcceptOverlay().showModal();
				}
			}
			else 
			{
				if(this.getWaitOverlay().isModalShowing()) 
				{
					this.getWaitOverlay().closeModal();
					//this.getWaitOverlay().reset();
					this.getTradeOverlay().reset();
				}
			}
		}		
	}

}

