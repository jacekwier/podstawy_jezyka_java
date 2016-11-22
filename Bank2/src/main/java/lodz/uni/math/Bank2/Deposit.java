package lodz.uni.math.Bank2;

import java.math.BigDecimal;
import java.util.Date;

public class Deposit extends Transaction
{
	public Deposit(String account, BigDecimal amount, String description,Integer id) {
		this.setAccount(account);
		this.setAmount(amount);
		this.setDate(new Date());
		this.setDescription(description);
		this.setId(id);
	}
	
	public String getDescriptionToHistory()
	{
		String toReturn="Deposit:";
		toReturn+="\n\tId: "+this.getId();
		toReturn+="\n\tFrom: "+this.getAccount();
		toReturn+="\n\tAmount: "+this.getAmount();
		toReturn+="\n\tDate: "+this.getDate().toString();
		toReturn+="\n\tDescription: "+this.getDescription()+"\n";
		return toReturn;
	}
}
