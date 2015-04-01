package client.controller.discard;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import org.apache.log4j.Logger;

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
	private static final Logger log = Logger.getLogger(DiscardController.class.getName());

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
				discardView.setResourceDiscardAmount(ResourceType.WOOD, ++woodDiscardAmount);
				updateButtons(woodDiscardAmount,woodMax,resource);
				break;
			}
			case BRICK:
			{
				discardView.setResourceDiscardAmount(ResourceType.BRICK, ++brickDiscardAmount);
				updateButtons(brickDiscardAmount,brickMax,resource);
				break;
			}
			case SHEEP:
			{
				discardView.setResourceDiscardAmount(ResourceType.SHEEP, ++sheepDiscardAmount);
				updateButtons(sheepDiscardAmount,sheepMax,resource);
				break;
			}
			case WHEAT:
			{
				discardView.setResourceDiscardAmount(ResourceType.WHEAT, ++wheatDiscardAmount);
				updateButtons(wheatDiscardAmount,wheatMax,resource);
				break;
			}
			case ORE:
			{
				discardView.setResourceDiscardAmount(ResourceType.ORE, ++oreDiscardAmount);
				updateButtons(oreDiscardAmount,oreMax,resource);
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
				discardView.setResourceDiscardAmount(ResourceType.WOOD, --woodDiscardAmount);
				updateButtons(woodDiscardAmount,woodMax,resource);
				break;
			}
			case BRICK:
			{
				discardView.setResourceDiscardAmount(ResourceType.BRICK, --brickDiscardAmount);
				updateButtons(brickDiscardAmount,brickMax,resource);
				break;
			}
			case SHEEP:
			{
				discardView.setResourceDiscardAmount(ResourceType.SHEEP, --sheepDiscardAmount);
				updateButtons(sheepDiscardAmount,sheepMax,resource);
				break;
			}
			case WHEAT:
			{
				discardView.setResourceDiscardAmount(ResourceType.WHEAT, --wheatDiscardAmount);
				updateButtons(wheatDiscardAmount,wheatMax,resource);
				break;
			}
			case ORE:
			{
				discardView.setResourceDiscardAmount(ResourceType.ORE, --oreDiscardAmount);
				updateButtons(oreDiscardAmount,oreMax,resource);
				break;
			}
		}
		totalDiscardSelected--;
		discardView.setDiscardButtonEnabled(false);
		updateResourceValues();

		discardView.setStateMessage(totalDiscardSelected + "/" + totalResources/2);
	}
	
	@Override
	public void discard() {
		
		ResourceList resourceHand = new ResourceList(brickDiscardAmount, woodDiscardAmount, wheatDiscardAmount, sheepDiscardAmount, oreDiscardAmount);
		try {
			catanGame.updateModel(catanGame.getProxy().movesDiscardCards(catanGame.getPlayerInfo().getPlayerIndex(), resourceHand));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		log.trace("Updating");
		if (obs instanceof CatanGame) 
		{
			catanModel = ((CatanGame) obs).getModel();
			
			if (catanModel.getTurnTracker().getStatus().equals(TurnType.DISCARDING))
			{
				log.trace("In discarding state");
				initDiscardValues();
				if (!catanGame.getCurrentPlayer().hasDiscarded() &&
					!discardView.isModalShowing())
				{
					log.trace("Player has not discarded yet");
					if (totalResources >= 7)
					{
						log.trace("Showing discard modal --\\");
						discardView.showModal();
						updateResourceValues();
					}
					else
					{
						try {
							log.trace("Sending empty discard");
							catanGame.getProxy().movesDiscardCards(catanGame.getPlayerInfo().getPlayerIndex(), new ResourceList());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				
				if (catanGame.getCurrentPlayer().hasDiscarded() &&
					discardView.isModalShowing())
				{
					log.trace("Player has discarded");
					discardView.closeModal();
					log.trace("Closed discard view modal --/");
				}
				
				if (catanGame.getCurrentPlayer().hasDiscarded() &&
					waitView.isModalShowing() == false)
				{
					log.trace("Player has discarded, showing wait dialog");
					// We discarded, but the wait modal isn't up
					log.trace("Showing wait modal --\\");
					waitView.showModal();
				}
				
			}
			else
			{
				log.trace("Not in discarding state");
				if (discardView.isModalShowing()) {
					discardView.closeModal();
					log.trace("Closed discard view --/");
				}
				if(waitView.isModalShowing()) {
					waitView.closeModal();
					log.trace("Closed wait view --/");
				}
			}
		}
	}
}


