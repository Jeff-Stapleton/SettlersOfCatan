package server;

import java.util.ArrayList;

import server.facade.GameFacade;
import server.facade.MovesFacade;
import server.models.ServerUser;
import shared.CatanModel;
import shared.Player;
import shared.comm.serialization.AbstractMovesRequest;
import shared.definitions.CatanColor;
import client.view.data.GameInfo;
import client.view.data.PlayerInfo;

/**
 * The Class ServerGame.
 */
public class ServerGame
{
	GameInfo info = null;
	CatanModel model = null;
	
	MovesFacade movesFacade = null;
	GameFacade gameFacade = null;
	
	ArrayList<AbstractMovesRequest> commandList = new ArrayList<AbstractMovesRequest>();
	
	public ServerGame(int gameNumber, String name, boolean randomTiles,	boolean randomNumbers, boolean randomPorts)
	{
		info = new GameInfo(gameNumber, name, new ArrayList<PlayerInfo>());
		model = new CatanModel(randomTiles,	randomNumbers, randomPorts);
		movesFacade = new MovesFacade(this);
		gameFacade = new GameFacade(model);
	}

	public GameInfo getInfo()
	{
		return info;
	}
	
	public CatanModel getModel()
	{
		return model;
	}
	
	public ArrayList<AbstractMovesRequest> getCommandList()
	{
		return commandList;
	}

	public void addPlayer(ServerUser user, CatanColor color)
	{
		int index = 0;
		for (;index < info.getPlayers().size(); index++)
		{
			if (info.getPlayerWithIndex(index) == null)
			{
				break;
			}
		}
		info.addPlayer(new PlayerInfo(user.getID(), index, user.getUsername(), color));
		
		model.getPlayers()[index] = new Player(user.getID(), index, user.getUsername(), color);
	}
	
	public MovesFacade getMovesFacade()
	{
		return movesFacade;
	}
	
	public GameFacade getGameFacade(){
		return gameFacade;
	}
}
