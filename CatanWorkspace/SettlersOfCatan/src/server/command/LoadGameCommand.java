package server.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import shared.CatanModel;
import shared.Player;
import shared.comm.serialization.LoadGameRequest;
import shared.comm.serialization.MonumentRequest;
import shared.comm.serialization.SaveGameRequest;

public class LoadGameCommand implements ICommand<CatanModel>
{

	private String fileLocation;
	
	public LoadGameCommand(LoadGameRequest request) 
	{
		this.fileLocation = request.getName();
	}

	/**
	 * Executes "LoadGame", Loads game from file
	 * @throws FileNotFoundException 
	 *
	 * @pre file exists
	 * @post game is now in the state that mirrors the file
	 * 
	 */
	@Override
	public CatanModel execute(CatanModel catanModel)
	{

		
		return catanModel;
	}

}
