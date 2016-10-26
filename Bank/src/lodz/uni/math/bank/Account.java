package lodz.uni.math.bank;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Account 
{
	private Integer idAccount;
	private String accountNumber;
	private String accountDesc;
	private BigDecimal money=new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
	private LinkedList<Transaction> history=new LinkedList<Transaction>();
	
	public Account(int idAccount, String accountNumber, String accountDesc) {
		this.setIdAccount(idAccount);
		this.setAccountNumber(accountNumber);
		this.setAccountDesc(accountDesc);
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountDesc() {
		return accountDesc;
	}

	public void setAccountDesc(String accountDesc) {
		this.accountDesc = accountDesc;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	public String toString()
	{
		String toReturn="";
		toReturn+="ID: "+this.getIdAccount().toString()+", Number: "+this.getAccountNumber()+"\n";
		toReturn+="\tDescription:"+this.getAccountDesc()+"\n";
		toReturn+="\tMoney: "+this.getMoney().toString()+"\n";
		return toReturn;
	}

	public LinkedList<Transaction> getHistory() {
		return history;
	}

	public void setHistory(LinkedList<Transaction> history) {
		this.history = history;
	}
	
	public void addToHistory(Transaction t)
	{
		this.history.add(t);
	}
	public void addMoney(BigDecimal money)
	{
		this.money=this.money.add(money);
	}
}
