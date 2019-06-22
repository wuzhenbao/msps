package com.wuzhenbao.it.service.impl;

import com.wuzhenbao.it.dao.user.UserDao;
import com.wuzhenbao.it.model.UserInfo;
import com.wuzhenbao.it.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public int addUser(UserInfo user) throws SQLException{
		user.setIsDeleted("1");
		user.setUserNameEn("test");
		user.setUserAddress("深圳坂田");
		user.setUserEmail("zhangsan@126.com");
		user.setUserSex("1");
		return userDao.addUser(user);
	}

	@Override
	public List<UserInfo> findUserByCondition(UserInfo user) throws SQLException {
		return userDao.findUserByCondition(user);
	}

}
