package server.command;

import shared.CatanModel;
import shared.Player;
import shared.comm.serialization.MonumentRequest;
import shared.comm.serialization.SaveGameRequest;

public class SaveGameCommand implements ICommand<CatanModel>
{

	private String fileLocation;
	
	public SaveGameCommand(SaveGameRequest request) 
	{
		this.fileLocation = request.getName();
	}

	/**
	 * Executes "SaveGame", Saves current game out to file
	 *
	 * @pre Game exists
	 * @post completes the save
	 * 
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) 
	{
//		Player player = catanModel.getPlayers()[owner];
//		
//		player.setVictoryPoints(player.getVictoryPoints()+1);
//		player.getOldDevCards().setMonument(player.getOldDevCards().getMonument() - 1);
//
//		MapChecks.checkForWinner(catanModel, owner);
		
		return catanModel;
	}

}
