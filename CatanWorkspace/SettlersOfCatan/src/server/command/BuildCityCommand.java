package server.command;

import shared.CatanModel;

public class BuildCityCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Build City", subtracts the resources from the instigator
	 * subtracts a city from unbuilt city pool and places it on a vertex location
	 *
	 * @pre The player has enough resources, enough cities and is a valid vertex location
	 * @post completes the build city
	 * 
	 * @param a ResourceList
	 */
	@Override
	public CatanModel execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
