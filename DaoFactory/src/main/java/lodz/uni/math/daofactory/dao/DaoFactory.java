package lodz.uni.math.daofactory.dao;

import java.util.HashMap;

import lodz.uni.math.daofactory.dao.sources.XML;
import lodz.uni.math.daofactory.dao.sources.DB;
import lodz.uni.math.daofactory.dao.sources.WS;

public class DaoFactory{
	
	static HashMap<EnumDaoFactory, InterfaceDaoFactory> sources = new HashMap<EnumDaoFactory, InterfaceDaoFactory>();
	
	private InterfaceDaoFactory interfaceDaoFactory;

	public  DaoFactory() {
		sources.put(EnumDaoFactory.XML, XML.getInstance());
		sources.put(EnumDaoFactory.DB, DB.getInstance());
		sources.put(EnumDaoFactory.WS, WS.getInstance());
	}
	
	public void setSource(EnumDaoFactory dataSource) {
		interfaceDaoFactory = sources.get(dataSource);
	}
	
	public InterfaceDaoFactory getSource() {
		return interfaceDaoFactory;
	}
}
