package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Business;

public interface BusinessDao {
	
	/*查询返回一个对象,增删改返回 int 
	 */
	
	//所有商家列表
	public List<Business> listBusiness();
	
	//搜索商家(多条件模糊查询)，利用查看所有商家列表来改
	public List<Business> listBusiness(String businessName, String businessAddress);
	
	//新建商家
	//新增的方法命名统一用"save"
	public int saveBusiness(String businessName);
	
	//删除商家
	//商家是主表，要先删次表food。可以在DB中设置级联删除，或者在java中删除
	public int removeBusiness(int businessId);
	
	//商家登录，返回一个商家对象
	public Business getBusinessByIdByPass(Integer businessId, String password);
	
	//查看商家信息
	public Business getBusinessById(Integer businessId);
	
	//修改商家信息
	//需要用到id，地址，名字，介绍，起配送费，共6个信息。太长了，所以不如直接传一个对象！
	public int updateBusiness(Business business);
	
	//更新密码
	public int updateBusinessPassword(Integer businessId, String password);
}
