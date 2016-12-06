package lodz.uni.math.Bank2;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.junit.Test;

import junit.framework.TestCase;

public class TestAccount extends TestCase {
	Account account=new Account(3, "123456789123456", "Konto");
	
	@Test
	public void testAccount(){
		new Account(2,"123456789012345","Kontoo");
	}
	
	@Test
	public void testGetIdAccount()
	{
		assertEquals((Integer)3,account.getIdAccount());
	}
	
	@Test
	public void testSetIdAccount()
	{
		account.setIdAccount((Integer)4);
		assertEquals((Integer)4,account.getIdAccount());
	}
	
	@Test
	public void testGetAccountNumber()
	{
		assertEquals("123456789123456",account.getAccountNumber());
	}
	
	@Test
	public void testSetAccountNumber()
	{
		account.setAccountNumber("123456789123450");
		assertEquals("123456789123450",account.getAccountNumber());
	}
	
	@Test
	public void testGetAccountDesc()
	{
		assertEquals("Konto",account.getAccountDesc());
	}
	
	@Test
	public void testSetAccountDesc()
	{
		account.setAccountDesc("Konto2");
		assertEquals("Konto2",account.getAccountDesc());
	}
	
	@Test
	public void testGetmoney()
	{
		assertEquals(new BigDecimal("0"),account.getMoney());
	}
	
	@Test
	public void testSetmoney()
	{
		account.setMoney(new BigDecimal("33.3"));
		assertEquals(new BigDecimal("33.3"),account.getMoney());
	}
	
	@Test
	public void testToString()
	{
		assertEquals("ID: 3, Number: 123456789123456\n\tDescription:Konto\n\tMoney: 0\n",account.toString());
	}
	
	@Test
	public void testgetHistory()
	{
		assertEquals(new LinkedList<Transaction>(),account.getHistory());
	}
	
	@Test
	public void testAddTohistory()
	{
		Transaction t=new Deposit("000000000000001", new BigDecimal("1"), "Opis", 101,account);
		account.addToHistory(t);
		LinkedList<Transaction> l=new LinkedList<>();
		l.add(t);
		assertEquals(t,account.getHistory().get(0));
	}
	
	@Test
	public void testAddmoney()
	{
		account.addMoney(new BigDecimal("5"));
		assertEquals(new BigDecimal("5"),account.getMoney());
	}

	@Test
	public void testGetIdDeposit()
	{
		assertEquals((Integer)10000,account.getIdDeposit());
	}
	
	@Test
	public void testSetIdDeposit()
	{
		account.setIdDeposit(10005);
		assertEquals((Integer)10005,account.getIdDeposit());
	}
	
	
}
