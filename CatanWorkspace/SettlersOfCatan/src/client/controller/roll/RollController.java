package client.controller.roll;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import org.apache.log4j.Logger;

import shared.CatanModel;
import shared.TurnType;
import client.CatanGame;
import client.comm.IServerProxy;
import client.view.base.*;
import client.view.roll.IRollResultView;
import client.view.roll.IRollView;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, Observer
{
	private static final Logger log = Logger.getLogger(RollController.class.getName());

	private IRollResultView resultView;
	private IRollView rollView;
	private CatanGame catanGame;
	private CatanModel catanModel;
	
	private int rollValue;
	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(CatanGame catanGame, IRollView view, IRollResultView resultView) 
	{
		super(view);
		
		this.catanGame = catanGame;
		catanGame.addObserver(this);
		rollView = view;
		setResultView(resultView);
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
		resultView.setController(this);
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	@Override
	public void rollDice() {
		Random randomGenerator1 = new Random();
		Random randomGenerator2 = new Random();
		int dice1 = randomGenerator1.nextInt(6) + 1;
		int dice2 = randomGenerator2.nextInt(6) + 1;
		int diceRoll = dice1 + dice2;
		getResultView().setRollValue(diceRoll);
//		rollView.closeModal();
//		log.trace("Closed roll view modal --/");
		log.trace("Showing roll result modal --\\");
		getResultView().showModal();	
		rollValue = diceRoll;
	}
	
	@Override
	public void update(Observable obs, Object obj) 
	{
		if (obs instanceof CatanGame) 
		{
			catanModel = ((CatanGame) obs).getModel();
			if (catanModel.getTurnTracker().getStatus().equals(TurnType.ROLLING) &&
				catanModel.getTurnTracker().getCurrentTurn() == catanGame.getPlayerInfo().getPlayerIndex() &&
				!rollView.isModalShowing())
			{
				log.debug(catanModel.getTurnTracker().toString());
				log.trace("Showing roll view modal --\\");
				rollView.showModal();
			}
		}
	}

	@Override
	public void sendRoll(int number) {
		try {
			catanGame.updateModel(catanGame.getProxy().movesRollNumber(catanGame.getPlayerInfo().getPlayerIndex(), number));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

