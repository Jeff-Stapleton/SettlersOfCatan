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
				catanLobby.getGame().updateModel(catanLobby.getProxy().gameModel());
				catanLobby.getGame().startServerPoller();
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
		if (catanLobby.getGame().getGameInfo().getPlayers().size() == 4)
		{
			catanLobby.stopLobbyPoller();

			getView().closeModal();
			log.trace("Closed player waiting modal --/");
			catanLobby.getGame().startServerPoller();
		}
		else
		{
			getView().setPlayers(catanLobby.getGame().getGameInfo().getPlayers().toArray(new PlayerInfo[0]));
			getView().closeModal();
			log.trace("Closed player waiting modal --/");
			log.trace("Showing player waiting modal --\\");
			getView().showModal();
		}
	}

}

