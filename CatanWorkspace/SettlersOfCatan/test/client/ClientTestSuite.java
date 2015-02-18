package client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import client.comm.Testing;
import client.comm.Testing2;

@RunWith(Suite.class)
@SuiteClasses({
	Testing.class,
	Testing2.class
})

public class ClientTestSuite {

}
