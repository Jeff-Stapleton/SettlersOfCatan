package client.controller.login;

import client.CatanGame;
import client.view.base.*;
import client.view.login.ILoginView;
import client.view.misc.*;

import java.net.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	private CatanGame catanGame;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(CatanGame catanGame, ILoginView view,
			IMessageView messageView) {
		super(view);
		
		this.messageView = messageView;
		this.catanGame = catanGame;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		String password=this.getLoginView().getLoginPassword();
		String username=this.getLoginView().getLoginUsername();
		
		//Login Here
		try 
		{
			catanGame.userLogin(username, password);
			getLoginView().closeModal();
			loginAction.execute();
			//login success
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			messageView.setMessage("Could not login to the server");
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
			messageView.showModal();
		}
		else if(!username.matches(validCharsRegex)) {
			messageView.setMessage(usernameError);
			messageView.showModal();
		}
		else if(!password1.matches(validCharsRegex)) {
			messageView.setMessage(passwordError);
			messageView.showModal();
		}
		else if(password1.length() < 5) {
			//passwords length has to be greater than 4
			messageView.setMessage(passwordError);
			messageView.showModal();
		}
		else if (!password1.equals(password2)) {
			//passwords don't match
			messageView.setMessage("Passwords must match.");
			messageView.showModal();
		}
		else 
		{
			try 
			{
				catanGame.userRegister(username, password1);
				getLoginView().closeModal();
				loginAction.execute();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				messageView.setMessage("Could not register the user");
				messageView.showModal();
			}
		}
	}
}

