package client.controller.discard;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.*;
import client.CatanGame;
import client.view.base.*;
import client.view.discard.IDiscardView;
import client.view.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IWaitView waitView;
	private CatanGame catanGame;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(CatanGame game, IDiscardView view, IWaitView waitView)
	{
		super(view);
		
		this.waitView = waitView;
		catanGame = game;
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
	}

	@Override
	public void discard() {
		
		getDiscardView().closeModal();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}

