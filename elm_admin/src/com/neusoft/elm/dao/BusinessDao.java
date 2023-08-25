package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {
	public List<Business> listBusiness(String businessName, String businessAddress);

	public int saveBusiness(String businessName);

	public int removeBusiness(int businessId);

}
