package com.neusoft.elm.view;

import com.neusoft.elm.po.Business;

public interface BusinessView {
	
	//全查询
	public void listBusinessAll();
	//多条件模糊查询
	public void listBusiness();
	//增添商家
	public void saveBusiness();
	//删除商家
	public void removeBusiness();
	//商家登录
	public Business login();
	//查看商家信息
	//因为是商家登录，所以知道了商家自己的ID，就不用再让商家输入ID查询了，所以有一个参数
	public void showBusiness(Integer businessId);
	//修改商家信息
	public void editBusiness(Integer businessId);
	//修改密码
	public void updateBusinessByPassword(Integer businessId);
	
}
