package com.smbms.service;

import java.util.List;

import com.smbms.pojo.Bill;

public interface BillService {
	/**
	 * 获取所有Bill表中的信息
	 * 
	 * @param bill
	 * @return
	 */
	List<Bill> billGetall();

	/**
	 * 动态sql根据三个条件获取bill表中的信息
	 * 
	 * @param productName
	 * @param ProviderName
	 * @param pay
	 * @return
	 */
	List<Bill> billGetPPI(String productName, int providerId, int isPayment);

	/**
	 * 根据billId获取bill表中的所有数据然后显示到页面上
	 * 
	 * @param billId
	 * @return
	 */
	Bill findBillById(int billId);

	int addBill(Bill bill);

	int modifyBillById(Bill bill);

	// int updateBill(Bill bill);

	boolean delBillById(int id);
}
