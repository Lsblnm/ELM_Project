package com.neusoft.elm.view.impl;

import java.util.Scanner;

import com.neusoft.elm.dao.AdminDao;
import com.neusoft.elm.dao.impl.AdminDaoImpl;
import com.neusoft.elm.po.Admin;
import com.neusoft.elm.view.AdminView;

public class AdminViewImpl implements AdminView {
	private Scanner input = new Scanner(System.in);

	@Override
	public Admin login() {
		// TODO Auto-generated method stub
		System.out.println("���������Ա���ƣ�");
		String adminName = input.next();
		System.out.println("���������룺");
		String password = input.next();
		AdminDao dao = new AdminDaoImpl();
		return dao.getAdminByNameByPass(adminName, password);
	}

}
