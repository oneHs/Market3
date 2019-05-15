package com.smbms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smbms.pojo.User;
import com.smbms.service.UserService;
/**
 * 登陆Controller
 * @author Mr.Yu
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/login")
	public String userLogin(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		String userCode = req.getParameter("userCode");
		String userPassword = req.getParameter("userPassword");
		System.out.println(userCode);
		System.out.println(userPassword);
		User user = userService.login(userCode, userPassword);
		System.err.println("hello word");
		if (user != null) {
			System.out.println("登陆功能欧克");
			session.setAttribute("userSession", user);
			return "jsp/frame";
		} else {
			return "jsp/error";
		}

	}
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session){
		session.removeAttribute("userSession");
        return "redirect:/login.jsp";
	}
	
}
