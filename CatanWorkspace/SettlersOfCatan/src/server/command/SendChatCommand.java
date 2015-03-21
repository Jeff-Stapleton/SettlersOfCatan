package server.command;

import server.comm.request.MovesSendChatRequest;
import shared.CatanModel;
import shared.MessageLine;
import shared.comm.serialization.SendChatRequest;

public class SendChatCommand implements ICommand<CatanModel>
{
	private int playerIndex;
	private String chat;
	
	public SendChatCommand(SendChatRequest request) 
	{
		this.playerIndex = request.getPlayerIndex();
		this.chat = request.getContent();
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
	public CatanModel execute(CatanModel catanModel) 
	{
		String playerName = catanModel.getPlayers()[playerIndex].getName();
		MessageLine message = new MessageLine(playerName, chat);
		catanModel.getChat().addLine(message);
		
		return catanModel;
	}

}
