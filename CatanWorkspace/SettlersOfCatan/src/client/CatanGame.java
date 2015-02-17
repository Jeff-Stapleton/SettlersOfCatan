package client;

import java.util.Observable;

import client.comm.ServerProxy;
import shared.CatanModel;

public class CatanGame extends Observable {
	
	/** The log. */
	private ServerProxy server;
	private CatanModel model;
	
	/**
	 * Instantiates a new Catan Game.
	 */
	public CatanGame(ServerProxy gameServer, CatanModel initialModel) 
	{
		server = gameServer;
		model = initialModel;
	}
	
	public CatanModel getModel() 
	{
		return model;
	}

	public void setModel(CatanModel model) 
	{
		this.model = model;
		setChanged();
		notifyObservers();
	}	
}
