package lodz.uni.math.bank.tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import lodz.uni.math.bank.pojo.Account;
import lodz.uni.math.bank.transactions.Transaction;
import lodz.uni.math.bank.transactions.Deposit;

public class TestAccount{
	private static Account testAccount1;
	private static Transaction transactionDeposit;
	
	@BeforeClass
	public static void initObjects(){
		testAccount1=new Account(3, "123456789123456", "Konto");
		transactionDeposit=new Deposit("000000000000001", new BigDecimal("1"), "Opis", 101,testAccount1);
	}
	@Before
	public void reset(){
		testAccount1=new Account(3, "123456789123456", "Konto");
	}
	
	@Test
	public void testGetIdAccount()
	{
		assertEquals(testAccount1.getIdAccount(),(Integer)3);
	}
	
	@Test
	public void testGetAccountNumber()
	{
		assertEquals(testAccount1.getAccountNumber(),"123456789123456");
	}
	
	@Test
	public void testGetAccountDesc()
	{
		assertEquals(testAccount1.getAccountDesc(),"Konto");
	}
	
	@Test
	public void testSetAccountDesc()
	{
		testAccount1.setAccountDesc("Konto2");
		assertEquals(testAccount1.getAccountDesc(),"Konto2");
	}
	
	@Test
	public void testGetmoney()
	{
		assertEquals(testAccount1.getMoney(),new BigDecimal("0"));
	}
	
	@Test
	public void testSetmoney()
	{
		testAccount1.setMoney(new BigDecimal("33.3"));
		assertEquals(testAccount1.getMoney(),new BigDecimal("33.3"));
	}
	
	@Test
	public void testToString()
	{
		assertEquals(testAccount1.toString(),"ID: 3, Number: 123456789123456\n\tDescription:Konto\n\tMoney: 0\n");
	}
	
	@Test
	public void testgetHistory()
	{
		assertEquals(testAccount1.getHistory(),new ArrayList<Transaction>());
	}
	
	@Test
	public void testAddTohistory()
	{
		testAccount1.addToHistory(transactionDeposit);
		ArrayList<Transaction> array=new ArrayList<Transaction>();
		array.add(transactionDeposit);
		assertEquals(testAccount1.getHistory().get(0),transactionDeposit);
	}
	
	@Test
	public void testAddmoney()
	{
		testAccount1.addMoney(new BigDecimal("5"));
		assertEquals(testAccount1.getMoney(),new BigDecimal("5"));
	}

	@Test
	public void testGetIdDeposit()
	{
		assertEquals(testAccount1.getIdDeposit(),(Integer)10000);
	}
	
	@Test
	public void testSetIdDeposit()
	{
		testAccount1.setIdDeposit(10005);
		assertEquals(testAccount1.getIdDeposit(),(Integer)10005);
	}
	
	@Test
	public void testGetIdCheck(){
		assertEquals(testAccount1.getIdCheck(),(Integer)100);
	}
}
