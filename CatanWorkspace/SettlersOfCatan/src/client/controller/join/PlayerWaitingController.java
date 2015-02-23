package client.controller.join;

import client.comm.IServerProxy;
import client.view.base.*;
import client.view.join.IPlayerWaitingView;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController {

	public PlayerWaitingController(IServerProxy serverProxy, IPlayerWaitingView view) {
		super(view);
		
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {

		getView().showModal();
	}

	@Override
	public void addAI() {

		// TEMPORARY
		getView().closeModal();
	}

}

