package lodz.uni.math.bank;

import java.math.BigDecimal;
import java.util.Date;

public class Deposit extends Transaction
{
	private static Integer idCounter=10000;
	
	public Deposit(String account, double amount, String description) {
		this.setAccount(account);
		this.setAmount(new BigDecimal(amount));
		this.setDate(new Date());
		this.setDescription(description);
		this.setId(idCounter);
		idCounter+=1;
	}
	
	public String getDescriptionToHistory()
	{
		String toReturn="Deposit:";
		toReturn+="\n\tId: "+this.getId();
		toReturn+="\n\tFrom: "+this.getAccount();
		toReturn+="\n\tAmount: "+this.getAmount();
		toReturn+="\n\tDate: "+this.getDate().toLocaleString();
		toReturn+="\n\tDescription: "+this.getDescription()+"\n";
		return toReturn;
	}
	
	/*
	 * A gdyby pole id przeniesc do deposit ?
	 */
	

}
