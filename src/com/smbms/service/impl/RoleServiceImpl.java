package com.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.mapper.RoleMapper;
import com.smbms.pojo.Role;
import com.smbms.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public List<Role> roleGetAll() {
		return roleMapper.getRoleAll();
	}

}
