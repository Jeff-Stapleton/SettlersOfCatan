package client.controller.map;

import java.io.IOException;
import java.util.*;

import shared.CanCan;
import shared.CatanModel;
import shared.Hex;
import shared.Map;
import shared.Player;
import shared.Port;
import shared.TurnTracker;
import shared.TurnType;
import shared.definitions.*;
import shared.locations.*;
import client.CatanGame;
import client.comm.IServerProxy;
import client.view.base.*;
import client.view.data.*;
import client.view.map.IMapView;
import client.view.map.IRobView;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private CatanModel catanModel = null;
	private IRobView robView;
	private CatanGame catanGame;
	private boolean playingRoadBuildingCard;
	private int numRoadsPlaced;
	private int playerIndex;
	private CatanColor playerColor;
	private TurnType mapState;
	private Player player;
	private boolean isBuilding;
	
	public MapController(CatanGame catanGame, IMapView view, IRobView robView) 
	{
		super(view);
		
		setRobView(robView);
		this.catanGame = catanGame;
		
		//initFromModel();

		playingRoadBuildingCard = false;
		catanGame.addObserver(this);
		isBuilding = false;
	}
	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	private CatanColor getColor()
	{
		return playerColor;
	}
	
	private void setColor(CatanColor color)
	{
		playerColor = color;
	}
	
	
	
	protected void initFromModel() 
	{
		//initFromModel gets the initCatanModel, adds the hexes, numbers, and ports
		for (int i = 0; i < catanModel.getMap().getHexes().length; i++)
		{
			Hex hex = catanModel.getMap().getHexes()[i];
			if (catanModel.getMap().getHexes()[i].getResource() == null)
			{
				// desert
				getView().addHex(hex.getLocation(), HexType.DESERT);
				getView().placeRobber(hex.getLocation());
			}
			else
			{
				// not desert
				getView().addHex(hex.getLocation(), hex.getResource());
				getView().addNumber(hex.getLocation(), hex.getNumber());
			}
			getView().addHex(new HexLocation(-3, 3), HexType.WATER);
			getView().addHex(new HexLocation(-3, 2), HexType.WATER);
			getView().addHex(new HexLocation(-3, 1), HexType.WATER);
			getView().addHex(new HexLocation(-3, 0), HexType.WATER);
			getView().addHex(new HexLocation(-2, -1), HexType.WATER);
			getView().addHex(new HexLocation(-2, 3), HexType.WATER);
			getView().addHex(new HexLocation(-1, 3), HexType.WATER);
			getView().addHex(new HexLocation(-1, -2), HexType.WATER);
			getView().addHex(new HexLocation(0, 3), HexType.WATER);
			getView().addHex(new HexLocation(0, -3), HexType.WATER);
			getView().addHex(new HexLocation(1, -3), HexType.WATER);
			getView().addHex(new HexLocation(1, 2), HexType.WATER);
			getView().addHex(new HexLocation(2, 1), HexType.WATER);
			getView().addHex(new HexLocation(2, -3), HexType.WATER);
			getView().addHex(new HexLocation(3, -3), HexType.WATER);
			getView().addHex(new HexLocation(3, -2), HexType.WATER);
			getView().addHex(new HexLocation(3, -1), HexType.WATER);
			getView().addHex(new HexLocation(3, 0), HexType.WATER);
		}
		
		for (int i = 0; i < catanModel.getMap().getPorts().size(); i++)
		{
			Port port = catanModel.getMap().getPorts().get(i);
			if (port.getType() == null) {
				// Three port
				getView().addPort(new EdgeLocation(port.getLocation().getX(), port.getLocation().getY(), port.getDirection()), PortType.THREE);
			}
			else
			{
				// Two port
				getView().addPort(new EdgeLocation(port.getLocation().getX(), port.getLocation().getY(), port.getDirection()), port.getType());
			}
		}

	}
	
	protected void updateFromModel()
	{

		
		for (int i = 0; i < catanModel.getMap().getRoads().size(); i++)
			for (int j = 0; j < catanModel.getPlayers().length; j++)
				if (catanModel.getPlayers()[j].getPlayerID() == catanModel.getMap().getRoads().get(i).getOwner())
					getView().placeRoad(new EdgeLocation(catanModel.getMap().getRoads().get(i).getLocation().getX(), catanModel.getMap().getRoads().get(i).getLocation().getY(), catanModel.getMap().getRoads().get(i).getLocation().getDir()), catanModel.getPlayers()[j].getColor());
		
		for (int i = 0; i < catanModel.getMap().getSettlements().size(); i++)
			for (int j = 0; j < catanModel.getPlayers().length; j++)
				if (catanModel.getPlayers()[j].getPlayerID() == catanModel.getMap().getSettlements().get(i).getOwner())
					getView().placeSettlement(catanModel.getMap().getSettlements().get(i).getLocation(), catanModel.getPlayers()[j].getColor());
		
		for (int i = 0; i < catanModel.getMap().getCities().size(); i++)
			for (int j = 0; j < catanModel.getPlayers().length; j++)
				if (catanModel.getPlayers()[j].getPlayerID() == catanModel.getMap().getCities().get(i).getOwner())
					getView().placeCity(catanModel.getMap().getCities().get(i).getLocation(), catanModel.getPlayers()[j].getColor());
		
		getView().placeRobber(new HexLocation(catanModel.getMap().getRobber().getX(), catanModel.getMap().getRobber().getY()));
			
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) 
	{
		return CanCan.canBuildRoad(player, edgeLoc, catanModel.getTurnTracker(), catanModel.getMap());
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		
		return CanCan.canBuildSettlement(player, vertLoc, catanModel.getTurnTracker(), catanModel.getMap());
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		
		return CanCan.canBuildCity(player, vertLoc, catanModel.getTurnTracker(), catanModel.getMap());
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		Hex robberHex = null;
		
		for (int i = 0; i < catanModel.getMap().getHexes().length; i++)
			if (hexLoc.getX() == catanModel.getMap().getHexes()[i].getLocation().getX() && hexLoc.getY() == catanModel.getMap().getHexes()[i].getLocation().getY())
				robberHex = catanModel.getMap().getHexes()[i];
		
		return CanCan.canPlaceRobber(robberHex, catanModel.getMap().getRobber(), catanModel.getTurnTracker());
	}

	public void placeRoad(EdgeLocation edgeLoc) 
	{
		try 
		{
			catanGame.setModel(catanGame.getProxy().movesBuildRoad(playerIndex, edgeLoc, false));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void placeSettlement(VertexLocation vertLoc) 
	{
		try 
		{
			catanGame.setModel(catanGame.getProxy().movesBuildSettlement(playerIndex, vertLoc, false));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void placeCity(VertexLocation vertLoc) 
	{
		try 
		{
			catanGame.setModel(catanGame.getProxy().movesBuildCity(playerIndex, vertLoc, false));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void placeRobber(HexLocation hexLoc) 
	{
		if (canPlaceRobber(hexLoc)) {
			RobPlayerInfo[] candidateVictims = new RobPlayerInfo[3];
			for(int i = 0; i < 4; i++){
				int infoArrayIndex = 0;
				if(i != playerIndex && CanCan.notTouchingRobber(hexLoc, catanModel.getMap().getRobber(), player, catanModel.getMap()))
				{
					RobPlayerInfo robPlayerInfo = new RobPlayerInfo();
					robPlayerInfo.setPlayerIndex(i);
					robPlayerInfo.setColor(catanModel.getPlayers()[i].getColor());
					robPlayerInfo.setName(catanModel.getPlayers()[i].getName());
					robPlayerInfo.setNumCards(catanModel.getPlayers()[i].getResources().totalCount());
					robPlayerInfo.setId(catanModel.getPlayers()[i].getPlayerID());
					candidateVictims[infoArrayIndex] = robPlayerInfo;
					infoArrayIndex += 1;
				}
			}
			getRobView().setPlayers(candidateVictims);
			getView().placeRobber(hexLoc);
			getRobView().showModal();
		}
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) 
	{	
		getView().startDrop(pieceType, getColor(), allowDisconnected);
	}
	
	public void cancelMove() 
	{
		
	}
	
	public void playSoldierCard() 
	{
		if (catanModel.getTurnTracker().getCurrentTurn() == playerIndex)
		{
			getView().startDrop(PieceType.ROBBER, playerColor, false);
		}
		
	}
	
	public void playRoadBuildingCard() 
	{	
		if (catanModel.getTurnTracker().getCurrentTurn() == playerIndex)
		{
			playingRoadBuildingCard = true;
			numRoadsPlaced = 0;
			getView().startDrop(PieceType.ROBBER, playerColor, false);
		}
		
	}
	
	public void robPlayer(RobPlayerInfo victim) 
	{
		try 
		{
			catanGame.setModel(catanGame.getProxy().movesRobPlayer(playerIndex, victim.getId(), new HexLocation(catanModel.getMap().getRobber().getX(), catanModel.getMap().getRobber().getY())));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void updateState()
	{
		switch(catanModel.getTurnTracker().getStatus()){
		case FIRST_ROUND:
			mapState = TurnType.FIRST_ROUND;
			break;
		case SECOND_ROUND:
			mapState = TurnType.SECOND_ROUND;
			break;
		case ROLLING:
			mapState = TurnType.ROLLING;
			break;
		case ROBBING:
			mapState = TurnType.ROBBING;
			break;
		case PLAYING:
			mapState = TurnType.PLAYING;
			break;
		case DISCARDING:
			mapState = TurnType.DISCARDING;
			break;
		default:
			System.out.println("Somthing has gone terribly, horribly wrong in Update() in mapController.java");
			break;
		}		
	}

	@Override
	public void update(Observable obs, Object obj) 
	{
		if (isBuilding == false)
		{
			if (obs instanceof CatanGame) 
			{
				if (catanModel == null)
				{
					System.out.println("Initializing model");
					catanModel = ((CatanGame) obs).getModel();
					initFromModel();
				}
				else 
				{
					setColor(catanModel.getPlayers()[playerIndex].getColor());
					playerIndex = catanGame.getPlayerInfo().getPlayerIndex();
					player = catanModel.getPlayers()[playerIndex];
					
					System.out.println("Updating model");
					catanModel = ((CatanGame) obs).getModel();
					updateState();
					updateFromModel();
					
					
					//THE SUPER TERRIBLE CHECKING SYSTEM FOR THE FIRST ROUND RIGHT NOW
					
					if (player != null && playerIndex == catanModel.getTurnTracker().getCurrentTurn())
					{
						
						if (player.getRoads() == 15 && player.getSettlements() == 5 && !catanModel.getMap().getIsBuilding()) 
						{ 
							catanModel.getMap().setIsBuilding(true);
							startMove(PieceType.ROAD, true, true);
						}
						else if (player.getRoads() == 14 && player.getSettlements() == 5 && !catanModel.getMap().getIsBuilding()) 
						{
							catanModel.getMap().setIsBuilding(true);
							startMove(PieceType.SETTLEMENT, true, true);
						}
						else if (player.getRoads() == 14 && player.getSettlements() == 4 && mapState.equals(TurnType.SECOND_ROUND) && !catanModel.getMap().getIsBuilding()) 
						{ 
							catanModel.getMap().setIsBuilding(true);
							startMove(PieceType.ROAD, true, true);
						}
						else if (player.getRoads() == 13 && player.getSettlements() == 4 && mapState.equals(TurnType.SECOND_ROUND) && !catanModel.getMap().getIsBuilding()) 
						{ 
							catanModel.getMap().setIsBuilding(true);
							startMove(PieceType.SETTLEMENT, true, true);
						}
					}
					
					if (player.getRoads() == 14 && player.getSettlements() == 5)
						catanModel.getMap().setIsBuilding(false);
					
				}
			}
		}
	}
	
}

