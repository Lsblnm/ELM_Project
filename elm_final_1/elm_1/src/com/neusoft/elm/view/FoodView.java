package com.neusoft.elm.view;

import java.util.List;

import com.neusoft.elm.po.Food;

public interface FoodView {
	//食品列表
	public List<Food> showFoodList(Integer businessId);
	//新增食品
	public void saveFood(Integer businessId);
	//修改食品
	public void updateFood(Integer businessId);
	//删除食品
	public void removeFood(Integer businessId);
}
