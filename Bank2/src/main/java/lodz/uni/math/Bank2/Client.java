package lodz.uni.math.Bank2;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.LinkedList;

public class Client extends Bank
{
	private static int clientCounter=0;
	private String name=null;
	private String surname=null;
	private GregorianCalendar birthDate=null;
	private LinkedList<Account> accountList= new LinkedList<Account>();
	private Integer idAccount=0;
	private Account activeAccount=null;
	private Integer idWireOut=new Integer("0");
	
	Client()
	{
		clientCounter+=1;
		clientList.add(this);
	}
	
	Client(String name, String surname, GregorianCalendar birthDate)
	{
		this.name=name;
		this.surname=surname;
		this.birthDate=birthDate;
		clientCounter+=1;
		clientList.add(this);
	}
	
	public Client(String name, String surname) 
	{
		this.name=name;
		this.surname=surname;
		clientCounter+=1;
		clientList.add(this);
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

	public void makeDeposit(String account, String _amount, String description) 
	{
		BigDecimal amount=new BigDecimal(_amount);
		Transaction transaction=new Deposit(account,amount,description,this.activeAccount.getIdDeposit());
		this.activeAccount.addToHistory(transaction);
		this.activeAccount.addMoney(amount);
		
	}

	public String getHistory() 
	{
		String toReturn="History of client: "+this.name+" "+this.surname;
		toReturn+="\nAccount: [ID]"+this.activeAccount.getIdAccount();
		toReturn+=" [Number]"+this.activeAccount.getAccountNumber()+"\n";
		for (Transaction t:this.activeAccount.getHistory())
		{
			toReturn+=t.getDescriptionToHistory();
			
		}
		return toReturn;
	}

	public void makeCheck(String toAccount, String _amount, String description) {
		BigDecimal amount=new BigDecimal(_amount);
		Transaction transaction=new Check(toAccount, amount,description,this.activeAccount.getIdCheck());
		this.activeAccount.addToHistory(transaction);
		this.activeAccount.addMoney(amount.multiply(new BigDecimal("-1")));
		for(Client client:clientList)
		{
			for(Account account:client.accountList)
			{
				if(toAccount.equals(account.getAccountNumber()))
				{
					client.setActiveAccount(account);
					client.makeDeposit(this.activeAccount.getAccountNumber(),
							_amount, description);
					continue;
				}
			}
		}
	}

	public void makeWireOut(String toAccount, String _amount, String description, 
			String country, String swift) {
		BigDecimal amount=new BigDecimal(_amount);
		Transaction transaction=new WireOut(toAccount, amount,description,getIdWireOut(), country,swift);
		this.activeAccount.addToHistory(transaction);
		this.activeAccount.addMoney(amount.multiply(new BigDecimal("-1")));
		
	}
	
	public Integer getIdWireOut()
	{
		idWireOut+=1;
		return idWireOut;
	}
	

}
