package com.neusoft.elm.dao;

import com.neusoft.elm.po.Admin;

public interface AdminDao {
	
	//查询命名规则：返回一个对象用get开头，一个集合用List开头
	public Admin getAdminByNameByPass(String adminName, String password);
	
}
