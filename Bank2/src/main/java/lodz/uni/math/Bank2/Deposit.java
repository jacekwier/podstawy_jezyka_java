package lodz.uni.math.Bank2;

import java.math.BigDecimal;
import java.util.Date;

public class Deposit extends Transaction
{
	public Deposit(String account, BigDecimal amount, String description,Integer id, Account activeAccount) {
		this.setAccount(account);
		this.setAmount(amount);
		this.setDate(new Date());
		this.setDescription(description);
		this.setId(id);
		activeAccount.addToHistory(this);
		activeAccount.addMoney(amount);
		this.setMoneyAfterTransaction(activeAccount.getMoney().toString());
	}
	
	public String getDescriptionToHistory()
	{
		String toReturn="Deposit:";
		toReturn+="\n\tId: "+this.getId();
		toReturn+="\n\tFrom: "+this.getAccount();
		toReturn+="\n\tAmount: "+this.getAmount();
		toReturn+="\n\tDate: "+this.getDate().toString();
		toReturn+="\n\tDescription: "+this.getDescription();
		toReturn+="\n\tMoney after transaction: "+this.getMoneyAfterTransaction()+"\n";
		return toReturn;
	}
}
