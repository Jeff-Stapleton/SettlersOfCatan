package client.controller.points;

import java.util.Observable;
import java.util.Observer;

import shared.CatanModel;
import shared.Player;
import client.CatanGame;
import client.view.base.*;
import client.view.points.IGameFinishedView;
import client.view.points.IPointsView;

/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, Observer {

	private IGameFinishedView finishedView;
	private CatanGame catanGame;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView, CatanGame catanGame)
	{
		super(view);
		
		this.catanGame = catanGame;
		
		catanGame.addObserver(this);
		
		setFinishedView(finishedView);
	}
	
	public IPointsView getPointsView() {		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	@Override
	public void update(Observable obs, Object obj) 
	{
		if (obs instanceof CatanGame) 
		{
			catanGame = (CatanGame) obs;
			
			if (catanGame.getModel().getTurnTracker().getStatus() != null) {
				getPointsView().setPoints(catanGame.getModel().getPlayers()[catanGame.getCurrentPlayer().getPlayerIndex()].getVictoryPoints());
				if (catanGame.getModel().getWinner() > -1) {
					if (catanGame.getPlayerInfo().getId() == catanGame.getModel().getWinner()) 
					{
						getFinishedView().setWinner(catanGame.getModel().getPlayers()[catanGame.getModel().getTurnTracker().getCurrentTurn()].getName(), true);
						getFinishedView().showModal();
					}
					else {
						getFinishedView().setWinner(catanGame.getModel().getPlayers()[catanGame.getModel().getTurnTracker().getCurrentTurn()].getName(), false);
						getFinishedView().showModal();
					}
				}
			}
			
			getPointsView().setPoints(catanGame.getModel().getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].getVictoryPoints());
		}
	}
	
}

