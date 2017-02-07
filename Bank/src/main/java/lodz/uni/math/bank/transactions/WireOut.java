package lodz.uni.math.bank.transactions;

import java.math.BigDecimal;
import java.util.Date;

import lodz.uni.math.bank.exceptions.NoEnoughMoney;
import lodz.uni.math.bank.pojo.Account;

public class WireOut extends Transaction{
	
	private String country;
	private String swift;
	
	public WireOut(String toAccount, BigDecimal amount, String description, Integer idWireOut, String country, String swift,Account activeAccount) {
		this.setAccount(toAccount);
		this.setAmount(amount);
		this.setDate(new Date());
		this.setDescription(description);
		this.setId(idWireOut);
		this.country=country;
		this.swift=swift;
		if(activeAccount.getMoney().compareTo(amount)==-1){
			throw new NoEnoughMoney();
		}
		activeAccount.addToHistory(this);
		activeAccount.addMoney(amount.multiply(new BigDecimal("-1")));
		this.setMoneyAfterTransaction(activeAccount.getMoney().toString());
	}
	
	public String getDescriptionToHistory()
	{
		String toReturn="Wire-Out:";
		toReturn+="\n\tId: "+this.getId();
		toReturn+="\n\tCountry: "+this.country;
		toReturn+="\n\tSwift: "+this.swift;
		toReturn+="\n\tTo Account: "+this.getAccount();
		toReturn+="\n\tAmount: "+this.getAmount();
		toReturn+="\n\tDate: "+this.getDate().toString();
		toReturn+="\n\tDescription: "+this.getDescription();
		toReturn+="\n\tMoney after transaction: "+this.getMoneyAfterTransaction()+"\n";
		return toReturn;
	}

}
