package client.controller.communication;

import java.util.*;

import client.CatanGame;
import client.view.base.*;
import client.view.communication.IGameHistoryView;
import client.view.communication.LogEntry;
import shared.CatanModel;
import shared.MessageLine;
import shared.Player;
import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, Observer {

	private CatanModel catanModel;
	private CatanGame catanGame;
	public GameHistoryController(CatanGame catanGame, IGameHistoryView view) 
	{
		
		super(view);
		this.catanGame = catanGame;
		catanGame.addObserver(this);
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}

	@Override
	public void update(Observable obs, Object obj) 
	{
		if (obs instanceof CatanGame) 
		{
			catanModel = ((CatanGame) obs).getModel();
			
	        List<LogEntry> entries = new ArrayList<LogEntry>();

	        for (MessageLine line : catanGame.getModel().getLog().getLines())
	        {
	        	String user = line.getSource();
	        	CatanColor color = null;
	        	color = catanGame.getGameInfo().getPlayerWithName(user).getColor();
	        	entries.add(new LogEntry(color, line.getMessage()));
	        }
	        
	        getView().setEntries(entries);
			
		}
	}
	
}

