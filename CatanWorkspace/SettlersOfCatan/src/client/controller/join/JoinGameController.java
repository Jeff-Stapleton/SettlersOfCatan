package client.controller.join;

import java.io.IOException;

import org.apache.log4j.Logger;

import shared.definitions.CatanColor;
import client.CatanLobby;
import client.view.base.*;
import client.view.data.*;
import client.view.join.IJoinGameView;
import client.view.join.INewGameView;
import client.view.join.ISelectColorView;
import client.view.misc.*;

/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController
{
	private static final Logger log = Logger.getLogger(JoinGameController.class.getName());
	
	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private CatanLobby catanLobby;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(CatanLobby catanLobby, IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView)
	{
		super(view);
		
		this.catanLobby = catanLobby;

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
	}

	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value)
	{	
		joinAction = value;
		log.trace("Join action set");
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}
	
	private void updateGameList()
	{
		try {
			GameInfo[] games = catanLobby.getProxy().gamesList();
			
			((IJoinGameView)super.getView()).setGames(games, catanLobby.getPlayerInfo());
		} catch (IOException e) {
			System.err.println("Could not get games list from server.");
			e.printStackTrace();
		}
		
	}

	@Override
	public void start()
	{
		log.trace("Updating game list");
		updateGameList();
		log.trace("Showing join game modal --\\");
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame()
	{
		log.trace("Showing new game modal --\\");
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame()
	{
		log.trace("Canceled creating a new game");
		getNewGameView().closeModal();
		log.trace("Closed new game modal --/");
		
		try {
			log.trace("Refreshing games list");
			getJoinGameView().setGames(catanLobby.getProxy().gamesList(), catanLobby.getPlayerInfo());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void createNewGame()
	{
		try
		{
			if (getNewGameView().getTitle().toString().length() > 0)
			{
				log.trace("Creating a new game");
				catanLobby.getProxy().gamesCreate(getNewGameView().getTitle(),
												  getNewGameView().getRandomlyPlaceHexes(),
												  getNewGameView().getRandomlyPlaceNumbers(),
												  getNewGameView().getUseRandomPorts());
				getNewGameView().closeModal();
				log.trace("Closed new game modal --/");
				
				log.trace("Refreshing games list for joining");
				getJoinGameView().setGames(catanLobby.getProxy().gamesList(), catanLobby.getPlayerInfo());
			}
			else
				getNewGameView().setTitle("Please enter a valid game name");
		}
		catch (IOException e)
		{
			log.error("Unable to create a new game on server");
			e.printStackTrace();
		}
	}

	@Override
	public void startJoinGame(GameInfo game)
	{
		log.trace("Starting join game by resetting color view");
		getSelectColorView().reset();
		
		log.trace("Disabling taken colors for color view");
		for (PlayerInfo player : game.getPlayers())
		{
			if (player.getId() != catanLobby.getPlayerInfo().getId())
			{
				getSelectColorView().setColorEnabled(player.getColor(), false);
			}
			else
			{
				catanLobby.getPlayerInfo().setColor(player.getColor());
				getSelectColorView().setSelectedColor(player.getColor());
			}
		}
		
		catanLobby.getGame().setGameInfo(game);
		
		log.trace("Showing color selection modal --\\");
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame()
	{
		log.trace("Join game canceled");
		catanLobby.getGame().setGameInfo(null);
		
		getJoinGameView().closeModal();
		log.trace("Closed join game modal --/");
	}

	@Override
	public void joinGame(CatanColor color)
	{
		try {
			catanLobby.gamesJoin(color, catanLobby.getGame().getGameInfo().getId());
		} catch (IOException e) {
			System.err.println("Could not get model from server");
			e.printStackTrace();
		}
		
		// If join succeeded
		catanLobby.getGame().getPlayerInfo().setColor(color);
		getSelectColorView().closeModal();
		log.trace("Closed color selection modal --/");
		getJoinGameView().closeModal();
		log.trace("Closed join game modal --/");
		
		joinAction.execute();
	}
}

