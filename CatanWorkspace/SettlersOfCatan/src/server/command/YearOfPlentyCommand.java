package server.command;

import org.apache.log4j.Logger;

import shared.CatanModel;
import shared.Player;
import shared.ResourceList;
import shared.comm.serialization.YearOfPlentyRequest;
import shared.definitions.ResourceType;

public class YearOfPlentyCommand implements ICommand<CatanModel>
{
	private static final Logger log = Logger.getLogger(YearOfPlentyCommand.class);
	
	private YearOfPlentyRequest request;
	
	public YearOfPlentyCommand(YearOfPlentyRequest request) {
		this.request = request;
	}

	/**
	 * Executes "Year of Plenty Dev Card", removes the dev card from the players hand.  Awards 
	 * the player any two resources of his/her choosing
	 *
	 * @pre The player does in deed have the card and it the correct turn/state
	 * @post completes the play year of plenty card
	 * 
	 * @param a PlayerIndex, a ResourceOne, a ResourceTwo
	 */
	@Override
	public CatanModel execute(CatanModel catanModel) {
		
		Player player = catanModel.getPlayers()[request.getPlayerIndex()];
		ResourceList inventory = player.getResources();
		log.trace("P b4: " +inventory.toString());
		ResourceList bank = catanModel.getBank();
		log.trace("b b4: " + bank.toString());
		ResourceType resourceOne = ResourceType.fromString(request.getResource1());
		ResourceType resourceTwo = ResourceType.fromString(request.getResource2());

		inventory.setResource(resourceOne, inventory.getResource(resourceOne) + 1);
		inventory.setResource(resourceTwo, inventory.getResource(resourceTwo) + 1);

		bank.setResource(resourceOne, bank.getResource(resourceOne) - 1);
		bank.setResource(resourceTwo, bank.getResource(resourceTwo) - 1);
		
		player.getOldDevCards().setYearOfPlenty(player.getOldDevCards().getYearOfPlenty() - 1);
		player.setPlayedDevCard(true);
		
		log.trace("p after: " + inventory.toString());
		log.trace("b after: " + bank.toString());
		
		return catanModel;
	}

}
