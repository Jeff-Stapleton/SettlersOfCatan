package shared;

import comm.client.*;

public class CatanGame {
	
	/** The log. */
	private CatanModel model;
	
	/**
	 * Instantiates a new Catan Game.
	 */
	private CatanGame() 
	{
		model = new CatanModel();
	}
	
	public CatanModel getModel() 
	{
		return model;
	}

	public void setModel(CatanModel model) 
	{
		this.model = model;
	}	
}
