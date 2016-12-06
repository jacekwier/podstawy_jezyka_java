package lodz.uni.math.Bank2;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class BankTest {
	static Bank bank=new Bank();

	@Test
	public void testGetLastNumberAccount() {
		BigInteger big=Bank.getLastNumberAccount();
		assertEquals(new BigInteger("1"), big);
	}

	@Test
	public void testSetLastNumberAccount() {
		bank.setLastNumberAccount(new BigInteger("1"));
		assertEquals(new BigInteger("1"), Bank.getLastNumberAccount());
	}

}
