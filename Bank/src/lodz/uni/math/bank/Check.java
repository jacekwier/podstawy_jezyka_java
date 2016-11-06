package lodz.uni.math.bank;

import java.math.BigDecimal;
import java.util.Date;

public class Check extends Transaction{

	public Check(String toAccount, BigDecimal amount, String description, Integer idCheck) {
		this.setAccount(toAccount);
		this.setAmount(amount);
		this.setDate(new Date());
		this.setDescription(description);
		this.setId(idCheck);
	}
	
	public String getDescriptionToHistory()
	{
		String toReturn="Check:";
		toReturn+="\n\tId: "+this.getId();
		toReturn+="\n\tTo Account: "+this.getAccount();
		toReturn+="\n\tAmount: "+this.getAmount();
		toReturn+="\n\tDate: "+this.getDate().toString();
		toReturn+="\n\tDescription: "+this.getDescription()+"\n";
		return toReturn;
	}

}
