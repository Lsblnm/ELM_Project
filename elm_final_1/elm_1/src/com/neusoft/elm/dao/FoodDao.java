package com.neusoft.elm.dao;

import java.util.List;

import com.neusoft.elm.po.Food;

public interface FoodDao {
	
	//查看食品列表
	public List<Food> listFoodByBusinessId(Integer businessId);
	//新增食品
	public int saveFood(Food food);
	//修改食品
	//两个函数：第一个由食品编号查出食品对象，第二个更新
	public Food getFoodById(Integer foodId);
	public int updateFood(Food food);
	//删除食品
	public int removeFood(Integer foodId);
}
