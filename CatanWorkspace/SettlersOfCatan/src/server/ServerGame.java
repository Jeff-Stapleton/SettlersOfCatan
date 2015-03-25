package server;

import java.util.ArrayList;

import server.facade.MovesFacade;
import server.models.ServerUser;
import shared.CatanModel;
import shared.Player;
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
	
	public ServerGame(int gameNumber, String name, boolean randomTiles,	boolean randomNumbers, boolean randomPorts)
	{
		info = new GameInfo(gameNumber, name, new ArrayList<PlayerInfo>());
		model = new CatanModel();
		movesFacade = new MovesFacade(model);
	}

	public GameInfo getInfo()
	{
		return info;
	}
	
	public CatanModel getModel()
	{
		return model;
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

}
