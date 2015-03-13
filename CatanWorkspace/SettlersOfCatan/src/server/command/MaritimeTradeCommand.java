package server.command;

import shared.CatanModel;

public class MaritimeTradeCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Maritime Trade", subtracts the given resources from the instigator
	 * and add the get resources to the instigator. 
	 *
	 * @pre Bank and player have enough resources, players is built on a port and it correct turn/state
	 * @post completes the maritime trade
	 * 
	 * @param a PlayerIndex, a Ratio, a GetResource, a GiveResource
	 */
	@Override
	public CatanModel execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
