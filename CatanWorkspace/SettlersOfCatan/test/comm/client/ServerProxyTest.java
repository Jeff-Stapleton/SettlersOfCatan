package comm.client;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import comm.shared.ServerException;

public class ServerProxyTest
{
	
	ServerProxy proxy;

	@Before
	public void setUp() throws Exception
	{
		proxy = new ServerProxy("http://localhost:8081");
	}

	@After
	public void tearDown() throws Exception
	{
		
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testUserRegister() throws IOException
	{
		proxy.userRegister("cory", "cory");

	    exception.expect(IOException.class);
		proxy.userRegister("cory", "cory");
	}
	
	@Test
	public void testUserLogin() throws IOException
	{
		proxy.userLogin("cory", "cory");

	    exception.expect(IOException.class);
		proxy.userLogin("fakeman", "jake");
	}

}
