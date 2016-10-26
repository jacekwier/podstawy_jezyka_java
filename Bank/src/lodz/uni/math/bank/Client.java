package lodz.uni.math.bank;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Client 
{
	private static int clientCounter=0;
	private String name=null;
	private String surname=null;
	private GregorianCalendar birthDate=null;
	private LinkedList<Account> accountList= new LinkedList<Account>();
	private Integer idAccount=0;
	private Account activeAccount=null;
	
	Client()
	{
		clientCounter+=1;
	}
	
	Client(String name, String surname, GregorianCalendar birthDate)
	{
		this.name=name;
		this.surname=surname;
		this.birthDate=birthDate;
		clientCounter+=1;
	}
	
	public Client(String name, String surname) 
	{
		this.name=name;
		this.surname=surname;
		clientCounter+=1;
	}

	public static int getClientCounter() 
	{
		return clientCounter;
	}
	
	public String toString()
	{
		if (this.birthDate==null)
		{
			return (this.name+" "+this.surname);
		}
		String birthDate=this.birthDate.get(GregorianCalendar.DAY_OF_MONTH)+"."+
				this.birthDate.get(GregorianCalendar.MONTH)+"."+
				this.birthDate.get(GregorianCalendar.YEAR);
		return (this.name+" "+this.surname+"("+birthDate+")");
	}

	public void addAccount(String accountNumber, String accountDesc) 
	{
		accountList.add(new Account(this.idAccount,accountNumber,accountDesc));
		setActiveAccount(accountList.get(accountList.size()-1));
		this.idAccount+=1;
	}

	public String getAccounts() 
	{
		String toReturn="";
		for(Account a: accountList)
		{	
			toReturn+=a.toString();
		}
		return toReturn;
	}

	public Account getActiveAccount() {
		return activeAccount;
	}
	
	public void setActiveAccount(Account activeAccount) 
	{
		this.activeAccount = activeAccount;
	}
	
	public void setActiveAccount(Integer id) 
	{
		for(Account a:accountList)
		{
			if(a.getIdAccount().equals(id))
			{
				this.activeAccount=a;
				break;
			}
			//Wypadaloby napisac wyjatek ktory powie ze nie ma konta o takim id
		}
	}

	public void makeDeposit(String account, double amount, String description) 
	{
		Transaction transaction=new Deposit(account,amount,description);
		this.activeAccount.addToHistory(transaction);
		BigDecimal aa=new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP);
		this.activeAccount.addMoney(aa);
		
	}
	

}
