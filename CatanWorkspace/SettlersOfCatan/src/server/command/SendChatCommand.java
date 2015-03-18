package server.command;

import shared.CatanModel;

public class SendChatCommand implements ICommand<CatanModel>{

	/**
	 * Executes "Send Chat", send the instigator's message to be broadcast to all other players
	 *
	 * @pre player is logged in and joined a game
	 * @post completes the send chat
	 * 
	 * @param a PlayerIndex a Message
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
//		catanModel.getChat().addLine(newLine);
		return catanModel;
	}

}
