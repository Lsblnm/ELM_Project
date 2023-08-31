package com.neusoft.elm.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.neusoft.elm.dao.CartDao;
import com.neusoft.elm.dao.OrderDetailetDao;
import com.neusoft.elm.dao.OrdersDao;
import com.neusoft.elm.dao.impl.CartDaoImpl;
import com.neusoft.elm.dao.impl.OrderDetailetDaoImpl;
import com.neusoft.elm.dao.impl.OrdersDaoImpl;
import com.neusoft.elm.po.Cart;
import com.neusoft.elm.po.OrderDetailet;
import com.neusoft.elm.po.Orders;
import com.neusoft.elm.service.OrdersService;
import com.neusoft.elm.util.DBUtil;

public class OrdersServiceImpl implements OrdersService {
	@Override
	public int createOrders(String userId, Integer businessId, Integer daId, Double orderTotal) {
		int orderId = 0;

		CartDao cartDao = new CartDaoImpl();
		OrdersDao ordersDao = new OrdersDaoImpl();
		OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();

		try {
			DBUtil.beginTransaction(); // ����һ������

			// 1����ѯ��ǰ�û����ﳵ�е�ǰ�̼ҵ�����ʳƷ��Ŀ���Ƿ��붩����ϸ�У�
			Cart cart = new Cart();
			cart.setUserId(userId);
			cart.setBusinessId(businessId);
			List<Cart> cartList = cartDao.listCart(cart);

			// 2����������������ȡ�������
			Orders orders = new Orders();
			orders.setUserId(userId);
			orders.setBusinessId(businessId);
			orders.setDaId(daId);
			orders.setOrderTotal(orderTotal);
			orderId = ordersDao.saveOrders(orders);

			// 3���򶩵���ϸ�������������ϸ����
			List<OrderDetailet> orderDetailetList = new ArrayList();
			for (Cart c : cartList) {
				OrderDetailet od = new OrderDetailet();
				od.setOrderId(orderId);
				od.setFoodId(c.getFoodId());
				od.setQuantity(c.getQuantity());
				orderDetailetList.add(od);
			}
			orderDetailetDao.saveOrderDetailetBatch(orderDetailetList);

			// 4����չ��ﳵ����������ǰ�û�����ǰ�̼ң�
			cartDao.removeCart(cart);

			DBUtil.commitTransaction(); // �ύһ������
		} catch (Exception e) {
			orderId = 0;
			try {
				DBUtil.rollbackTransaction(); // �ع�һ������
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return orderId;
	}

	@Override
	public Orders getOrdersById(Integer orderId) {
		Orders orders = null;

		OrdersDao ordersDao = new OrdersDaoImpl();
		OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();

		try {
			DBUtil.getConnection();

			// 1�����ݶ���ID��ѯ������Ϣ�����һ���̼ң�
			orders = ordersDao.getOrdersById(orderId);

			// 2�����ݶ���ID��ѯ������ϸ��Ϣ
			List<OrderDetailet> list = orderDetailetDao.listOrderDetailetByOrderId(orderId);
			orders.setList(list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return orders;
	}

	@Override
	public List<Orders> listOrdersByUserId(String userId) {
		List<Orders> list = new ArrayList<>();

		OrdersDao ordersDao = new OrdersDaoImpl();
		OrderDetailetDao orderDetailetDao = new OrderDetailetDaoImpl();

		try {
			DBUtil.getConnection();

			// 1�������û�ID��ѯ������Ϣ�����һ���̼ң�
			list = ordersDao.listOrdersByUserId(userId);

			// 2����ѯ��������Ķ�����ϸ��Ϣ
			for (Orders o : list) {
				List<OrderDetailet> odList = orderDetailetDao.listOrderDetailetByOrderId(o.getOrderId());
				o.setList(odList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
}
