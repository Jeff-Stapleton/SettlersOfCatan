package client.controller.join;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import shared.CatanModel;
import client.CatanGame;
import client.comm.IServerProxy;
import client.view.base.*;
import client.view.join.IPlayerWaitingView;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {
	
	CatanGame catanGame;

	public PlayerWaitingController(CatanGame catanGame, IPlayerWaitingView view) {
		super(view);
		
		catanGame.addObserver(this);
		this.catanGame = catanGame;
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {
		if (catanGame.getModel() != null)
		{
			CatanModel model = catanGame.getModel();
			
			if (model.getPlayers().length < 4)
			{
				getView().showModal();
			}
			
			String[] AIChoices = {""};
			try {
				AIChoices = catanGame.getProxy().gameListAI();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			getView().setAIChoices(AIChoices);
		}
	}

	@Override
	public void addAI() 
	{
		try {
			catanGame.getProxy().gameAddAI(getView().getSelectedAI());
		} catch (IOException e) {
			String outputStr = "Could not reach the server.";
			JOptionPane.showMessageDialog(null, outputStr,
					"Server unavailable", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object obj) {
		if (o instanceof CatanGame) {
			CatanGame game = (CatanGame) o;

			if (game.getModel().getPlayers().length == 4)
			{
				getView().closeModal();
			}
		}
	}

}

