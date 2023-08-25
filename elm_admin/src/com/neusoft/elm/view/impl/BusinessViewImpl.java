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
		System.out.println("�̼ұ��\t�̼�����\t�̼ҵ�ַ\t�̼ҽ���\t���ͷ�\t���ͷ�");
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
		System.out.println("�Ƿ���Ҫ�����̼����ƹؼ���(y/n):");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������̼����ƹؼ���:");
			businessName = input.next();
		}
		System.out.println("�Ƿ���Ҫ�����̼ҵ�ַ�ؼ���(y/n):");
		inputStr = input.next();
		if (inputStr.equals("y")) {
			System.out.println("�������̼ҵ�ַ�ؼ���:");
			businessAddress = input.next();
		}
		BusinessDao dao = new BusinessDaoImpl();
		List<Business> list = dao.listBusiness(businessName, businessAddress);
		System.out.println("�̼ұ��\t�̼�����\t�̼ҵ�ַ\t�̼ҽ���\t���ͷ�\t���ͷ�");
		for (Business b : list) {
			System.out.println(b.getBusinessId() + "\t" + b.getBusinessName() + "\t" + b.getBusinessAddress() + "\t"
					+ b.getBusinessExplain() + "\t" + b.getStarPrice() + "\t" + b.getDeliveryPrice());

		}

	}

	@Override
	public void saveBusiness() {
		// TODO Auto-generated method stub
		System.out.println("�������̼�����:");
		String businessName = input.next();
		BusinessDao dao = new BusinessDaoImpl();
		int businessId = dao.saveBusiness(businessName);
		if (businessId > 0) {
			System.out.println("�½��̼ҳɹ����̼ұ��Ϊ��" + businessId);
		} else {
			System.out.println("�½��̼�ʧ�ܣ�");
		}
	}

	@Override
	public void removeBusiness() {
		// TODO Auto-generated method stub
		System.out.println("�������̼ұ��:");
		int businessId = input.nextInt();
		BusinessDao dao = new BusinessDaoImpl();
		System.out.println("ȷ��Ҫɾ����(y/n):");
		if (input.next().equals("y")) {
			int result = dao.removeBusiness(businessId);
			if (result == 1) {
				System.out.println("ɾ���̼ҳɹ���");
			} else {
				System.out.println("ɾ���̼�ʧ�ܣ�");
			}
		}
	}
}
