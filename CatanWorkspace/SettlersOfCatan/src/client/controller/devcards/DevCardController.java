package client.controller.devcards;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JToggleButton;

import org.apache.log4j.Logger;

import shared.CanCan;
import shared.CatanModel;
import shared.DevCardList;
import shared.Player;
import shared.ResourceList;
import shared.TurnTracker;
import shared.TurnType;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import client.CatanGame;
import client.view.base.*;
import client.view.devcards.IBuyDevCardView;
import client.view.devcards.IPlayDevCardView;
import client.view.resources.ResourceBarElement;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController, Observer
{
	private static final Logger log = Logger.getLogger(DevCardController.class.getName());

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	
	// variables added by Jordan
	private CatanGame catanGame;
	private CatanModel model;
	private Player thisPlayer;
	private TurnTracker turn;
	private DevCardList deck;
	private ResourceList bank;
	// end variables
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction, CatanGame catanGame) {

		super(view);
		catanGame.addObserver(this);
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
		this.catanGame = catanGame;
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}

	@Override
	public void startBuyCard() {
		log.trace("Showing buy card modal --\\");
		getBuyCardView().showModal();
	}

	@Override
	public void cancelBuyCard() {
		getBuyCardView().closeModal();
		log.trace("Closed buy card modal --/");
	}

	@Override
	public void buyCard() {	
		try {
			catanGame.updateModel(catanGame.getProxy().movesBuyDevCard(thisPlayer.getPlayerIndex()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		getBuyCardView().closeModal();
		log.trace("Closed buy card modal --/");
	}

	@Override
	public void startPlayCard() {
		log.trace("Showing play card modal --\\");
		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {
		getPlayCardView().closeModal();
		log.trace("Closed play card modal --/");
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		try {
			catanGame.updateModel(catanGame.getProxy().movesMonopoly(thisPlayer.getPlayerIndex(), resource));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void playMonumentCard() {
		try {
			catanGame.updateModel(catanGame.getProxy().movesMonument(thisPlayer.getPlayerIndex()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void playRoadBuildCard() {
		// This eventually ends up being called in MapController.playRoadBuildingCard()
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		// This eventually ends up being called in MapController.playRoadBuildingCard()
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		try {
			catanGame.updateModel(catanGame.getProxy().movesYearOfPlenty(thisPlayer.getPlayerIndex(), resource1, resource2));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs instanceof CatanGame) {
			catanGame = (CatanGame) obs;
			model = catanGame.getModel();
			thisPlayer = model.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()];
			turn = model.getTurnTracker();
			deck = model.getDeck();
			bank = model.getBank();
			
			// update counts
			getPlayCardView().setCardAmount(DevCardType.MONOPOLY, thisPlayer.getOldDevCards().getMonopoly() + thisPlayer.getNewDevCards().getMonopoly());
			getPlayCardView().setCardAmount(DevCardType.MONUMENT, thisPlayer.getOldDevCards().getMonument() + thisPlayer.getNewDevCards().getMonument());
			getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, thisPlayer.getOldDevCards().getRoadBuilding() + thisPlayer.getNewDevCards().getRoadBuilding());
			getPlayCardView().setCardAmount(DevCardType.SOLDIER, thisPlayer.getOldDevCards().getSoldier() + thisPlayer.getNewDevCards().getSoldier());
			getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, thisPlayer.getOldDevCards().getYearOfPlenty() + thisPlayer.getNewDevCards().getYearOfPlenty());

			
			// enable based on OldDevCardList
			getPlayCardView().setCardEnabled(DevCardType.MONOPOLY, CanCan.canUseMonopoly(thisPlayer, turn));
			getPlayCardView().setCardEnabled(DevCardType.MONUMENT, CanCan.canUseMonument(thisPlayer, turn));
			getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD, CanCan.canUseRoadBuilder(thisPlayer, turn));
			getPlayCardView().setCardEnabled(DevCardType.SOLDIER, CanCan.canUseSoldier(thisPlayer, turn));
			getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY, CanCan.canUseYearOfPlenty(thisPlayer, turn));		
			
			
		}
	}

}

