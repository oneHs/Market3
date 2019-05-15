package com.smbms.service;

import java.util.List;

import com.smbms.pojo.User;

public interface UserService {
	/**
	 * 用户登陆
	 * 
	 * @param userCode
	 * @param userPassword
	 * @return
	 * @throws Exception
	 */
	User login(String userCode, String userPassword);

	/**
	 * 获取User表中的所有数据
	 * @return
	 */
	List<User> userGetAll();
	
	String roleNameGetById(Integer userRole);
	
	List<User> userGetByNR(String userName ,Integer userRole);
	
}
