package client.controller.communication;

import java.util.*;

import client.CatanGame;
import client.view.base.*;
import client.view.communication.IGameHistoryView;
import client.view.communication.LogEntry;
import client.view.data.PlayerInfo;
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
			catanModel = catanGame.getModel();
			
	        List<LogEntry> entries = new ArrayList<LogEntry>();
	        HashMap<String, CatanColor> player_colors = new HashMap<>();
			for (Player player : catanModel.getPlayers())
			{
					player_colors.put(player.getName(), player.getColor());
			}
	        
	        for (MessageLine line : catanGame.getModel().getLog().getLines())
	        {
	        	String user = line.getSource();
	        	CatanColor color = null;
	        	color = player_colors.get(user);
	        	entries.add(new LogEntry(color, line.getMessage()));
	        }
	        getView().setEntries(entries);
			
		}
	}
	
}

