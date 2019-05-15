package com.smbms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * 供应商控制器
 * @author 89106
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;

import com.smbms.pojo.Provider;
import com.smbms.pojo.User;
import com.smbms.service.ProviderService;
/**
 * 供应商Controller
 * @author Mr.Yu
 *
 */
@Controller
@RequestMapping("provider")
public class ProviderController {
	@Autowired
	private ProviderService providerService;

	@RequestMapping("providerPage")
	public String provider(HttpSession session, HttpServletRequest request) {
		List<Provider> providers = providerService.providerGetAll();
		session.setAttribute("providerList", providers);
		return "/jsp/providerlist";
	}

	@RequestMapping("providerAdd")
	public String providerAdd(HttpSession session, HttpServletRequest request) {
		Provider provider = new Provider();
		String proCode = request.getParameter("proCode");// 供应商编码
		String proName = request.getParameter("proName");// 供应商名称
		String proContact = request.getParameter("proContact");// 联系人
		String proPhone = request.getParameter("proPhone");// 联系电话
		String proAddress = request.getParameter("proAddress");// 联系地址
		String proFax = request.getParameter("proFax");// 传真
		String proDesc = request.getParameter("proDesc");// 描述
		provider.setProCode(proCode);
		provider.setProName(proName);
		provider.setProContact(proContact);
		provider.setProPhone(proPhone);
		provider.setProAddress(proAddress);
		provider.setProFax(proFax);
		provider.setProDesc(proDesc);
		provider.setCreatedBy(((User) request.getSession().getAttribute("userSession")).getId());
		provider.setCreationDate(new Date());
		System.out.println("供应商");
		providerService.addBill(provider);
		System.out.println("添加");
		return "redirect:/provider/providerPage.action";
	}

	@RequestMapping("providerFind")
	public String providerFind(HttpSession session, HttpServletRequest request) {
		String proCode = request.getParameter("queryProCode");
		String proName = request.getParameter("queryProName");
		List<Provider> providerList = providerService.providerGetCN(proCode, proName);
		System.out.println("proCode:"+proCode);
		System.out.println("proName:"+proName);
		session.setAttribute("providerList", providerList);
		return "/jsp/providerlist";
	}
}
