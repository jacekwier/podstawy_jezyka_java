package lodz.uni.math.bank.transactions;

import java.math.BigDecimal;
import java.util.Date;

import lodz.uni.math.bank.exceptions.NoEnoughMoney;
import lodz.uni.math.bank.pojo.Account;
import lodz.uni.math.bank.pojo.Bank;
import lodz.uni.math.bank.pojo.Client;

public class Check extends Transaction{

	public Check(String toAccount, BigDecimal amount, String description, Integer idCheck, Account activeAccount) {
		this.setAccount(toAccount);
		this.setAmount(amount);
		this.setDate(new Date());
		this.setDescription(description);
		this.setId(idCheck);
		if(activeAccount.getMoney().compareTo(amount)==-1){
			throw new NoEnoughMoney();
		}
		activeAccount.addToHistory(this);
		activeAccount.addMoney(amount.multiply(new BigDecimal("-1")));
		this.setMoneyAfterTransaction(activeAccount.getMoney().toString());
		for(Client client:Bank.clientList)
		{
			for(Account account:client.getAccountList())
			{
				if(toAccount.equals(account.getAccountNumber()))
				{
					client.setActiveAccount(account);
					client.makeDeposit(activeAccount.getAccountNumber(),
							amount.toString(), description);
				}
			}
		}
	}
	
	public String getDescriptionToHistory()
	{
		String toReturn="Check:";
		toReturn+="\n\tId: "+this.getId();
		toReturn+="\n\tTo Account: "+this.getAccount();
		toReturn+="\n\tAmount: "+this.getAmount();
		toReturn+="\n\tDate: "+this.getDate().toString();
		toReturn+="\n\tDescription: "+this.getDescription();
		toReturn+="\n\tMoney after transaction: "+this.getMoneyAfterTransaction()+"\n";
		return toReturn;
	}

}
