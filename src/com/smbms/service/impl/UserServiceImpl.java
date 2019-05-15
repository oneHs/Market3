package com.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.mapper.UserMapper;
import com.smbms.pojo.User;
import com.smbms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	/*
	 * @Override public User userLogin(String userCode, String userPassword) {
	 * return userMapper.userLogin(userCode, userPassword); }
	 * 
	 * @Override public ArrayList<User> userGetAll() { return
	 * userMapper.getUserAll(); }
	 */

	@Override
	public User login(String userCode, String userPassword) {
		return userMapper.userLogin(userCode, userPassword);
	}

	@Override
	public List<User> userGetAll() {
		return userMapper.getUserAll();
	}

	@Override
	public String roleNameGetById(Integer userRole) {
		return userMapper.getRoleNameById(userRole);
	}

	@Override
	public List<User> userGetByNR(String userName, Integer userRole) {
		return userMapper.getUserByNR(userName, userRole);
	}

}
