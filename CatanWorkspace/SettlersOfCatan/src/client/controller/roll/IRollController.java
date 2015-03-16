package client.controller.roll;

import client.view.base.*;

/**
 * Interface for the roll controller
 */
public interface IRollController extends IController
{
	
	/**
	 * Called when the user clicks the "Roll!" button in the roll view
	 */
	void rollDice();
	
	/**
	 * 
	 */
	void sendRoll(int number);
	
}

