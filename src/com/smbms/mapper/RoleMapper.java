package com.smbms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.smbms.pojo.Role;

public interface RoleMapper {
	@Select("select * from  smbms_role")
	List<Role> getRoleAll();
}
