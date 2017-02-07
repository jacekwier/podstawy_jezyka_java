package lodz.uni.math.bank.transactions;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Transaction 
{
	private String account;
	private BigDecimal amount;
	private Date date;
	private String description;
	private Integer id;
	private String descriptionToHistory;
	private String moneyAfterTransaction="";
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescriptionToHistory() {
		return descriptionToHistory;
	}

	public String getMoneyAfterTransaction() {
		return moneyAfterTransaction;
	}

	public void setMoneyAfterTransaction(String moneyAfterTransaction) {
		this.moneyAfterTransaction = moneyAfterTransaction;
	}

}
