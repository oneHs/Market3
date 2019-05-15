package com.smbms.controller;

import java.math.BigDecimal; 
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.smbms.pojo.Bill;
import com.smbms.pojo.Provider;
import com.smbms.pojo.User;
import com.smbms.service.BillService;
import com.smbms.service.ProviderService;
/**
 * 订单Controller
 * @author Mr.Yu
 *
 */
@Controller
@RequestMapping("bill")
public class BillController {
	@Autowired
	BillService billService;

	@Autowired
	ProviderService providerService;

	/**
	 * 进入到订单管理
	 * 
	 * @return
	 */
	@RequestMapping("billPage")
	public String billOrder(HttpSession session, HttpServletRequest request) {
		// 获取bill所有信息
		List<Bill> bills = billService.billGetall();
		// 遍历Bill然后根据providerId获取providerName 在设置到providerName上
		for (Bill bill : bills) {
			Integer providerId = bill.getProviderId();
			Provider provider = providerService.getProviderById(providerId);
			bill.setProviderName(provider.getProName());
		}
		// 获取provider所有信息
		List<Provider> providerList = providerService.providerGetAll();
		// 放到foreach中的ProviderList属性里
		session.setAttribute("providerList", providerList);
		// 把所有bill信息放到foreach中的BillList属性中
		session.setAttribute("billList", bills);

		return "/jsp/billlist";
	}

	/**
	 * 动态查询
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/billFind")
	public String billFind(HttpSession session, HttpServletRequest request) {
		// 获取控制台queryProductName信息
		String queryProductName = request.getParameter("queryProductName");
		System.err.println(queryProductName);
		// 获取控制台queryProviderId信息
		Integer providerIdz = Integer.parseInt(request
				.getParameter("queryProviderId"));
		System.err.println(providerIdz);
		// 获取控制台isPayment信息
		Integer isPayment = Integer.parseInt(request
				.getParameter("queryIsPayment"));
		// 把获取控制台的值放入billGetPPI方法中去判定
		List<Bill> billList = billService.billGetPPI(queryProductName,
				providerIdz, isPayment);
		// 遍历Bill然后根据providerId获取providerName 在设置到providerName上
		for (Bill bill : billList) {
			Integer providerId = bill.getProviderId();
			Provider provider = providerService.getProviderById(providerId);
			bill.setProviderName(provider.getProName());
		}
		// 把所有bill信息放到foreach中的BillList属性中
		session.setAttribute("billList", billList);
		return "/jsp/billlist";
	}

	@RequestMapping(value = "billAdd")
	public String billAdd(HttpServletRequest request) {
		Bill bill = new Bill();
		String billCode = request.getParameter("billCode");
		String productName = request.getParameter("productName");
		String productUnit = request.getParameter("productUnit");
		String productCount = request.getParameter("productCount");
		String totalPrice = request.getParameter("totalPrice");
		String providerId = request.getParameter("providerId");
		String isPayment = request.getParameter("isPayment");
		String productDesc = request.getParameter("productDesc");
		bill.setBillCode(billCode);
		bill.setProductName(productName);
		bill.setProductUnit(productUnit);
		bill.setProductCount(new BigDecimal(productCount).setScale(2,
				BigDecimal.ROUND_DOWN));
		bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,
				BigDecimal.ROUND_DOWN));
		bill.setProviderId(Integer.parseInt(providerId));
		bill.setIsPayment(Integer.parseInt(isPayment));
		bill.setCreatedBy(((User) request.getSession().getAttribute(
				"userSession")).getId());
		bill.setProductDesc(productDesc);
		System.out.println(bill);
		bill.setCreationDate(new Date());
		billService.addBill(bill);
		return "redirect:/bill/billPage.action";
	}

	@RequestMapping(value = "/getProviders", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String allProviders() {
		List<Provider> providerList = providerService.providerGetAll();
		System.out.println(providerList);
		String json = JSON.toJSONStringWithDateFormat(providerList,
				"yyyy-MM-dd-hh-mm-ss");
		return json;
	}

	@RequestMapping("billShowAll")
	public String billShowAll(HttpServletRequest request) {
		String billId = request.getParameter("billid");
		Bill bill = billService.findBillById(Integer.parseInt(billId));
		Integer providerId = bill.getProviderId();
		Provider provider = providerService.getProviderById(providerId);
		bill.setProviderName(provider.getProName());
		request.setAttribute("bill", bill);
		return "/jsp/billview";
	}

	@RequestMapping("/billDetail")
	public String billDetail(HttpServletRequest request, HttpSession session) {
		String billId = request.getParameter("billid");
		Bill bill = billService.findBillById(Integer.parseInt(billId));
		Provider provider = providerService.getProviderById(bill
				.getProviderId());
		bill.setProviderName(provider.getProName());
		request.setAttribute("bill", bill);
		return "jsp/billmodify";
	}

	@RequestMapping("/billUpdate")
	public String billUpdat(HttpServletRequest request) {
		Bill bill = new Bill();
		String id = request.getParameter("id");
		String productName = request.getParameter("productName");
		String productDesc = request.getParameter("productDesc");
		String productUnit = request.getParameter("productUnit");
		String productCount = request.getParameter("productCount");
		String totalPrice = request.getParameter("totalPrice");
		String providerId = request.getParameter("providerId");
		String isPayment = request.getParameter("isPayment");
		bill.setId(Integer.parseInt(id));
		bill.setProductName(productName);
		bill.setProductDesc(productDesc);
		bill.setProductUnit(productUnit);
		bill.setProductCount(new BigDecimal(productCount).setScale(2,
				BigDecimal.ROUND_DOWN));
		bill.setIsPayment(Integer.parseInt(isPayment));
		bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2,
				BigDecimal.ROUND_DOWN));
		bill.setProviderId(Integer.parseInt(providerId));
		bill.setModifyBy(((User) request.getSession().getAttribute(
				"userSession")).getId());
		bill.setModifyDate(new Date());
		billService.modifyBillById(bill);
		return "redirect:/bill/billPage.action";
	}

	@RequestMapping(value = "billDel", produces = ("application/json;charset=UTF-8"))
	// value提供进一步的映射信息 produces设置返回数据的类型以及编码 可以是JSON或者是XML
	@ResponseBody
	public String billDel(HttpServletRequest request) {
		String id = request.getParameter("billid");
		System.err.println(id);
		boolean flag = billService.delBillById(Integer.parseInt(id));
		String json = JSON.toJSONStringWithDateFormat(flag,
				"yyyy-MM-dd-hh-mm-ss");
		//System.out.println(json);
		return json;
	}

}
