package client.controller.communication;

import java.util.*;

import client.CatanGame;
import client.view.base.*;
import client.view.communication.IGameHistoryView;
import client.view.communication.LogEntry;
import shared.CatanModel;
import shared.Player;
import shared.definitions.*;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, Observer {

	private CatanModel catanModel;
	private CatanGame catanGame;
	public GameHistoryController(CatanGame catanGame, IGameHistoryView view) {
		
		super(view);
		this.catanGame = catanGame;
		catanGame.addObserver(this);
		initFromModel();
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
		
		List<LogEntry> entries = new ArrayList<LogEntry>();
		int playerIndex = -1;
        
        if (catanModel != null) {
	        for (int i = 0; i < catanModel.getLog().getLines().size(); i++) 
	        {
	        	String name = catanModel.getLog().getLines().get(i).getSource();
	        	for (int j = 0; j < 4; j++)
	        	{
	        		if (catanModel.getPlayers()[j].getName().equals(name))
	        		{
	        			playerIndex = catanModel.getPlayers()[j].getPlayerIndex();
	        		}
	        	}
	        	
	        	entries.add(new LogEntry(catanModel.getPlayers()[playerIndex].getColor(), catanModel.getLog().getLines().get(i).getMessage()));
	        }
    	}
        
        getView().setEntries(entries);
	}

	@Override
	public void update(Observable obs, Object obj) {
		if (obs instanceof CatanGame) {
			catanModel = ((CatanGame) obs).getModel();
			initFromModel();
		}
	}
	
}

