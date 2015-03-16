package client.view.catan;

import java.awt.*;
import javax.swing.*;

import client.CatanGame;
import client.comm.IServerProxy;
import client.controller.map.IMapController;
import client.controller.map.MapController;
import client.view.map.*;

@SuppressWarnings("serial")
public class MidPanel extends JPanel
{
	
	private TradePanel tradePanel;
	private MapView mapView;
	private RobView robView;
	private MapController mapController;
	private GameStatePanel gameStatePanel;
	
	public MidPanel(CatanGame catanGame)
	{
		
		this.setLayout(new BorderLayout());
		
		tradePanel = new TradePanel(catanGame);
		
		mapView = new MapView();
		robView = new RobView();
		mapController = new MapController(catanGame, mapView, robView);
		mapView.setController(mapController);
		robView.setController(mapController);
		
		gameStatePanel = new GameStatePanel();
		
		this.add(tradePanel, BorderLayout.NORTH);
		this.add(mapView, BorderLayout.CENTER);
		this.add(gameStatePanel, BorderLayout.SOUTH);
		
		this.setPreferredSize(new Dimension(800, 700));
	}
	
	public GameStatePanel getGameStatePanel()
	{
		return gameStatePanel;
	}
	
	public IMapController getMapController()
	{
		return mapController;
	}
	
}

