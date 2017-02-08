package lodz.uni.math.daofactory;

import org.apache.log4j.Logger;

import lodz.uni.math.daofactory.dao.DaoFactory;
import lodz.uni.math.daofactory.dao.EnumDaoFactory;

public class Main {
	
	private static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		
		DaoFactory daoFactory = new DaoFactory();
		daoFactory.setSource(EnumDaoFactory.XML);
		daoFactory.getSource().selectUserById(1);
		logger.info(daoFactory.getSource().selectAllUsers().size());
		daoFactory.setSource(EnumDaoFactory.DB);
		daoFactory.getSource().selectUserById(1);
		logger.info(daoFactory.getSource().selectAllUsers().size());
		daoFactory.setSource(EnumDaoFactory.WS);
		daoFactory.getSource().selectUserById(1);
		logger.info(daoFactory.getSource().selectAllUsers().size());
		
		DaoFactory daoFactory2 = new DaoFactory();
		daoFactory2.setSource(EnumDaoFactory.WS);
    	
		logger.info(daoFactory.getSource());
		logger.info(daoFactory2.getSource());
	}
}
