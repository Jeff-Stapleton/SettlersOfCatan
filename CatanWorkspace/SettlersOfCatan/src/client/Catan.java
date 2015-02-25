package client;

import javax.swing.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import client.comm.IServerProxy;
import client.comm.ServerProxy;
import client.controller.join.JoinGameController;
import client.controller.join.PlayerWaitingController;
import client.controller.login.LoginController;
import client.view.base.IAction;
import client.view.base.OverlayView;
import client.view.catan.CatanPanel;
import client.view.join.JoinGameView;
import client.view.join.NewGameView;
import client.view.join.PlayerWaitingView;
import client.view.join.SelectColorView;
import client.view.login.LoginView;
import client.view.misc.MessageView;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame
{
	
	private CatanPanel catanPanel;
	
	public Catan(IServerProxy serverProxy)
	{
		
		OverlayView.setWindow(this);
		
		this.setTitle("Settlers of Catan");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		catanPanel = new CatanPanel(serverProxy);
		this.setContentPane(catanPanel);
		
		display();
	}
	
	private void display()
	{
		pack();
		setVisible(true);
	}
	
	//
	// Main
	//
	
	public static void main(final String[] args)
	{
		String host = "localhost";
		int port = 8081;
		
		Options options = new Options();
		options.addOption("p", "port", true, "The port to run on");
		options.addOption("h", "host", true, "hostname to connect to");
		
		try {
			CommandLineParser parser = new GnuParser();
			CommandLine cmd = parser.parse( options, args);
			
			String portArg = cmd.getOptionValue("p");
			String hostArg = cmd.getOptionValue("h");
			
			if (portArg != null) {
				port = Integer.valueOf(portArg);
			}
			if (hostArg != null) {
				host = hostArg;
			}
			
		} catch (ParseException e1) {
			System.err.println("Cannot parse the command line options");
			e1.printStackTrace();
			System.exit(1);
		}
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		// Make them final so we can use them in the anonymous inner class
		final String fHost = host;
		final Integer fPort = port;
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				ServerProxy serverProxy = new ServerProxy("http://" + fHost + ":" + fPort);
				CatanGame catanGame = new CatanGame(serverProxy, null);
				
				new Catan(serverProxy);
				
				PlayerWaitingView playerWaitingView = new PlayerWaitingView();
				final PlayerWaitingController playerWaitingController = new PlayerWaitingController(catanGame,
																									playerWaitingView);
				playerWaitingView.setController(playerWaitingController);
				
				JoinGameView joinView = new JoinGameView();
				NewGameView newGameView = new NewGameView();
				SelectColorView selectColorView = new SelectColorView();
				MessageView joinMessageView = new MessageView();
				final JoinGameController joinController = new JoinGameController(catanGame,
																				 joinView,
																				 newGameView,
																				 selectColorView,
																				 joinMessageView);
				joinController.setJoinAction(new IAction() {
					@Override
					public void execute()
					{
						playerWaitingController.start();
					}
				});
				joinView.setController(joinController);
				newGameView.setController(joinController);
				selectColorView.setController(joinController);
				joinMessageView.setController(joinController);
				
				LoginView loginView = new LoginView();
				MessageView loginMessageView = new MessageView();
				LoginController loginController = new LoginController(catanGame,
																	  loginView,
																	  loginMessageView);
				loginController.setLoginAction(new IAction() {
					@Override
					public void execute()
					{
						joinController.start();
					}
				});
				loginView.setController(loginController);
				loginView.setController(loginController);
				
				loginController.start();
			}
		});
	}
	
}

