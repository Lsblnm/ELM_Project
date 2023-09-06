package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Cart;

public interface CartDao {
	
	//向购物车表添加一条记录
	public int saveCart(Cart cart) throws Exception;
	public int updateCart(Cart cart) throws Exception;
	//从购物车表中删除一条记录
	public int removeCart(Cart cart) throws Exception;
	//查询购物车表中的视频信息
	public List<Cart> listCart(Cart cart) throws Exception;	
}
