package client.view.misc;

import client.view.base.*;

/**
 * Interface for the wait view, which is used to display wait dialogs to the
 * user
 */
public interface IWaitView extends IOverlayView
{
	
	/**
	 * Sets the message displayed by the wait view.
	 * 
	 * @param message
	 *            The message to be displayed
	 */
	void setMessage(String message);
}

