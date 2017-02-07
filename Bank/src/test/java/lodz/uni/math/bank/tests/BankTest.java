package lodz.uni.math.bank.tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import lodz.uni.math.bank.pojo.Bank;

public class BankTest {
	private static Bank testBank=new Bank();

	@BeforeClass
	public static void initObjects(){
		testBank=new Bank();
	}
	@Before
	public void reset(){
		testBank=new Bank();
	}
	
	@Test
	public void testgetNewNumberAccount() {
		assertEquals(testBank.getNewNumberAccount(),"000000000000001");
	}
}
