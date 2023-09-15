package com.neusoft.elm.view.impl;

import java.util.List;
import java.util.Scanner;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.view.BusinessView;

public class BusinessViewImpl implements BusinessView{
	
	private Scanner input = new Scanner(System.in);
	
	@Override
	public void listBusinessAll() {
		BusinessDao  dao = new BusinessDaoImpl();
		List<Business> list = dao.listBusiness(null, null);
		System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
		for(Business b : list) {
			System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice());	
		}
	}
	
	@Override
	public void listBusiness() {
		
		String businessName = "";
		String businessAddress = "";
		
		String inputStr = "";
		System.out.println("是否需要输入商家名称关键词（y/n）：");
		inputStr = input.next();
		if(inputStr.equals("y")) {
			System.out.println("请输入商家名称关键词：");
			businessName = input.next();	
		}
		
		System.out.println("是否需要输入商家地址关键词（y/n）：");
		inputStr = input.next();
		if(inputStr.equals("y")) {
			System.out.println("请输入商家地址关键词：");
			businessAddress = input.next();	
		}
		
		BusinessDao dao = new BusinessDaoImpl();
		List<Business> list = dao.listBusiness(businessName, businessAddress);
		System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
		for(Business b : list) {
			System.out.println(b.getBusinessId()+"\t"+b.getBusinessName()+"\t"+b.getBusinessAddress()+"\t"+b.getBusinessExplain()+"\t"+b.getStarPrice()+"\t"+b.getDeliveryPrice());	
		}
		
	}
	
	@Override
	public void saveBusiness() {
		
		String businessName = "";
		System.out.println("请输入商家名称：");
		businessName = input.next();
		
		BusinessDao dao = new BusinessDaoImpl();
		int businessId = dao.saveBusiness(businessName);
		if(businessId > 0) {
			System.out.println("新建商家成功！ 商家编号为："+businessId);
		}else {
			System.out.println("新建商家失败！");
		}
	}
	
	@Override
	public void removeBusiness() {
		
		System.out.println("请输入商家编号：");
		int businessId = input.nextInt();
		System.out.println("确认要删除吗（y/n）：");
		if(input.next().equals("y")) {
			//声明放在这可以吗？
			BusinessDao dao = new BusinessDaoImpl();
			int result = dao.removeBusiness(businessId);
			if(result == 1) {
				System.out.println("删除商家成功！");
			}else {
				System.out.println("删除商家失败！");
			}
		}
	}
	
	@Override
	public Business login() {
		
		System.out.println("请输入商家编号：");
		int businessId = input.nextInt();
		System.out.println("请输入商家密码：");
		String businesspass = input.next();
		
		BusinessDaoImpl dao = new BusinessDaoImpl();
		Business business = dao.getBusinessByIdByPass(businessId, businesspass);
		return business;
	}
	
	@Override
	public void showBusiness(Integer businessId) {
		
		BusinessDaoImpl dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);
		System.out.println(business);
	}
	
	@Override
	public void editBusiness(Integer businessId) {
		//遇到更新得先展示原有信息
		BusinessDaoImpl dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);
		System.out.println(business);
		
		String inputStr = "";
		System.out.println("是否修改商家名称（y/n）；");
		inputStr = input.next();
		if(inputStr.equals("y")) {
			System.out.println("请输入新的商家名称；");
			business.setBusinessName(input.next());
		}
		
		System.out.println("是否修改商家地址（y/n）；");
		inputStr = input.next();
		if(inputStr.equals("y")) {
			System.out.println("请输入新的商家地址；");
			business.setBusinessAddress(input.next());
		}
		
		System.out.println("是否修改商家介绍（y/n）；");
		inputStr = input.next();
		if(inputStr.equals("y")) {
			System.out.println("请输入新的商家介绍；");
			business.setBusinessExplain(input.next());
		}
		
		System.out.println("是否修改起送费（y/n）；");
		inputStr = input.next();
		if(inputStr.equals("y")) {
			System.out.println("请输入新的起送费；");
			business.setStarPrice(input.nextDouble());
		}
		
		System.out.println("是否修改配送费（y/n）；");
		inputStr = input.next();
		if(inputStr.equals("y")) {
			System.out.println("请输入新的配送费；");
			business.setStarPrice(input.nextDouble());
		}
		
		int result = dao.updateBusiness(business);
		if(result > 0) {
			System.out.println("\n修改商家信息成功！\n");
		}else {
			System.out.println("\n修改商家信息失败！\n");
		}
	}
	
	@Override
	public void updateBusinessByPassword(Integer businessId) {
		
		BusinessDaoImpl dao = new BusinessDaoImpl();
		Business business = dao.getBusinessById(businessId);
		
		System.out.println("\n请输入旧密码：");
		String oldPassword = input.next();
		System.out.println("\n请输入新密码：");
		String newPassword = input.next();
		System.out.println("\n请再次输入新密码：");
		String reNewPassword = input.next();
		
		if(!business.getPassword().equals(oldPassword)) {
			System.out.println("\n旧密码输入错误!");
		}else if(!newPassword.equals(reNewPassword)){
			System.out.println("\n两次输入密码不一致!");
		}else {
			int result = dao.updateBusinessPassword(businessId, newPassword);
			if(result > 0) {
				System.out.println("\n修改密码成功！\n");
			}else {
				System.out.println("\n修改密码失败！\n");
			}
		}
		
	}
	
}
