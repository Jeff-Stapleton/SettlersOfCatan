package client.controller.discard;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.TurnType;
import shared.definitions.*;
import client.CatanGame;
import client.view.base.*;
import client.view.discard.IDiscardView;
import client.view.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer 
{

	private IWaitView waitView;
	private ResourceList discard;
	private CatanGame catanGame;
	private CatanModel catanModel = null;
	private IDiscardView discardView;
	private Player players[];
	private int player;
	
	private int brickMax;
	private int brickDiscardAmount = 0;
	private int oreMax;
	private int oreDiscardAmount = 0;
	private int sheepMax;
	private int sheepDiscardAmount = 0;
	private int wheatMax;
	private int wheatDiscardAmount = 0;
	private int woodMax;
	private int woodDiscardAmount = 0;
	private int totalResources;
	private int totalDiscardSelected = 0;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView,  CatanGame catanGame) {
		
		super(view);
		
		this.catanGame = catanGame;
		this.waitView = waitView;
		this.discardView = view;
		catanGame.addObserver(this);
		discardView.setDiscardButtonEnabled(false);
		
		//player = catanGame.getPlayerInfo().getPlayerIndex();
		//players = catanGame.getModel().getPlayers();
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				//discard.setWood(discard.getWood() + 1);
				discardView.setResourceDiscardAmount(ResourceType.WOOD, ++woodDiscardAmount);
				updateButtons(woodDiscardAmount,woodMax,resource);
				//handleEnablingResources(resource);
				break;
			}
			case BRICK:
			{
				//discard.setBrick(discard.getBrick() + 1);
				discardView.setResourceDiscardAmount(ResourceType.BRICK, ++brickDiscardAmount);
				updateButtons(brickDiscardAmount,brickMax,resource);
				//handleEnablingResources(resource);
				break;
			}
			case SHEEP:
			{
				//discard.setSheep(discard.getSheep() + 1);
				discardView.setResourceDiscardAmount(ResourceType.SHEEP, ++sheepDiscardAmount);
				updateButtons(sheepDiscardAmount,sheepMax,resource);
				//handleEnablingResources(resource);
				break;
			}
			case WHEAT:
			{
				//discard.setWheat(discard.getWheat() + 1);
				discardView.setResourceDiscardAmount(ResourceType.WHEAT, ++wheatDiscardAmount);
				updateButtons(wheatDiscardAmount,wheatMax,resource);
				//handleEnablingResources(resource);
				break;
			}
			case ORE:
			{
				//discard.setOre(discard.getOre() + 1);
				discardView.setResourceDiscardAmount(ResourceType.ORE, ++oreDiscardAmount);
				updateButtons(oreDiscardAmount,oreMax,resource);
				//handleEnablingResources(resource);
				break;
			}
		}
		
		totalDiscardSelected++;
		if(totalDiscardSelected == totalResources/2){
			updateResourceValues();
			discardView.setDiscardButtonEnabled(true);
		}
		else{
			discardView.setDiscardButtonEnabled(false);
		}

		discardView.setStateMessage(totalDiscardSelected + "/" + totalResources/2);
	}
	
	private void updateResourceValues(){
		updateButtons(brickDiscardAmount,brickMax,ResourceType.BRICK);
		updateButtons(oreDiscardAmount,oreMax,ResourceType.ORE);
		updateButtons(sheepDiscardAmount,sheepMax,ResourceType.SHEEP);
		updateButtons(wheatDiscardAmount,wheatMax,ResourceType.WHEAT);
		updateButtons(woodDiscardAmount,woodMax,ResourceType.WOOD);
	}
	
	public void updateButtons(int discardAmount, int totalAmount, ResourceType resource){
		if(totalDiscardSelected == totalResources/2){
			if(discardAmount < totalAmount){
				if(discardAmount == 0){
					discardView.setResourceAmountChangeEnabled(resource, false, false);
				}
				else{				
					discardView.setResourceAmountChangeEnabled(resource, false, true);
				}
			}
			else{
				if(discardAmount == 0){
					discardView.setResourceAmountChangeEnabled(resource, false, false);
				}
				else{
					discardView.setResourceAmountChangeEnabled(resource, false, true);
				}
			}
		}
		else{
			if(discardAmount < totalAmount){
				if(discardAmount == 0){
					discardView.setResourceAmountChangeEnabled(resource, true, false);
				}
				else{				
					discardView.setResourceAmountChangeEnabled(resource, true, true);
				}
			}
			else{
				if(discardAmount == 0){
					discardView.setResourceAmountChangeEnabled(resource, false, false);
				}
				else{
					discardView.setResourceAmountChangeEnabled(resource, false, true);
				}
			}
		}
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				//discard.setWood(discard.getWood() - 1);
				discardView.setResourceDiscardAmount(ResourceType.WOOD, --woodDiscardAmount);
				updateButtons(woodDiscardAmount,woodMax,resource);
				//handleEnablingResources(resource);
				break;
			}
			case BRICK:
			{
				//discard.setBrick(discard.getBrick() - 1);
				discardView.setResourceDiscardAmount(ResourceType.BRICK, --brickDiscardAmount);
				updateButtons(brickDiscardAmount,brickMax,resource);
				//handleEnablingResources(resource);
				break;
			}
			case SHEEP:
			{
				//discard.setSheep(discard.getSheep() - 1);
				discardView.setResourceDiscardAmount(ResourceType.SHEEP, --sheepDiscardAmount);
				updateButtons(sheepDiscardAmount,sheepMax,resource);
				//handleEnablingResources(resource);
				break;
			}
			case WHEAT:
			{
				//discard.setWheat(discard.getWheat() - 1);
				discardView.setResourceDiscardAmount(ResourceType.WHEAT, --wheatDiscardAmount);
				updateButtons(wheatDiscardAmount,wheatMax,resource);
				//handleEnablingResources(resource);
				break;
			}
			case ORE:
			{
				//discard.setOre(discard.getOre() - 1);
				discardView.setResourceDiscardAmount(ResourceType.ORE, --oreDiscardAmount);
				updateButtons(oreDiscardAmount,oreMax,resource);
				//handleEnablingResources(resource);
				break;
			}
		}
		totalDiscardSelected--;
		discardView.setDiscardButtonEnabled(false);
		updateResourceValues();

		discardView.setStateMessage(totalDiscardSelected + "/" + totalResources/2);
	}
	
/*
 * 	public void handleEnablingResources(ResourceType resource)
	{
		switch (resource)
		{
			case WOOD:
			{
				if (discard.getWood() < players[player].getResources().getWood())
				{
					if (discard.getWood() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
					}
				}
				else
				{
					if (discard.getWood() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, false);
					}
				}
			}
			case BRICK:
			{
				if (discard.getBrick() < players[player].getResources().getBrick())
				{
					if (discard.getBrick() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, false);
					}
				}
				else
				{
					if (discard.getBrick() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, false);
					}
				}
			}
			case SHEEP:
			{
				if (discard.getSheep() < players[player].getResources().getSheep())
				{
					if (discard.getSheep() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
					}
				}
				else
				{
					if (discard.getSheep() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, false);
					}
				}
			}
			case WHEAT:
			{
				if (discard.getWheat() < players[player].getResources().getWheat())
				{
					if (discard.getWheat() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
					}
				}
				else
				{
					if (discard.getWheat() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, false);
					}
				}
			}
			case ORE:
			{
				if (discard.getOre() < players[player].getResources().getOre())
				{
					if (discard.getOre() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, false);
					}
				}
				else
				{
					if (discard.getOre() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, false);
					}
				}
			}
		}
	}(non-Javadoc)
 * @see client.controller.discard.IDiscardController#discard()
 */

	@Override
	public void discard() {
		
		ResourceList resourceHand = new ResourceList(brickDiscardAmount, woodDiscardAmount, wheatDiscardAmount, sheepDiscardAmount, oreDiscardAmount);
		try {
			catanGame.updateModel(catanGame.getProxy().movesDiscardCards(catanGame.getPlayerInfo().getPlayerIndex(), resourceHand));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getDiscardView().closeModal();
		brickDiscardAmount=0;
		woodDiscardAmount=0;
		sheepDiscardAmount=0;
		wheatDiscardAmount=0;
		oreDiscardAmount=0;
		discardView.setDiscardButtonEnabled(false);
	}
	
	private void resetAllDiscardValues() 
	{
		brickDiscardAmount = 0;
		oreDiscardAmount = 0;
		sheepDiscardAmount = 0;
		wheatDiscardAmount = 0;
		woodDiscardAmount = 0;
		totalDiscardSelected = 0;
		
		discardView.setStateMessage(totalDiscardSelected + "/" + totalResources/2);
		discardView.setResourceDiscardAmount(ResourceType.BRICK, 0);
		discardView.setResourceDiscardAmount(ResourceType.ORE, 0);
		discardView.setResourceDiscardAmount(ResourceType.SHEEP, 0);
		discardView.setResourceDiscardAmount(ResourceType.WHEAT, 0);
		discardView.setResourceDiscardAmount(ResourceType.WOOD, 0);
	}
	
	private void initDiscardValues(){
		ResourceList r = catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].getResources();
		totalResources = r.getBrick()+r.getOre()+r.getSheep()+r.getWheat()+r.getWood();
		discardView.setResourceMaxAmount(ResourceType.BRICK, r.getBrick());
		discardView.setResourceMaxAmount(ResourceType.ORE, r.getOre());
		discardView.setResourceMaxAmount(ResourceType.SHEEP, r.getSheep());
		discardView.setResourceMaxAmount(ResourceType.WHEAT, r.getWheat());
		discardView.setResourceMaxAmount(ResourceType.WOOD, r.getWood());	
		this.brickMax = r.getBrick();
		this.oreMax = r.getOre();
		this.sheepMax = r.getSheep();
		this.wheatMax = r.getWheat();
		this.woodMax = r.getWood();
		
		resetAllDiscardValues();
	}
	
	@Override
	public void update(Observable obs, Object obj) 
	{
		if (obs instanceof CatanGame) 
		{
			catanModel = ((CatanGame) obs).getModel();
			
			if (catanModel.getTurnTracker().getStatus().equals(TurnType.DISCARDING))
			{
				initDiscardValues();
				if (totalResources >= 7 &&
					catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].hasDiscarded())
				{
					discardView.showModal();
					updateResourceValues();
				}
				else if(totalResources < 7 ||
					catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].hasDiscarded())
				{
					try {
						catanGame.getProxy().movesDiscardCards(catanGame.getPlayerInfo().getPlayerIndex(), new ResourceList());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					waitView.showModal();
				}
			}
			else
			{
				if(waitView.isModalShowing()) {
					waitView.closeModal();
				}
				if(this.getDiscardView().isModalShowing() && catanModel.getTurnTracker().getStatus().equals(TurnType.ROBBING)) {
					this.getDiscardView().closeModal();
				}
				
				//issue 209 fix
				hasDiscarded = false;
			}
		}
	}
}


