package lodz.uni.math.daofactory.dao.sources;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.easymock.EasyMock;

import lodz.uni.math.daofactory.dao.InterfaceDaoFactory;
import lodz.uni.math.daofactory.pojo.User;

public class DB implements InterfaceDaoFactory {
	
	private static Logger logger = Logger.getLogger(DB.class.getName());
	
	User mockedUser = EasyMock.createMock(User.class);

	private static DB db = null;

	public static DB getInstance() {
		if (db == null) {
			db = new DB();
		}
		return db;
	}

	private DB() {
	}

	public List<User> selectAllUsers() {
		List<User> mockedUsersList = EasyMock.createMock(ArrayList.class);
		EasyMock.expect(mockedUsersList.size()).andReturn(55).anyTimes();
		EasyMock.expect(mockedUsersList.get(0)).andReturn(mockedUser).anyTimes();
		EasyMock.replay(mockedUsersList);
		return mockedUsersList;
	}

	public User selectUserById(int id) {
		EasyMock.expect(mockedUser.getId()).andReturn(id).anyTimes();
		EasyMock.expect(mockedUser.getName()).andReturn("JanuszDB").anyTimes();
		EasyMock.expect(mockedUser.getAge()).andReturn(55).anyTimes();
		EasyMock.replay(mockedUser);
		logger.trace("User id=" + mockedUser.getId() + ", name=" + mockedUser.getName() + ", age=" + mockedUser.getAge());
		return mockedUser;
	}

}
