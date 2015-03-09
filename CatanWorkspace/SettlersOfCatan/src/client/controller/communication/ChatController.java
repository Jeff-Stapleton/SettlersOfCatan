package client.controller.communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.CatanModel;
import shared.MessageLine;
import shared.definitions.CatanColor;
import client.CatanGame;
import client.view.base.*;
import client.view.communication.IChatView;
import client.view.communication.LogEntry;

/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, Observer {

	private CatanModel catanModel;
	private CatanGame catanGame;
	
	public ChatController(CatanGame catanGame, IChatView view) {
		
		super(view);
		this.catanGame = catanGame;
		catanGame.addObserver(this);
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) 
	{
    	try {
    		catanGame.updateModel(catanGame.getProxy().movesSendChat(catanGame.getPlayerInfo().getPlayerIndex(), message));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
	}
	
	public void updateFromModel()
	{
		List<LogEntry> entries = new ArrayList<LogEntry>();

        for (MessageLine line : catanModel.getChat().getLines())
        {
        	System.out.println(line.toString());
        	String user = line.getSource();
        	CatanColor color = null;
        	// catanGame.getGameInfo().getPlayerWithName(user) is not returning the correct player. I suspect this is not ever getting updated or something...
        	color = catanGame.getGameInfo().getPlayerWithName(user).getColor();
//			System.out.println("ChatColor: " + color);
        	entries.add(new LogEntry(color, line.getMessage()));
        }
        
        getView().setEntries(entries);
	}
	
	
	@Override
	public void update(Observable obs, Object obj) 
	{
		if (obs instanceof CatanGame) 
		{
			catanModel = catanGame.getModel();
			
	        updateFromModel();
		}
	}
	

}

