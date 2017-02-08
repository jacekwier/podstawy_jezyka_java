package lodz.uni.math.daofactory.dao.sources;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.easymock.EasyMock;

import lodz.uni.math.daofactory.dao.InterfaceDaoFactory;
import lodz.uni.math.daofactory.pojo.User;

public class XML implements InterfaceDaoFactory {

	private static Logger logger = Logger.getLogger(XML.class.getName());

	User mockedUser = EasyMock.createMock(User.class);

	private static XML xml = null;

	public static XML getInstance() {
		if (xml == null) {
			xml = new XML();
		}
		return xml;
	}

	private XML() {
	}

	public List<User> selectAllUsers() {
		List<User> mockedUsersList = EasyMock.createMock(ArrayList.class);
		EasyMock.expect(mockedUsersList.size()).andReturn(59).anyTimes();
		EasyMock.expect(mockedUsersList.get(0)).andReturn(mockedUser).anyTimes();
		EasyMock.replay(mockedUsersList);
		return mockedUsersList;
	}

	public User selectUserById(int id) {
		EasyMock.expect(mockedUser.getId()).andReturn(id).anyTimes();
		EasyMock.expect(mockedUser.getName()).andReturn("JanuszXML").anyTimes();
		EasyMock.expect(mockedUser.getAge()).andReturn(59).anyTimes();
		EasyMock.replay(mockedUser);
		logger.trace("User id=" + mockedUser.getId() + ", name=" + mockedUser.getName() + ", age=" + mockedUser.getAge());
		return mockedUser;
	}

}
