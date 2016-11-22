package lodz.uni.math.Bank2;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.junit.Test;

import junit.framework.TestCase;

public class TestAccount extends TestCase {
	Account account=new Account(3, "123456789123456", "Konto");
	
	@Test
	public void testGetIdAccount()
	{
		assertEquals(account.getIdAccount(), (Integer)3);
	}
	
	@Test
	public void testSetIdAccount()
	{
		account.setIdAccount((Integer)4);
		assertEquals(account.getIdAccount(), (Integer)4);
	}
	
	@Test
	public void testGetAccountNumber()
	{
		assertEquals(account.getAccountNumber(), "123456789123456");
	}
	
	@Test
	public void testSetAccountNumber()
	{
		account.setAccountNumber("123456789123450");
		assertEquals(account.getAccountNumber(), "123456789123450");
	}
	
	@Test
	public void testGetAccountDesc()
	{
		assertEquals(account.getAccountDesc(), "Konto");
	}
	
	@Test
	public void testSetAccountDesc()
	{
		account.setAccountDesc("Konto2");
		assertEquals(account.getAccountDesc(), "Konto2");
	}
	
	@Test
	public void testGetmoney()
	{
		assertEquals(account.getMoney(), new BigDecimal("0"));
	}
	
	@Test
	public void testSetmoney()
	{
		account.setMoney(new BigDecimal("33.3"));
		assertEquals(account.getMoney(), new BigDecimal("33.3"));
	}
	
	@Test
	public void testToString()
	{
		assertEquals(account.toString(), "ID: 3, Number: 123456789123456\n\tDescription:Konto\n\tMoney: 0\n");
	}
	
	@Test
	public void testgetHistory()
	{
		assertEquals(account.getHistory(), new LinkedList<Transaction>());
	}
	
	@Test
	public void testSetHistory()
	{
		Transaction t=new Check("000000000000001", new BigDecimal("1"), "Opis", 101);
		LinkedList<Transaction> l=new LinkedList<>();
		l.add(t);
		account.setHistory(l);
		assertEquals(account.getHistory(), l);
	}
	
	@Test
	public void testAddTohistory()
	{
		Transaction t=new Check("000000000000001", new BigDecimal("1"), "Opis", 101);
		account.addToHistory(t);
		LinkedList<Transaction> l=new LinkedList<>();
		l.add(t);
		assertEquals(account.getHistory(), l);
	}
	
	@Test
	public void testAddmoney()
	{
		account.addMoney(new BigDecimal("5"));
		assertEquals(account.getMoney(), new BigDecimal("5"));
	}

	@Test
	public void testGetIdDeposit()
	{
		assertEquals(account.getIdDeposit(), (Integer)10000);
	}
	
	@Test
	public void testSetIdDeposit()
	{
		account.setIdDeposit(10005);
		assertEquals(account.getIdDeposit(), (Integer)10005);
	}
	
	
}
