package client;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import client.comm.Testing;

@RunWith(Suite.class)
@SuiteClasses({
	Testing.class
})

public class ClientTestSuite {

}
