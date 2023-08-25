package com.neusoft.elm;

import java.util.Scanner;

import com.neusoft.elm.po.Admin;
import com.neusoft.elm.view.AdminView;
import com.neusoft.elm.view.BusinessView;
import com.neusoft.elm.view.impl.AdminViewImpl;
import com.neusoft.elm.view.impl.BusinessViewImpl;

public class ElmAdminEntry {
	public void work() {
		Scanner input = new Scanner(System.in);
		System.out.println("-------------------------------------------------");
		System.out.println("|\t\t ����ô��̨����ϵͳ \t\t|");
		System.out.println("-------------------------------------------------");
		// ��¼
		AdminView adminView = new AdminViewImpl();
		BusinessView businessView = new BusinessViewImpl();
		Admin admin = adminView.login();
		if (admin != null) {
			int menu = 0;
			// ������˵�
			while (menu != 5) {
				System.out.println("\n======1.�����̼��б�=2.�����̼�=3.�½��̼�=4.ɾ���̼�=5.�˳�ϵͳ======");
				System.out.println("���������ѡ��");
				menu = input.nextInt();
				switch (menu) {
				case 1:
					businessView.listBusinessAll();
					break;
				case 2:
					businessView.listBusiness();
					break;
				case 3:
					businessView.saveBusiness();
					break;
				case 4:
					businessView.removeBusiness();
					break;
				case 5:
					System.out.println("----------��ӭ�´ι��ٶ���ô�̼Һ�̨����ϵͳ----------");
					break;
				default:
					System.out.println("û�����ѡ��\n");

				}
			}
		}

		else {
			System.out.println("\n����Ա���ƻ������������\n");
		}
	}

	public static void main(String[] args) {
		new ElmAdminEntry().work();
	}
}
