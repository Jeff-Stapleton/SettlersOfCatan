package client.controller.resources;

import java.util.*;

import shared.CanCan;
import shared.CatanModel;
import shared.Player;
import client.CatanGame;
import client.view.base.*;
import client.view.resources.IResourceBarView;
import client.view.resources.ResourceBarElement;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, Observer {

	private Map<ResourceBarElement, IAction> elementActions;
	
	public ResourceBarController(IResourceBarView view) {

		super(view);
		
		elementActions = new HashMap<ResourceBarElement, IAction>();
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {
		executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (o instanceof CatanGame)
		{
			CatanModel model = ((CatanGame) o).getModel();
			Player thisPlayer = model.getPlayers()[((CatanGame) o).getPlayerID()];
			// Check for enable disable DevCard Button
			if (CanCan.canBuyDevCard(thisPlayer, model.getDeck(), model.getTurnTracker()))
			{
				getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
			}
			else
			{
				getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
			}
		}
		
	}

}

