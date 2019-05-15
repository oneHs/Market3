package com.smbms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smbms.pojo.Role;
import com.smbms.pojo.User;
import com.smbms.service.RoleService;
import com.smbms.service.UserService;
/**
 * 用户Controller
 * @author Mr.YU
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/userPage")
	public String user(HttpSession session) {
		List<User> userList = userService.userGetAll();
		for (User user : userList) {
			Integer userRole = user.getUserRole();
			String roleName = userService.roleNameGetById(userRole);
			user.setUserRoleName(roleName);
		}
		List<Role> roleList = roleService.roleGetAll();
		System.err.println(roleList);
		session.setAttribute("roleList", roleList);
		session.setAttribute("userList", userList);

		return "/jsp/userlist";

	}
	@RequestMapping("/userFind")
	public String userFindByNR(HttpSession session ,HttpServletRequest request ){
		String userName=request.getParameter("queryname");
		Integer userRole1=Integer.parseInt(request.getParameter("queryUserRole"));
		List<User>  userList=userService.userGetByNR(userName, userRole1);
		for (User user : userList) {
			Integer userRole = user.getUserRole();
			String roleName = userService.roleNameGetById(userRole);
			user.setUserRoleName(roleName);
		}
		
		List<Role> roleList = roleService.roleGetAll();
		session.setAttribute("roleList", roleList);
		session.setAttribute("userList", userList);
		return "/jsp/userlist";
	}
}
