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
	
	/*
	 * A gdyby pole id przeniesc do deposit ?
	 */
	

}
