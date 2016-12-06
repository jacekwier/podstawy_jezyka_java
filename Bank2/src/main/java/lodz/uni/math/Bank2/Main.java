package lodz.uni.math.Bank2;

import java.util.logging.Logger;

public class Main {

	public static void main(String[] args) 
	{
		Logger logger = Logger.getLogger(Main.class.getName());
		
			/**Tworzenie kientow*/
		Client client1=new Client("Jan","Nowak","94120201399");
		Client client2=new Client("Adam","Kowalski","02070803628");
		//logger.info(client1.toString());
		//logger.info(client2.toString());
		
			/**Dodanie kont do klienta pierwszego i drugiego*/
		client1.addAccount("Konto 1 klienta1");
		//logger.info(client1.getAccounts());
		client1.addAccount("Konto 2 klienta1");
		client2.addAccount("Konto 1 klienta2");
		//logger.info(client1.getAccounts());
		//logger.info(client2.getAccounts());
		
			/**Zmiana aktywnego konta danego klienta, depozyty i historia*/
		//logger.info(client1.getActiveAccount().toString());
		//logger.info(client1.getAccounts());
		client1.setActiveAccount(0);
		client1.makeDeposit("555555555555555","50.05","Spac mi sie chce -.-'");
		client1.makeDeposit("666666666666666","50.10","Noc z java");
		client2.makeDeposit("777777777777777","10","EEE");
		logger.info(client1.getHistory());
		//logger.info(client2.getHistory());
		
			/**Przelew na istniejace konto*/
		//logger.info(client1.getActiveAccount().toString());
		client1.makeCheck("000000000000003","10.15","Przelew pierwszy");
		//logger.info(client1.getActiveAccount().toString());
		//logger.info(client1.getHistory());
		//logger.info(client2.getActiveAccount().toString());
		//logger.info(client2.getHistory());
		
			/**Przelew na konto ktorego nie ma u mnie w banku*/
		client1.makeCheck("777777777777777","10","Kasa poza bank");
		//logger.info(client1.getActiveAccount().toString());
		//logger.info(client1.getHistory());
		
			/**Wire-out*/
		client1.makeWireOut("123456789012345","19.99","Poza kraj","Sweden","111112222233333");
		//logger.info(client1.getActiveAccount().toString());
		//logger.info(client1.getHistory());
		//logger.info(client1.getActiveAccount().toString());
	}

}
