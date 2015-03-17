package server.command;

import shared.CatanModel;

public class AcceptTradeCommand implements ICommand<CatanModel>{

	/**
	 * Executes the "Accept Trade", exchanges the resources between the instigator and
	 * the investigator
	 *
	 * @pre is a valid trade offer
	 * @post completes the domestic trade
	 * 
	 * @param a PlayerIndex, a willAcceptBoolean
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
