package client.controller.roll;

import java.util.Random;

import client.comm.IServerProxy;
import client.view.base.*;
import client.view.roll.IRollResultView;
import client.view.roll.IRollView;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController {

	private IRollResultView resultView;
	private IRollView rollView;
	private IServerProxy serverProxy;
	
	private int rollValue;
	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView, IServerProxy serverProxy) 
	{
		super(view);
		setResultView(resultView);
		this.serverProxy = serverProxy;
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
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
//		System.out.println("dice1 " + dice1 + " dice2 " + dice2);
		getResultView().setRollValue(diceRoll);
//		getResultView().setRollValue(7);
		rollView.closeModal();
		getResultView().showModal();	
		//presenter.rollNumber(diceRoll);
		rollValue = diceRoll;
	}
}

