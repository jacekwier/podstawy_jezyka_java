package lodz.uni.math.bank;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import lodz.uni.math.bank.exceptions.ClientExists;
import lodz.uni.math.bank.exceptions.NoEnoughMoney;
import lodz.uni.math.bank.exceptions.WrongName;
import lodz.uni.math.bank.exceptions.WrongPesel;
import lodz.uni.math.bank.exceptions.WrongSurname;
import lodz.uni.math.bank.pojo.Bank;
import lodz.uni.math.bank.pojo.Client;

public class TestClient {

	private static Client client1;

	@BeforeClass
	public static void test() {
		client1 = new Client("Jan","Asuss","12253003813");
		client1.addAccount("Konto 1");
	}
	
	@After
	public void after(){
		Bank.clientList.clear();
		client1.setLastNumberAccount(new BigInteger("0"));
		client1 = new Client("Jan","Asuss","12253003813");
		client1.addAccount("Konto 1");
	}
	
	@Test(expected=WrongPesel.class)
	public void testPeselValidationWrongLenght(){
		new Client("SomeName","SomeSurname","122530038");
	}
	
	@Test(expected=WrongPesel.class)
	public void testPeselValidationLetters(){
		new Client("SomeName","SomeSurname","3908241005d");
	}
	
	@Test(expected=WrongPesel.class)
	public void testPeselValidationWrongChecksum(){
		new Client("SomeName","SomeSurname","39082410053");
	}
	
	@Test(expected=ClientExists.class)
	public void testClientClientExsists(){
		new Client("Jan","Asuss","12253003813");
	}
	
	@Test(expected=WrongName.class)
	public void testNameValidationTooShort(){
		new Client("J","Asuss","39082410052");
	}
	
	@Test(expected=WrongName.class)
	public void testNameValidationNumbers(){
		new Client("Jan2","Asuss","39082410052");
	}
	
	@Test(expected=WrongSurname.class)
	public void testSurnameValidationTooShort(){
		new Client("Jan","A","39082410052");
	}
	
	@Test(expected=WrongSurname.class)
	public void testSurnameValidationNumbers(){
		new Client("Jan","Asuss2","39082410052");
	}
	
	@Test
	public void testGetPesel() {
		assertEquals("12253003813",client1.getPesel());
	}

	@Test
	public void testToString() {
		String result="Jan Asuss, PESEL: 12253003813";
		assertTrue(result.equals(client1.toString()));
	}
	
	@Test
	public void testAddAccount() {
		client1.addAccount("Konto 2");
		assertEquals("Konto 2", client1.getActiveAccount().getAccountDesc());
	}

	@Test
	public void testGetAccounts() {
		String inscription="Jan Asuss, PESEL: 12253003813\nID: 0, Number: 000000000000001\n\tDescription:Konto 1\n\tMoney: 0\n";
		assertEquals(inscription, client1.getAccountsDescription());
	}

	@Test
	public void testGetActiveAccount() {
		
		assertEquals("0", client1.getActiveAccount().getIdAccount().toString());
	}

	@Test
	public void testSetActiveAccountAccount() {
		client1.addAccount("Konto 2");
		client1.setActiveAccount(client1.getAccountList().get(0));
		assertEquals("Konto 1", client1.getActiveAccount().getAccountDesc());
	}

	@Test
	public void testSetActiveAccountInteger() {
		client1.addAccount("Konto 2");
		client1.setActiveAccount(0);
		assertEquals("Konto 1", client1.getActiveAccount().getAccountDesc());
	}

	@Test
	public void testMakeDeposit() {
		client1.addAccount("Konto");
		client1.makeDeposit("123456789012345", "23.23", "Pierwszy przelew");
		assertEquals("Pierwszy przelew", client1.getActiveAccount().getHistory().get(0).getDescription());
	}

	@Test
	public void testGetHistory() {
		client1.addAccount("konto");
		client1.makeCheck("123456789012345", "0.0", "Nic");
		assertEquals("Nic", client1.getActiveAccount().getHistory().get(0).getDescription());
	}

	@Test
	public void testMakeCheck() {
		client1.makeCheck("123456789012345", "0.0", "Nic");
		assertEquals("Nic", client1.getActiveAccount().getHistory().get(0).getDescription());
	}
	
	@Test(expected=NoEnoughMoney.class)
	public void testmakeCheckError(){
		client1.makeCheck("123456789012345", "300", "Na minus");
	}
	
	@Test
	public void testGetAccountList() {
		assertEquals(1, client1.getAccountList().size());
	}

	@Test
	public void testGetIdWireOut() {
		assertEquals("1",client1.getNewIdWireOut().toString());
	}
	
	@Test
	public void testMakeWireOut() {
		client1.makeWireOut("123456789123456", "0", "Wireout 1", "Sweden", "123456789012345");
		assertEquals("Wireout 1", client1.getActiveAccount().getHistory().get(0).getDescription());
	}
	
	@Test(expected=NoEnoughMoney.class)
	public void testMakeWireOutError(){
		client1.makeWireOut("123456789123456", "200", "Wireout 1", "Sweden", "123456789012345");
	}
}
