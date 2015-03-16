package server.command;

import shared.CatanModel;

public class BuildRoadCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Build Road", subtracts the resources from the instigator
	 * subtracts a road from unbuilt road pool and places it on a edge location
	 *
	 * @pre The player has enough resources, enough roads and is a valid edge location
	 * @post completes the build road
	 * 
	 * @param a PlayerIndex, a Location, a isFreeBoolean
	 */
	@Override
	public CatanModel execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
