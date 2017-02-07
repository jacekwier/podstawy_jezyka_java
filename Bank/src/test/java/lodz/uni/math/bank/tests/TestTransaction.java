package lodz.uni.math.bank.tests;

import static org.junit.Assert.*;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import lodz.uni.math.bank.pojo.Account;
import lodz.uni.math.bank.transactions.Transaction;
import lodz.uni.math.bank.transactions.WireOut;

public class TestTransaction {
	private static Account account;
	private static Transaction transaction;
	
	@Before
	public void before(){
		account=new Account(0, "000000000000000", "Konto1");
		transaction=new WireOut("123456789012345", new BigDecimal("0"), "Pierwszy przelew",0,"Sweden", "123456789012345", account);
	}

	@Test
	public void testGetDescriptionToHistory() {
		assertEquals(transaction.getDescriptionToHistory().split("\n")[0],"Wire-Out:");
	}

}
