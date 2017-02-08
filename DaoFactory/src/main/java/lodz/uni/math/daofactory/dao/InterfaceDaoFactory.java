package lodz.uni.math.daofactory.dao;

import java.util.List;

import lodz.uni.math.daofactory.pojo.User;

public interface InterfaceDaoFactory {

	List<User> selectAllUsers();

	User selectUserById(int user);

}
