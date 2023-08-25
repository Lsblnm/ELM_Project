package com.neusoft.elm.view.impl;

import java.util.List;
import java.util.Scanner;

import com.neusoft.elm.dao.BusinessDao;
import com.neusoft.elm.dao.impl.BusinessDaoImpl;
import com.neusoft.elm.po.Business;
import com.neusoft.elm.view.BusinessView;

public class BusinessViewImpl implements BusinessView {
	private Scanner input = new Scanner(System.in);

	@Override
	public void listBusinessAll() {
		// TODO Auto-generated method stub
		BusinessDao dao = new BusinessDaoImpl();
		List<Business> list = dao.listBusiness(null, null);
		System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
		for (Business b : list) {
			System.out.println(b.getBusinessId() + "\t" + b.getBusinessName() + "\t" + b.getBusinessAddress() + "\t"
					+ b.getBusinessExplain() + "\t" + b.getStarPrice() + "\t" + b.getDeliveryPrice());

		}

	}

	@Override
	public void listBusiness() {
		// TODO Auto-generated method stub
		String inputStr = "";
		String businessName = "";
		String businessAddress = "";
		System.out.println("是否需要输入商家名称关键词(y/n):");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("请输入商家名称关键词:");
			businessName = input.next();
		}
		System.out.println("是否需要输入商家地址关键词(y/n):");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("请输入商家地址关键词:");
			businessAddress = input.next();
		}
		BusinessDao dao = new BusinessDaoImpl();
		List<Business> list = dao.listBusiness(businessName, businessAddress);
		System.out.println("商家编号\t商家名称\t商家地址\t商家介绍\t起送费\t配送费");
		for (Business b : list) {
			System.out.println(b.getBusinessId() + "\t" + b.getBusinessName() + "\t" + b.getBusinessAddress() + "\t"
					+ b.getBusinessExplain() + "\t" + b.getStarPrice() + "\t" + b.getDeliveryPrice());

		}

	}

	@Override
	public void saveBusiness() {
		// TODO Auto-generated method stub
		System.out.println("请输入商家名称:");
		String businessName = input.next();
		BusinessDao dao = new BusinessDaoImpl();
		int businessId = dao.saveBusiness(businessName);
		if (businessId > 0) {
			System.out.println("新建商家成功！商家编号为：" + businessId);
		} else {
			System.out.println("新建商家失败！");
		}
	}

	@Override
	public void removeBusiness() {
		// TODO Auto-generated method stub
		System.out.println("请输入商家编号:");
		int businessId = input.nextInt();
		BusinessDao dao = new BusinessDaoImpl();
		System.out.println("确认要删除吗(y/n):");
		if (input.next().equals("y")) {
			int result = dao.removeBusiness(businessId);
			if (result == 1) {
				System.out.println("删除商家成功！");
			} else {
				System.out.println("删除商家失败！");
			}
		}
	}
}
