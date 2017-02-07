package lodz.uni.math.bank.tests;

import static org.junit.Assert.*;
import java.math.BigInteger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import lodz.uni.math.bank.exceptions.*;
import lodz.uni.math.bank.pojo.Bank;
import lodz.uni.math.bank.pojo.Client;

public class TestClient {

	private static Client client1;
	private static Client client2;

	@BeforeClass
	public static void initObjects() {
		client1 = new Client("Jan","Asuss","12253003813");
		client1.addAccount("Konto 1");
		client2 = new Client("Jane","Asusse","45032006114");
		client2.addAccount("Konto 1");
	}
	
	@Before
	public void reset(){
		Bank.clientList.clear();
		Bank.setLastNumberAccount(new BigInteger("0"));
		client1 = new Client("Jan","Asuss","12253003813");
		client1.addAccount("Konto 1");
		client2 = new Client("Jan","Asuss","45032006114");
		client2.addAccount("Konto 1");
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
		assertEquals(client1.getPesel(),"12253003813");
	}

	@Test
	public void testToString() {
		String result="Jan Asuss, PESEL: 12253003813";
		assertTrue(result.equals(client1.toString()));
	}

	@Test
	public void testGetAccounts() {
		String inscription="Jan Asuss, PESEL: 12253003813\nID: 0, Number: 000000000000001\n\tDescription:Konto 1\n\tMoney: 0\n";
		assertEquals(client1.getAccountsDescription(),inscription);
	}

	@Test
	public void testGetActiveAccount() {
		
		assertEquals(client1.getActiveAccount().getIdAccount().toString(),"0");
	}

	@Test
	public void testSetActiveAccountInteger() {
		client1.addAccount("Konto 2");
		client1.setActiveAccount(0);
		assertEquals("Konto 1", client1.getActiveAccount().getAccountDesc());
	}
	
	@Test(expected=WrongIdAccount.class)
	public void testSetActiveAccountIntegerException(){
		client1.addAccount("Konto 2");
		client1.setActiveAccount(23);
	}

	@Test
	public void testMakeDeposit() {
		client1.addAccount("Konto");
		client1.makeDeposit("123456789012345", "23.23", "Pierwszy przelew");
		assertEquals(client1.getActiveAccount().getHistory().get(0).getDescription(),"Pierwszy przelew");
	}

	@Test
	public void testGetHistory() {
		client1.addAccount("konto");
		client1.makeCheck("123456789012345", "0.0", "Nic");
		assertEquals(client1.getHistory().split("\n")[0],"History of client: Jan Asuss");
	}
	
	@Test
	public void testGetNewIdWireOut() {
		assertEquals(client1.getNewIdWireOut(),(Integer)1);
	}
	
	@Test
	public void testMakeCheck() {
		client1.makeCheck("123456789012345", "0.0", "Nic");
		assertEquals(client1.getActiveAccount().getHistory().get(0).getDescription(),"Nic");
	}
	
	@Test
	public void testMakeChecktoClientInBank(){
		client1.makeCheck(client2.getAccountList().get(0).getAccountNumber(), "0.0", "Nic");
		assertEquals(client2.getHistory().split("\n").length,9);
	}
	
	@Test(expected=NoEnoughMoney.class)
	public void testmakeCheckError(){
		client1.makeCheck("123456789012345", "300", "Na minus");
	}
	
	@Test
	public void testMakeWireOut() {
		client1.makeWireOut("123456789123456", "0", "Wireout 1", "Sweden", "123456789012345");
		assertEquals(client1.getActiveAccount().getHistory().get(0).getDescription(),"Wireout 1");
	}
	
	@Test(expected=NoEnoughMoney.class)
	public void testMakeWireOutError(){
		client1.makeWireOut("123456789123456", "200", "Wireout 1", "Sweden", "123456789012345");
	}
	
}
