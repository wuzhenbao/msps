package com.wuzhenbao.it.dao.user;

import java.sql.SQLException;
import java.util.List;

import com.wuzhenbao.it.model.UserInfo;

public interface UserDao {
	int addUser(UserInfo record) throws SQLException;

	UserInfo findUserById(UserInfo user) throws SQLException;
	
	List<UserInfo> findUserByCondition(UserInfo user) throws SQLException;

	void updateUser(UserInfo user) throws SQLException;

	void deleteUser(UserInfo user) throws SQLException;
	UserInfo login(UserInfo user) throws SQLException;
}
