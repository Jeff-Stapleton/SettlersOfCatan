package client.view.catan;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;

import shared.definitions.ResourceType;
import client.CatanGame;
import client.controller.discard.DiscardController;
import client.controller.roll.RollController;
import client.view.discard.DiscardView;
import client.view.misc.WaitView;
import client.view.roll.RollResultView;
import client.view.roll.RollView;

@SuppressWarnings("serial")
public class CatanPanel extends JPanel
{
	private final static Logger log = Logger.getLogger(CatanPanel.class.getName());
	
	private TitlePanel titlePanel;
	private LeftPanel leftPanel;
	private MidPanel midPanel;
	private RightPanel rightPanel;
	
	private DiscardView discardView;
	private WaitView discardWaitView;
	private DiscardController discardController;
	
	private RollView rollView;
	private RollResultView rollResultView;
	private RollController rollController;
	
	public CatanPanel(CatanGame catanGame)
	{
		log.finest("Creating CatanPanel");
		
		this.setLayout(new BorderLayout());
		
		titlePanel = new TitlePanel();
		midPanel = new MidPanel(catanGame);
		leftPanel = new LeftPanel(titlePanel, midPanel.getGameStatePanel(), catanGame);
		rightPanel = new RightPanel(midPanel.getMapController(), catanGame);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
		
		discardView = new DiscardView();
		discardWaitView = new WaitView();
		discardWaitView.setMessage("Waiting for other Players to Discard");
		discardController = new DiscardController(catanGame, discardView, discardWaitView);
		discardView.setController(discardController);
		discardWaitView.setController(discardController);
		
		rollView = new RollView();
		rollResultView = new RollResultView();
		rollController = new RollController(catanGame, rollView, rollResultView);
		rollView.setController(rollController);
		rollResultView.setController(rollController);
		
		JButton testButton = new JButton("Test");
		testButton.addActionListener(new ActionListener() {
			
//			 @Override
//			 public void actionPerformed(ActionEvent e) {
//			
//			 new client.points.GameFinishedView().showModal();
//			 }
//			
//			 @Override
//			 public void actionPerformed(ActionEvent e) {
//			
//			 rollView.showModal();
//			 }
//			
//			 @Override
//			 public void actionPerformed(java.awt.event.ActionEvent
//			 e) {
//			
//			 midPanel.getMapController().startMove(PieceType.ROBBER,
//			 false, false);
//			 }
			
			int state = 0;
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
//				rollView.showModal();
				
				discardView.setResourceMaxAmount(ResourceType.WOOD, 1);
				discardView.setResourceMaxAmount(ResourceType.BRICK, 0);
				discardView.setResourceMaxAmount(ResourceType.SHEEP, 11);
				discardView.setResourceMaxAmount(ResourceType.WHEAT, 1);
				discardView.setResourceMaxAmount(ResourceType.ORE, 0);
				
				discardView.setResourceAmountChangeEnabled(ResourceType.WOOD, true, false);
				discardView.setResourceAmountChangeEnabled(ResourceType.SHEEP, true, false);
				discardView.setResourceAmountChangeEnabled(ResourceType.WHEAT, true, false);
				
				discardView.setStateMessage("0/6");
				
				discardView.setDiscardButtonEnabled(true);
				
				if(state == 0)
				{
					discardView.showModal();
					state = 1;
				}
				else if(state == 1)
				{
					discardWaitView.showModal();
					state = 2;
				}
			}
		});
		this.add(testButton, BorderLayout.SOUTH);
		
		log.finest("CatanPanel created");
	}
	
}

