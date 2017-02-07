package lodz.uni.math.bank.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	BankTest.class, 
	TestClient.class, 
	TestAccount.class, 
	TestTransaction.class 
	})
public class AllTests {

}
