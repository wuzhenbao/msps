package com.wuzhenbao.it.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.wuzhenbao.it.model.UserInfo;
import com.wuzhenbao.it.service.IUserService;

@RestController()
public class TestConctroller {
	private static Logger log = Logger.getLogger(TestConctroller.class);
	@Autowired
	private IUserService userService;

	@RequestMapping(path = { "user/addUser" }, method = RequestMethod.POST)
	public String addUser(@RequestParam(name = "userName") String userName) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			UserInfo user = new UserInfo();
			int insertcount = userService.addUser(user);
			if (insertcount > 0) {
				log.info("保存用户信息成功" + userName);
				resultMap.put("code", "0");
				resultMap.put("message", "success");
			}
		} catch (Exception e) {
			resultMap.put("code", "1");
			resultMap.put("errorMsg", "保存用户信息失败");
		}
		return JSON.toJSONString(resultMap);
	}

	@RequestMapping(path = { "user/queryUser/{userName}" }, method = RequestMethod.GET)
	public String queryUser(@PathVariable(name = "userName") String userName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			UserInfo user = new UserInfo();
			user.setUserNameCn(userName);
			List<UserInfo> userList = userService.findUserByCondition(user);
			resultMap.put("code", "0");
			resultMap.put("message", "success");
			resultMap.put("userList", userList);
		} catch (Exception e) {
			resultMap.put("code", "1");
			resultMap.put("errorMsg", "查询用户信息失败");
		}
		return JSON.toJSONString(resultMap);
	}
}
