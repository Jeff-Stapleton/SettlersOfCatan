package client.controller.join;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import client.CatanLobby;
import client.view.base.*;
import client.view.data.PlayerInfo;
import client.view.join.IPlayerWaitingView;

/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer
{
	private static final Logger log = Logger.getLogger(PlayerWaitingController.class.getName());
	
	CatanLobby catanLobby;

	public PlayerWaitingController(CatanLobby catanLobby, IPlayerWaitingView view)
	{
		super(view);
		
		catanLobby.addObserver(this);
		this.catanLobby = catanLobby;
	}

	@Override
	public IPlayerWaitingView getView()
	{
		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start()
	{
		log.trace("Player waiting start");
		if (catanLobby.getGame().getGameInfo().getPlayers().size() < 4)
		{
			String[] AIChoices = {""};
			try {
				AIChoices = catanLobby.getProxy().gameListAI();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			getView().setAIChoices(AIChoices);
			getView().setPlayers(catanLobby.getGame().getGameInfo().getPlayers().toArray(new PlayerInfo[0]));
			log.trace("Showing player waiting modal --\\");
			getView().showModal();

			catanLobby.startLobbyPoller();
		}
		else
		{
			try {
				log.trace("Enough players. Lets join the game!");
				catanLobby.getGame().startServerPoller();
				catanLobby.getGame().updateModel(catanLobby.getProxy().gameModel());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void addAI() 
	{
		try {
			catanLobby.getProxy().gameAddAI(getView().getSelectedAI());
		} catch (IOException e) {
			String outputStr = "Could not reach the server.";
			JOptionPane.showMessageDialog(null, outputStr,
					"Server unavailable", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable o, Object obj)
	{
		log.trace("Updating player waiting controller");
		if (catanLobby.getGame().getGameInfo().getPlayers().size() == 4)
		{
			log.trace("Enough players, moving on to the game");
			catanLobby.stopLobbyPoller();

			getView().closeModal();
			log.trace("Closed player waiting modal --/");
			catanLobby.getGame().startServerPoller();
		}
		else
		{
			log.trace("Still not enough players. Refreshing wait view");
			getView().setPlayers(catanLobby.getGame().getGameInfo().getPlayers().toArray(new PlayerInfo[0]));
			getView().closeModal();
			log.trace("Closed player waiting modal --/");
			log.trace("Showing player waiting modal --\\");
			getView().showModal();
		}
	}

}

