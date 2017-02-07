package lodz.uni.math.bank.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;

import lodz.uni.math.bank.transactions.Transaction;

public class Account 
{
	private final static BigDecimal INITIAL_MONEY = new BigDecimal(0);
	private final static Integer FIRST_DEPOSIT_ID = new Integer(10000);
	private final static Integer FIRST_CHECK_ID = new Integer(100);
	
	private Integer idAccount;
	private String accountNumber;
	private String accountDesc;
	private BigDecimal money;
	private ArrayList<Transaction> history=new ArrayList<Transaction>();
	private Integer idDeposit;
	private Integer idCheck;
	
	public Account(int idAccount, String accountNumber, String accountDesc) {
		this.idAccount=idAccount;
		this.accountNumber=accountNumber;
		this.accountDesc=accountDesc;
		money=INITIAL_MONEY;
		setIdDeposit(FIRST_DEPOSIT_ID);
		setIdCheck(FIRST_CHECK_ID);
	}

	public Integer getIdAccount() {
		return this.idAccount;
	}

	public String getAccountNumber() {
		return accountNumber;
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

	public ArrayList<Transaction> getHistory() {
		return history;
	}
	
	public void addToHistory(Transaction t)
	{
		this.history.add(t);
	}
	public void addMoney(BigDecimal money)
	{
		this.money=this.money.add(money);
	}

	public Integer getIdDeposit() {
		return idDeposit++;
	}

	public void setIdDeposit(Integer idDeposit) {
		this.idDeposit = idDeposit;
	}

	public Integer getIdCheck() {
		return idCheck++;
	}

	public void setIdCheck(Integer idCheck) {
		this.idCheck = idCheck;
	}
}
