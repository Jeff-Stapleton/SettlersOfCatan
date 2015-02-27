package client.controller.communication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import shared.CatanModel;
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
	private int localIndex;
    private int playerIndex;
	
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
    		catanGame.getProxy().movesSendChat(catanGame.getPlayerInfo().getPlayerIndex(), message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update(Observable obs, Object obj) 
	{
		if (obs instanceof CatanGame) 
		{
			catanModel = ((CatanGame) obs).getModel();
			
	        List<LogEntry> entries = new ArrayList<LogEntry>();

	    	if (catanModel != null) {
	    		if (catanGame.getPlayerInfo() != null) {
		        	localIndex = catanGame.getPlayerInfo().getPlayerIndex();
	    		}
		        for (int i = 0; i < catanModel.getChat().getLines().size(); i++) 
		        {
		        	String name = catanModel.getChat().getLines().get(i).getSource();
		        	for (int j = 0; j < 4; j++)
		        	{
		        		if (catanModel.getPlayers()[j].getName().equals(name))
		        		{
		        			playerIndex = catanModel.getPlayers()[j].getPlayerIndex();
		        		}
		        	}
		        	
		        	entries.add(new LogEntry(catanModel.getPlayers()[playerIndex].getColor(), catanModel.getChat().getLines().get(i).getMessage()));
		        }
	    	}
	        
	        //getView().setEntries(entries);
			
		}
	}
	

}

