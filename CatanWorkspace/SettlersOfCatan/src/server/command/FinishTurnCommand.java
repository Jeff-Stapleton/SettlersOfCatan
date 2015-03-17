package server.command;

import shared.CatanModel;

public class FinishTurnCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Finish Turn", ends the players turn and allows the next player to 
	 * perform his or her turn.
	 *
	 * @pre Its is indeed the players turn and no other actions ie "trading" are currently pending
	 * @post completes the finish turn
	 * 
	 * @param a PlayerIndex
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
