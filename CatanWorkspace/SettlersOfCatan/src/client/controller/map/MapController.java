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
	
	public MapController(CatanGame catanGame, IMapView view, IRobView robView) {
		super(view);
		
		setRobView(robView);
		this.catanGame = catanGame;
		
		//initFromModel();

		playingRoadBuildingCard = false;
		catanGame.addObserver(this);
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
		return CanCan.canBuildRoad(catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()], edgeLoc, catanModel.getTurnTracker(), catanModel.getMap());
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		
		return CanCan.canBuildSettlement(catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()], vertLoc, catanModel.getTurnTracker(), catanModel.getMap());
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		
		return CanCan.canBuildCity(catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()], vertLoc, catanModel.getTurnTracker(), catanModel.getMap());
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
			catanGame.setModel(catanGame.getProxy().movesBuildRoad(catanGame.getPlayerInfo().getPlayerIndex(), edgeLoc, false));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void placeSettlement(VertexLocation vertLoc) 
	{
		try 
		{
			catanGame.setModel(catanGame.getProxy().movesBuildSettlement(catanGame.getPlayerInfo().getPlayerIndex(), vertLoc, false));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public void placeCity(VertexLocation vertLoc) 
	{
		try 
		{
			catanGame.setModel(catanGame.getProxy().movesBuildCity(catanGame.getPlayerInfo().getPlayerIndex(), vertLoc, false));
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
				if(i != catanGame.getPlayerInfo().getPlayerIndex() && CanCan.notTouchingRobber(hexLoc, catanModel.getMap().getRobber(), catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()], catanModel.getMap())){
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
		getView().startDrop(pieceType, catanGame.getPlayerInfo().getColor(), true);
	}
	
	public void cancelMove() 
	{
		
	}
	
	public void playSoldierCard() 
	{
		if (catanModel.getTurnTracker().getCurrentTurn() == catanGame.getPlayerInfo().getPlayerIndex())
		{
			getView().startDrop(PieceType.ROBBER, catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].getColor(), false);
		}
		
	}
	
	public void playRoadBuildingCard() 
	{	
		if (catanModel.getTurnTracker().getCurrentTurn() == catanGame.getPlayerInfo().getPlayerIndex())
		{
			playingRoadBuildingCard = true;
			numRoadsPlaced = 0;
			getView().startDrop(PieceType.ROBBER, catanModel.getPlayers()[catanGame.getPlayerInfo().getPlayerIndex()].getColor(), false);
		}
		
	}
	
	public void robPlayer(RobPlayerInfo victim) 
	{
		try 
		{
			catanGame.setModel(catanGame.getProxy().movesRobPlayer(catanGame.getPlayerInfo().getPlayerIndex(), victim.getId(), new HexLocation(catanModel.getMap().getRobber().getX(), catanModel.getMap().getRobber().getY())));
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable obs, Object obj) 
	{
		if (obs instanceof CatanGame) 
		{
			if (catanModel == null)
			{
				System.out.println("Initializing model");
				catanModel = ((CatanGame) obs).getModel();
				initFromModel();
				updateFromModel();
			}
			else
			{
				System.out.println("Updating model");
				catanModel = ((CatanGame) obs).getModel();
				updateFromModel();
			}
		}
	}
	
}

