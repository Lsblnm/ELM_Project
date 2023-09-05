package com.neusoft.elm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.elm.po.Business;
import com.neusoft.elm.service.BusinessService;
import com.neusoft.elm.service.impl.BusinessServiceImpl;

/*controller包存所有业务控制器
当不同请求过来时，分发给不同controller处理*/

//业务控制器返回值一律是object（因为有很多不同对象），返回到前端控制器统一处理

//下面是对business的做请求处理的控制器
public class BusinessController {
	
	//所有业务控制器方法都这么声明！！
	public Object listBusinessByOrderTypeId(HttpServletRequest request) throws Exception{
		//request.getParameter返回指定参数名称的字符串
		Integer orderTypeId = Integer.valueOf(request.getParameter("orderTypeId"));
		BusinessService service = new BusinessServiceImpl();
		List<Business> list = service.listBusinessByOrderTypeId(orderTypeId);
		return list;
	}
	
	public Object getBusinessById(HttpServletRequest request) throws Exception{
		Integer businessId = Integer.valueOf(request.getParameter("businessId"));
		BusinessService service = new BusinessServiceImpl();
		Business business  = service.getBusinessById(businessId);
		return business;
	}
	
}
