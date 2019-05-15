package com.smbms.service;

import java.util.List;

import com.smbms.pojo.Provider;

public interface ProviderService {

	public List<Provider> providerGetAll();

	/**
	 * 根据Bill表中的providerId供应商Id获取供应商Name
	 * 
	 * @param id
	 * @return
	 */
	Provider getProviderById(int id);

	List<Provider> providerGetCN(String proCode,String proName);

	int addBill(Provider provider);

}
