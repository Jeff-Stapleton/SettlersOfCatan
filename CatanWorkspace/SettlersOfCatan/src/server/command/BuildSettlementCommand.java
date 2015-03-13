package server.command;

import shared.CatanModel;

public class BuildSettlementCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Build Settlement", subtracts the resources from the instigator
	 * subtracts a city from unbuilt settlement pool and places it on a vertex location
	 *
	 * @pre The player has enough resources, enough settlements and is a valid vertex location
	 * @post completes the build settlement
	 * 
	 * @param a ResourceList
	 */
	@Override
	public CatanModel execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
