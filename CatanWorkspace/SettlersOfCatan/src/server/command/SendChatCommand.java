package server.command;

import server.comm.request.MovesSendChatRequest;
import shared.CatanModel;
import shared.comm.serialization.SendChatRequest;

public class SendChatCommand implements ICommand<CatanModel>{

	public SendChatCommand(SendChatRequest request) {
		// TODO Auto-generated constructor stub
	}

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
