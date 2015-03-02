package client.controller.discard;

import shared.Player;
import shared.ResourceList;
import shared.definitions.*;
import client.CatanGame;
import client.view.base.*;
import client.view.discard.IDiscardView;
import client.view.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController {

	private IWaitView waitView;
	private ResourceList discard;
	private CatanGame catanGame;
	private Player players[];
	private int player;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView,  CatanGame catanGame) {
		
		super(view);
		
		this.catanGame = catanGame;
		this.waitView = waitView;
		
		//player = catanGame.getPlayerInfo().getPlayerIndex();
		//players = catanGame.getModel().getPlayers();
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				discard.setWood(discard.getWood() + 1);
				handleEnablingResources(resource);
				break;
			}
			case BRICK:
			{
				discard.setBrick(discard.getBrick() + 1);
				handleEnablingResources(resource);
				break;
			}
			case SHEEP:
			{
				discard.setSheep(discard.getSheep() + 1);
				handleEnablingResources(resource);
				break;
			}
			case WHEAT:
			{
				discard.setWheat(discard.getWheat() + 1);
				handleEnablingResources(resource);
				break;
			}
			case ORE:
			{
				discard.setOre(discard.getOre() + 1);
				handleEnablingResources(resource);
				break;
			}
		}
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		switch(resource) {
			case WOOD:
			{
				discard.setWood(discard.getWood() - 1);
				handleEnablingResources(resource);
				break;
			}
			case BRICK:
			{
				discard.setBrick(discard.getBrick() - 1);
				handleEnablingResources(resource);
				break;
			}
			case SHEEP:
			{
				discard.setSheep(discard.getSheep() - 1);
				handleEnablingResources(resource);
				break;
			}
			case WHEAT:
			{
				discard.setWheat(discard.getWheat() - 1);
				handleEnablingResources(resource);
				break;
			}
			case ORE:
			{
				discard.setOre(discard.getOre() - 1);
				handleEnablingResources(resource);
				break;
			}
		}
	}
	
	public void handleEnablingResources(ResourceType resource)
	{
		switch (resource)
		{
			case WOOD:
			{
				if (discard.getWood() < players[player].getResources().getWood())
				{
					if (discard.getWood() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
					}
				}
				else
				{
					if (discard.getWood() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WOOD, false, false);
					}
				}
			}
			case BRICK:
			{
				if (discard.getBrick() < players[player].getResources().getBrick())
				{
					if (discard.getBrick() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, true, false);
					}
				}
				else
				{
					if (discard.getBrick() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.BRICK, false, false);
					}
				}
			}
			case SHEEP:
			{
				if (discard.getSheep() < players[player].getResources().getSheep())
				{
					if (discard.getSheep() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
					}
				}
				else
				{
					if (discard.getSheep() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.SHEEP, false, false);
					}
				}
			}
			case WHEAT:
			{
				if (discard.getWheat() < players[player].getResources().getWheat())
				{
					if (discard.getWheat() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
					}
				}
				else
				{
					if (discard.getWheat() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.WHEAT, false, false);
					}
				}
			}
			case ORE:
			{
				if (discard.getOre() < players[player].getResources().getOre())
				{
					if (discard.getOre() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, true, false);
					}
				}
				else
				{
					if (discard.getOre() > 0)
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, true);
					}
					else
					{
						getDiscardView().setResourceAmountChangeEnabled(ResourceType.ORE, false, false);
					}
				}
			}
		}
	}

	@Override
	public void discard() {
		
		getDiscardView().closeModal();
	}

}

