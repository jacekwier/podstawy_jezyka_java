package lodz.uni.math.bank.pojo;

import java.math.BigInteger;
import java.util.ArrayList;

public class Bank 
{
	private final static BigInteger INITIAL_NUMBER_ACCOUNT=new BigInteger("0");
	
	public static ArrayList<Client> clientList=new ArrayList<Client>();
	private static BigInteger lastNumberAccount=INITIAL_NUMBER_ACCOUNT;
	
	public static void setLastNumberAccount(BigInteger lastNumberAccount) {
		Bank.lastNumberAccount = lastNumberAccount;
	}
	
	public String getNewNumberAccount(){
		Bank.lastNumberAccount=(Bank.lastNumberAccount.add(new BigInteger("1")));
		String accountNumber=Bank.lastNumberAccount.toString();
		String zeros="";
		for(int i=0;i<15-accountNumber.length();i++){
			zeros+="0";
		}
		accountNumber=zeros+accountNumber;
		return accountNumber;
	}
}
