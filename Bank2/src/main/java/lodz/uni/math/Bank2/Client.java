package lodz.uni.math.Bank2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

import Exceptions.*;

public class Client extends Bank
{
	private String name=null;
	private String surname=null;
	private String pesel=null;
	private ArrayList<Account> accountList= new ArrayList<Account>();
	private Integer idAccount;
	private Account activeAccount=null;
	private Integer idWireOut=new Integer("0");
	
	public Client(String name, String surname, String pesel){
		this.name=name;
		this.surname=surname;
		this.pesel=pesel;
		this.idAccount=0;
		peselValidation(this.pesel);
		clientExsistsValidation(this.pesel);
		nameValidation(this.name);
		surnameValidation(this.surname);
		clientList.add(this);
	}
	
	private void peselValidation(String pesel){
		if(pesel.length()!=11){
			throw new WrongPesel();
		}
		for(int i=0;i<11;i++){
			if(!Character.isDigit(pesel.charAt(i))){
				throw new WrongPesel();
			}
		}
		int control=0;
		control=Integer.parseInt(""+pesel.charAt(0))*1+Integer.parseInt(""+pesel.charAt(1))*3+Integer.parseInt(""+pesel.charAt(2))*7;
		control+=Integer.parseInt(""+pesel.charAt(3))*9+Integer.parseInt(""+pesel.charAt(4))*1+Integer.parseInt(""+pesel.charAt(5))*3;
		control+=Integer.parseInt(""+pesel.charAt(6))*7+Integer.parseInt(""+pesel.charAt(7))*9+Integer.parseInt(""+pesel.charAt(8))*1;
		control+=Integer.parseInt(""+pesel.charAt(9))*3;
		if(10-(control % 10)!=Integer.parseInt(""+(pesel.charAt(10)))){
			throw new WrongPesel();
		}
	}

	private void clientExsistsValidation(String _pesel)
	{
		for(Client c:clientList){
			if(_pesel.equals(c.getPesel())){
				throw new ClientExists();
			}
		}
	}
	
	private void nameValidation(String name){
		String _name=name;
		_name.replace(" ", "");
		if(_name.length()<2){
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
		if(_surname.length()<2){
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
		setLastNumberAccount(getLastNumberAccount().add(new BigInteger("1")));
		String accountNumber=getLastNumberAccount().toString();
		String zeros="";
		for(int i=0;i<15-accountNumber.length();i++){
			zeros+="0";
		}
		accountNumber=zeros+accountNumber;
		accountList.add(new Account(this.idAccount,accountNumber,accountDesc));
		setActiveAccount(accountList.get(accountList.size()-1));
		this.idAccount=this.idAccount+1;
	}

	public String getAccounts() 
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
		new WireOut(toAccount, amount,description,getIdWireOut(), country,swift,this.activeAccount);
	}
	
	public Integer getIdWireOut()
	{
		idWireOut+=1;
		return idWireOut;
	}
	

}
