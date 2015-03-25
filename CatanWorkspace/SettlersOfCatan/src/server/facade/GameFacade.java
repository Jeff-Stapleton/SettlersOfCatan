package server.facade;

import shared.CatanModel;
import client.CatanGame;

/**
 * The Class GameFacade implements the model, reset, commands, addAI, listAI commands
 */
public class GameFacade {
	
	/** The catan game. */
	private CatanGame catanGame;
	private CatanModel catanModel;
	
	public GameFacade(CatanModel model){
		catanModel = model;
	}
	
	/**
	 * returns the model
	 */
	public void model(){
		
	}
	
	/**
	 * resets the game
	 */
	public void reset(){
		catanModel.reset();
	}
	
	/**
	 * posts the command 
	 */
	public void postCommands(){
		
	}
	
	/**
	 * gets a command
	 */
	public void getCommands(){
		
	}
}
