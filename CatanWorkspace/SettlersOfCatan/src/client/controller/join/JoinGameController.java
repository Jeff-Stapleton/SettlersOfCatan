package client.controller.join;

import java.io.IOException;
import java.util.ArrayList;

import shared.comm.serialization.GameResponse;
import shared.comm.serialization.PlayerResponse;
import shared.definitions.CatanColor;
import client.CatanGame;
import client.comm.IServerProxy;
import client.view.base.*;
import client.view.data.*;
import client.view.join.IJoinGameView;
import client.view.join.INewGameView;
import client.view.join.ISelectColorView;
import client.view.misc.*;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private CatanGame catanGame;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(CatanGame catanGame, IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView) {

		super(view);
		
		this.catanGame = catanGame;

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
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
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

	@Override
	public void start() {
		try {
			PlayerInfo currentPlayer = catanGame.getPlayerInfo();
			GameResponse[] games = catanGame.getProxy().gamesList();
			GameInfo[] gameInfos = new GameInfo[games.length];
			for (int i = 0; i < games.length; i++) {
				ArrayList<PlayerInfo> playerInfos = new ArrayList<PlayerInfo>();
				for (int j = 0; j < games[i].getPlayers().length; j++) {
					PlayerResponse player = games[i].getPlayers()[j];
					PlayerInfo playerInfo = new PlayerInfo(player.getId(), j, player.getName(), CatanColor.fromString(player.getColor()));
					playerInfos.add(playerInfo);
					if (playerInfo.getId() == currentPlayer.getId()) {
						currentPlayer = playerInfo;
					}
				}
				gameInfos[i] = new GameInfo(games[i].getId(), games[i].getTitle(), playerInfos);
			}
			
			// Game infos built, now put them in the view
			((IJoinGameView)super.getView()).setGames(gameInfos, currentPlayer);
		} catch (IOException e) {
			System.out.println("Could not get games list from server.");
			e.printStackTrace();
		}
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {
		
		getNewGameView().closeModal();
	}

	@Override
	public void startJoinGame(GameInfo game) {
		for (CatanColor color : new CatanColor[] {CatanColor.BLUE, CatanColor.BROWN, CatanColor.GREEN,
												  CatanColor.ORANGE, CatanColor.PUCE, CatanColor.PURPLE,
												  CatanColor.RED, CatanColor.WHITE, CatanColor.YELLOW})
		{
			getSelectColorView().setColorEnabled(color, true);
		}
		
		for (PlayerInfo player : game.getPlayers())
		{
			if (player.getId() != catanGame.getPlayerInfo().getId())
			{
				getSelectColorView().setColorEnabled(player.getColor(), false);
			}
		}
		
		catanGame.setGameInfo(game);
		
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
		catanGame.setGameInfo(null);
		
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		try {
			catanGame.getProxy().gamesJoin(color, catanGame.getGameInfo().getId());
			catanGame.updateModel();
		} catch (IOException e) {
			System.out.println("Could not get model from server");
			e.printStackTrace();
		}
		
		// If join succeeded
		getSelectColorView().closeModal();
		getJoinGameView().closeModal();
		joinAction.execute();
	}

}

