package server;

import shared.CatanModel;
import client.view.data.GameInfo;

/**
 * The Class ServerGame.
 */
public class ServerGame
{
	GameInfo info = null;
	CatanModel model = null;
	
	public GameInfo getInfo()
	{
		return info;
	}
	
	public CatanModel getModel()
	{
		return model;
	}

}
