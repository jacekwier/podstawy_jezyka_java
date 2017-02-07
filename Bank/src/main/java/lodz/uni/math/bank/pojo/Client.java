package lodz.uni.math.bank.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;

import lodz.uni.math.bank.exceptions.*;
import lodz.uni.math.bank.transactions.Check;
import lodz.uni.math.bank.transactions.Deposit;
import lodz.uni.math.bank.transactions.Transaction;
import lodz.uni.math.bank.transactions.WireOut;

public class Client extends Bank
{
	private final static Integer FIRST_WIREOUT_ID=new Integer("0");
	private final static int CORRECT_PESEL_LENGTH=11;
	private final static int MIN_NAME_LENGTH=2;
	private final static int MIN_SURNAME_LENGTH=2;
	
	private String name;
	private String surname;
	private String pesel;
	private ArrayList<Account> accountList= new ArrayList<Account>();
	private Integer lastIdAccount;
	private Account activeAccount;
	private Integer idWireOut=FIRST_WIREOUT_ID;
	
	public Client(String name, String surname, String pesel){
		this.name=name;
		this.surname=surname;
		this.pesel=pesel;
		this.lastIdAccount=0;
		peselValidation(this.pesel);
		clientExsistsValidation(this.pesel);
		nameValidation(this.name);
		surnameValidation(this.surname);
		clientList.add(this);
	}
	
	private void peselValidation(String pesel){
		if(pesel.length()!=CORRECT_PESEL_LENGTH){
			throw new WrongPesel();
		}
		for(int i=0;i<CORRECT_PESEL_LENGTH;i++){
			if(!Character.isDigit(pesel.charAt(i))){
				throw new WrongPesel();
			}
		}
		int peselControlSum=0;
		peselControlSum=Integer.parseInt(""+pesel.charAt(0))*1+Integer.parseInt(""+pesel.charAt(1))*3+Integer.parseInt(""+pesel.charAt(2))*7;
		peselControlSum+=Integer.parseInt(""+pesel.charAt(3))*9+Integer.parseInt(""+pesel.charAt(4))*1+Integer.parseInt(""+pesel.charAt(5))*3;
		peselControlSum+=Integer.parseInt(""+pesel.charAt(6))*7+Integer.parseInt(""+pesel.charAt(7))*9+Integer.parseInt(""+pesel.charAt(8))*1;
		peselControlSum+=Integer.parseInt(""+pesel.charAt(9))*3;
		if(10-(peselControlSum % 10)!=Integer.parseInt(""+(pesel.charAt(10)))){
			throw new WrongPesel();
		}
	}

	private void clientExsistsValidation(String pesel)
	{
		for(Client client:clientList){
			if(pesel.equals(client.getPesel())){
				throw new ClientExists();
			}
		}
	}
	
	private void nameValidation(String name){
		String _name=name;
		_name.replace(" ", "");
		if(_name.length()<MIN_NAME_LENGTH){
			throw new WrongName();
		}
		for(int i=0;i<_name.length();i++){
			if(!Character.isLetter(_name.charAt(i))){
				throw new WrongName();
			}
		}
	}
	
	private void surnameValidation(String surname){
		String _surname=surname;
		_surname.replace(" ", "");
		if(_surname.length()<MIN_SURNAME_LENGTH){
			throw new WrongSurname();
		}
		for(int i=0;i<_surname.length();i++){
			if(!Character.isLetter(_surname.charAt(i))){
				throw new WrongSurname();
			}
		}
	}
	
	public String getPesel()
	{
		return this.pesel;
	}
	
	public String toString()
	{
		return (this.name+" "+this.surname+", PESEL: "+this.pesel);
	}

	public void addAccount(String accountDesc) 
	{
		String newAccountNumber=getNewNumberAccount();
		accountList.add(new Account(this.lastIdAccount,newAccountNumber,accountDesc));
		setActiveAccount(accountList.get(accountList.size()-1));
		this.lastIdAccount=this.lastIdAccount+1;
	}

	public String getAccountsDescription() 
	{ 
		this.toString();
		String toReturn=this.toString()+"\n";
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
		boolean flag=false;
		for(Account a:accountList)
		{
			if(id.equals(a.getIdAccount()))
			{
				this.activeAccount=a;
				flag=true;
				break;
			}
		}
		if(flag==false){
			throw new WrongIdAccount();
		}
	}

	public void makeDeposit(String account, String _amount, String description) 
	{
		BigDecimal amount=new BigDecimal(_amount);
		new Deposit(account,amount,description,this.activeAccount.getIdDeposit(),this.activeAccount);
		
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

	public ArrayList<Account> getAccountList(){
		return this.accountList;
	}
	
	public void makeCheck(String toAccount, String _amount, String description) {
		BigDecimal amount=new BigDecimal(_amount);
		new Check(toAccount, amount,description,this.activeAccount.getIdCheck(),this.activeAccount);
	}

	public void makeWireOut(String toAccount, String _amount, String description, 
			String country, String swift) {
		BigDecimal amount=new BigDecimal(_amount);
		new WireOut(toAccount, amount,description,getNewIdWireOut(), country,swift,this.activeAccount);
	}
	
	public Integer getNewIdWireOut()
	{
		return ++idWireOut;
	}
	
}
