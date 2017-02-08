package lodz.uni.math.daofactory.dao.sources;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.easymock.EasyMock;

import lodz.uni.math.daofactory.dao.InterfaceDaoFactory;
import lodz.uni.math.daofactory.pojo.User;

public class WS implements InterfaceDaoFactory {

	private static Logger logger = Logger.getLogger(WS.class.getName());

	User mockedUser = EasyMock.createMock(User.class);

	private static WS ws = null;

	public static WS getInstance() {
		if (ws == null) {
			ws = new WS();
		}
		return ws;
	}

	private WS() {
	}

	public List<User> selectAllUsers() {
		List<User> mockedUsersList = EasyMock.createMock(ArrayList.class);
		EasyMock.expect(mockedUsersList.size()).andReturn(49).anyTimes();
		EasyMock.expect(mockedUsersList.get(0)).andReturn(mockedUser).anyTimes();
		EasyMock.replay(mockedUsersList);
		return mockedUsersList;
	}

	public User selectUserById(int id) {
		EasyMock.expect(mockedUser.getId()).andReturn(id).anyTimes();
		EasyMock.expect(mockedUser.getName()).andReturn("JanuszWS").anyTimes();
		EasyMock.expect(mockedUser.getAge()).andReturn(49).anyTimes();
		EasyMock.replay(mockedUser);
		logger.trace("User id=" + mockedUser.getId() + ", name=" + mockedUser.getName() + ", age=" + mockedUser.getAge());
		return mockedUser;
	}

}
