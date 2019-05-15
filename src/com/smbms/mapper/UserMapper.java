package com.smbms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.smbms.pojo.User;

public interface UserMapper {
	/**
	 * 根据账号密码登陆
	 * 
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	@Select("SELECT * FROM smbms_user WHERE userCode=#{userCode} AND userPassword=#{userPassword}")
	User userLogin(@Param("userCode") String userCode,@Param("userPassword") String userPassword);

	/**
	 * 获取User表中所有的数据
	 * 
	 * @return
	 */
	@Select("select * from smbms_user")
	List<User> getUserAll();
	
	/**
	 * 根据UserRole获取RoleName
	 * @param userRole
	 * @return
	 */
	@Select("select roleName from smbms_role  where id=#{userRole} ")
	String getRoleNameById(@Param("userRole") Integer userRole);
	
	/**
	 * 动态查询
	 * @param userName
	 * @param userRole
	 * @return
	 */
	@Select("<script> "
			+ "select * from smbms_user "
			+ "<where> "
			+ "<if test='userName!=null'>"
			+ "and userName like concat('%',#{userName},'%') "
			+ "</if> "
			+ "<if test= 'userRole!=0' >"
			+ "and userRole=#{userRole} "
			+ "</if> "
			+ "</where> "
			+ "</script>")
	List<User> getUserByNR(@Param("userName") String userName,@Param("userRole")int userRole);
}
