package com.smbms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smbms.mapper.ProviderMapper;
import com.smbms.pojo.Provider;
import com.smbms.service.ProviderService;

@Service
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	ProviderMapper providerMapper;

	@Override
	public List<Provider> providerGetAll() {
		return providerMapper.getProviderAll();
	}

	@Override
	public Provider getProviderById(int id) {
		return providerMapper.getProviderById(id);
	}

	@Override
	public int addBill(Provider provider) {
		return providerMapper.providerAdd(provider);
	}

	@Override
	public List<Provider> providerGetCN(String proCode, String proName) {
		return providerMapper.getProviderByCN(proCode, proName);
	}

}
