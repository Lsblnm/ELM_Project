package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Cart;

public interface CartDao {
	
	//���ﳵ�����һ����¼
	public int saveCart(Cart cart) throws Exception;
	public int updateCart(Cart cart) throws Exception;
	//�ӹ��ﳵ����ɾ��һ����¼
	public int removeCart(Cart cart) throws Exception;
	//��ѯ���ﳵ���е���Ƶ��Ϣ
	public List<Cart> listCart(Cart cart) throws Exception;	
}
