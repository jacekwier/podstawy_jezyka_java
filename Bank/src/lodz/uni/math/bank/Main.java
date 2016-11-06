package lodz.uni.math.bank;

import java.util.GregorianCalendar;
import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) 
	{
		Logger logger = Logger.getLogger(Main.class.getName());
		
			//Tworzenie kientow
		Client client1=new Client("Jan","Nowak",new GregorianCalendar(1994,1,1));
		Client client2=new Client("Adam","Kowalski");
		//logger.info(client1.toString());
		//logger.info(client2.toString());
		
			//Dodanie kont do klienta pierwszego i drugiego
		client1.addAccount("000000000000001","Konto 1 klienta1");
		client1.addAccount("000000000000002","Konto 2 klienta2");
		client2.addAccount("000000000000003","Konto 1 klienta2");
		//logger.info(client1.getAccounts());
		//logger.info(client2.getAccounts());
		
			//Zmiana aktywnego konta danego klienta
		//logger.info(client1.getActiveAccount().toString());
		//logger.info(client1.getAccounts());
		client1.setActiveAccount(0);
		client1.makeDeposit("555555555555555","50.05","Spac mi sie chce -.-'");
		client1.makeDeposit("666666666666666","50.10","Noc z java");
		client2.makeDeposit("777777777777777","10","EEE");
		logger.info(client1.getActiveAccount().toString());
		
		logger.info(client1.getHistory());
		logger.info(client2.getHistory());
	}

}
