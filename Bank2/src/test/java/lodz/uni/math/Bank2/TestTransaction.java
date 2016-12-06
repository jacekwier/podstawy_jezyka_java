package lodz.uni.math.Bank2;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestTransaction {
	private static Account account;
	private static Transaction transaction;
	
	@Before
	public void before(){
		account=new Account(0, "000000000000000", "Konto1");
		transaction=new Deposit("123456789012345", new BigDecimal("23.23"), "Pierwszy przelew",0,account);
	}
	
	@Test
	public void testGetAccount() {
		assertEquals("123456789012345", transaction.getAccount());
	}

	@Test
	public void testSetAccount() {
		transaction.setAccount("789789789789789");
		assertEquals("789789789789789", transaction.getAccount());
	}

	@Test
	public void testGetAmount() {
		assertEquals("23.23", transaction.getAmount().toString());
	}

	@Test
	public void testSetAmount() {
		transaction.setAmount(new BigDecimal("15.00"));
		assertEquals("15.00", transaction.getAmount().toString());
	}

	@Test
	public void testGetDate() {
		Date date=new Date();
		transaction.setDate(date);
		assertEquals(date, transaction.getDate());
	}

	@Test
	public void testSetDate() {
		Date date=new Date();
		transaction.setDate(date);
		assertEquals(date, transaction.getDate());
	}

	@Test
	public void testGetDescription() {
		assertEquals("Pierwszy przelew", transaction.getDescription());
	}

	@Test
	public void testSetDescription() {
		transaction.setDescription("Test");
		assertEquals("Test", transaction.getDescription());
	}

	@Test
	public void testGetId() {
		assertTrue(0==transaction.getId());
	}

	@Test
	public void testSetId() {
		transaction.setId(23);
		assertTrue(23==transaction.getId());
	}

	@Test
	public void testGetDescriptionToHistory() {
		String expect="Deposit:\n\t";
		expect+="Id: 0\n\t";
		expect+="From: 123456789012345\n\t";
		expect+="Amount: 23.23\n\t";
		expect+="Date: "+transaction.getDate()+"\n\t";
		expect+="Description: Pierwszy przelew\n\t";
		expect+="Money after transaction: 23.23\n";
		assertEquals(expect, transaction.getDescriptionToHistory());
	}

	@Test
	public void testGetMoneyAfterTransaction() {
		assertEquals("23.23", transaction.getMoneyAfterTransaction());
	}

	@Test
	public void testSetMoneyAfterTransaction() {
		transaction.setMoneyAfterTransaction("5");
		assertEquals("5", transaction.getMoneyAfterTransaction());
	}

}
