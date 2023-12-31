package com.neusoft.elm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.elm.dao.DeliveryAddressDao;
import com.neusoft.elm.dao.impl.DeliveryAddressDaoImpl;
import com.neusoft.elm.po.DeliveryAddress;
import com.neusoft.elm.service.DeliveryAddressService;
import com.neusoft.elm.util.DBUtil;

public class DeliveryAddressServiceImpl implements DeliveryAddressService{

	@Override
	public List<DeliveryAddress> listDeliveryAddressByUserId(String userId) {
		List<DeliveryAddress> list = new ArrayList<>();
		DeliveryAddressDao dao = new DeliveryAddressDaoImpl();
		try {
			DBUtil.getConnection();
			list = dao.listDeliveryAddressByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close();
		}
		return list;
	}
}
