package com.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.mapper.BillMapper;
import com.smbms.pojo.Bill;
import com.smbms.service.BillService;
@Service
public class BillServiceImpl implements BillService {


	@Autowired
	private BillMapper billMapper;

	@Override
	public List<Bill> billGetall() {
		return billMapper.getBillAll();
	}

	@Override
	public List<Bill> billGetPPI(String productName,int providerId, int isPayment) {
		return billMapper.getBillByPPI(productName, providerId, isPayment);
	}

	@Override
	public Bill findBillById(int billId) {
		return billMapper.getBillById(billId);
	}

	@Override
	public int addBill(Bill bill) {
		return billMapper.insBill(bill);
	}

	/*@Override
	public int updateBill(Bill bill) {
		return billMapper.upBill(bill);
	}*/

	  @Override
	    public int modifyBillById(Bill bill) {
	        return billMapper.updBillById(bill);
	    }

	@Override
	public boolean delBillById(int id) {
		return billMapper.billDelById(id);
	}
}
