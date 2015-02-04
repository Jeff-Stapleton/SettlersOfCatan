package shared;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

public class Testing2 {
	
	private TurnTracker turn;
	private Map map;
	private ArrayList <Building> settlements;
	private Player player;
	private ArrayList <Port> port;
	
	@Test
	public void setup()
	{
		turn = new TurnTracker();
		turn.setStatus(TurnType.PLAYING);
		turn.setCurrentTurn(5);
		
		map = new Map();
		settlements = new ArrayList<Building>();
		settlements.add(new Building(0, new VertexLocation(new HexLocation(3, 0), VertexDirection.West)));
		map.setSettlements(settlements);
		
		port = new ArrayList<Port>();
		port.add(new Port(PortType.BRICK, new HexLocation(3, 0), EdgeDirection.SouthWest, 3));
		map.setPorts(port);
		
		player = new Player();
		player.setPlayerID(0);
		player.setResources(new ResourceList(5, 5, 5, 5, 5));
		player.setSettlements(3);
		player.setCities(5);
		
	}
	@Test
	public void canMaritimeTrade() 
	{
		assertTrue("Can Maritime Trade", CanCan.canMaritimeTrade(player, turn));
	}

}
