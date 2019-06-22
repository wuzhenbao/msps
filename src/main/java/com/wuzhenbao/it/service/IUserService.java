package com.wuzhenbao.it.service;

import java.sql.SQLException;
import java.util.List;

import com.wuzhenbao.it.model.UserInfo;

public interface IUserService {
	public int addUser(UserInfo user) throws SQLException;
	List<UserInfo> findUserByCondition(UserInfo user) throws SQLException;
}
