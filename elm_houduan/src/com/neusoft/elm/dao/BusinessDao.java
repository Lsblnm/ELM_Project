package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {
	
	//注意dao层不再处理异常，所以要抛出给service!!!
	public List<Business> listBusinessByOrderTypeId(Integer orderTypeId) throws Exception;
	public Business getBusinessById(Integer businessId) throws Exception;
	
}
