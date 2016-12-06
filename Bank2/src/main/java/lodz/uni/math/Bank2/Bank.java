package lodz.uni.math.Bank2;

import java.math.BigInteger;
import java.util.ArrayList;

public class Bank 
{
	public static ArrayList<Client> clientList=new ArrayList<Client>();
	private static BigInteger lastNumberAccount=new BigInteger("0");
	
	public  static BigInteger getLastNumberAccount() {
		return Bank.lastNumberAccount;
	}
	public void setLastNumberAccount(BigInteger lastNumberAccount) {
		Bank.lastNumberAccount = lastNumberAccount;
	}
}
