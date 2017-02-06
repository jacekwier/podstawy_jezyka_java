package lodz.uni.math.bank.pojo;

import java.math.BigInteger;
import java.util.ArrayList;

public class Bank 
{
	private final static BigInteger INITIAL_NUMBER_ACCOUNT=new BigInteger("0");
	
	public static ArrayList<Client> clientList=new ArrayList<Client>();
	private static BigInteger lastNumberAccount=INITIAL_NUMBER_ACCOUNT;
	
	public  static BigInteger getLastNumberAccount() {
		return Bank.lastNumberAccount;
	}
	
	public void setLastNumberAccount(BigInteger lastNumberAccount) {
		Bank.lastNumberAccount = lastNumberAccount;
	}
	
	public String getNewNumberAccount(){
		setLastNumberAccount(getLastNumberAccount().add(new BigInteger("1")));
		String accountNumber=getLastNumberAccount().toString();
		String zeros="";
		for(int i=0;i<15-accountNumber.length();i++){
			zeros+="0";
		}
		accountNumber=zeros+accountNumber;
		return accountNumber;
	}
}
