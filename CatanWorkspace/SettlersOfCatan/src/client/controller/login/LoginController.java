package client.controller.login;

import client.CatanLobby;
import client.view.base.*;
import client.view.login.ILoginView;
import client.view.misc.*;

import java.io.*;

import org.apache.log4j.Logger;

/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController
{
	private static final Logger log = Logger.getLogger(LoginController.class.getName());

	private IMessageView messageView;
	private IAction loginAction;
	private CatanLobby catanLobby;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(CatanLobby catanLobby, ILoginView view, IMessageView messageView)
	{
		super(view);
		
		this.messageView = messageView;
		this.catanLobby = catanLobby;
	}
	
	public ILoginView getLoginView()
	{
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView()
	{
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value)
	{
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction()
	{
		
		return loginAction;
	}

	@Override
	public void start()
	{
		log.trace("Showing login modal --\\");
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		String password=this.getLoginView().getLoginPassword();
		String username=this.getLoginView().getLoginUsername();
		
		//Login Here
		try 
		{
			catanLobby.userLogin(username, password);
			getLoginView().closeModal();
			log.trace("Closed login modal --/");
			loginAction.execute();
			//login success
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			messageView.setMessage("Could not login to the server");
			log.trace("Showing message view modal --\\");
			messageView.showModal();
		}

	}

	@Override
	public void register() {
		
		// TODO: register new user (which, if successful, also logs them in)
		
		String username=this.getLoginView().getRegisterUsername();
		String password1=this.getLoginView().getRegisterPassword();
		String password2=this.getLoginView().getRegisterPasswordRepeat();
		
		String usernameError = "The username must be between three and seven characters: letters, digits, _ , and -";
		String passwordError = "The password must be five or more characters: letters, digits, _ , and -";
		String validCharsRegex = "[0-9]*[A-z]*[_]*[-]*";
		
		if(username.length()<3||username.length()>7) {
			//username isn't valid
			messageView.setMessage(usernameError);
			log.trace("Showing message view --\\");
			messageView.showModal();
		}
		else if(!username.matches(validCharsRegex)) {
			messageView.setMessage(usernameError);
			log.trace("Showing message view --\\");
			messageView.showModal();
		}
		else if(!password1.matches(validCharsRegex)) {
			messageView.setMessage(passwordError);
			log.trace("Showing message view --\\");
			messageView.showModal();
		}
		else if(password1.length() < 5) {
			//passwords length has to be greater than 4
			messageView.setMessage(passwordError);
			log.trace("Showing message view --\\");
			messageView.showModal();
		}
		else if (!password1.equals(password2)) {
			//passwords don't match
			messageView.setMessage("Passwords must match.");
			log.trace("Showing message view --\\");
			messageView.showModal();
		}
		else 
		{
			try 
			{
				catanLobby.userRegister(username, password1);
				getLoginView().closeModal();
				log.trace("Closed login modal --/");
				loginAction.execute();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				messageView.setMessage("Could not register the user");
				log.trace("Showing message view --\\");
				messageView.showModal();
			}
		}
	}
}

