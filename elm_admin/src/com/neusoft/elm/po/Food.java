package com.neusoft.elm.po;

public class Food {
	private int foodId;
	private String foodName;
	private String foodExplain;
	private double foodPrice;
	private int bussinessId;
	public String toString() {
		return "\n食品编号："+this.foodId+
			   "\n食品名称："+this.foodName+
			   "\n食品介绍："+this.foodExplain+
			   "\n食品价格："+this.foodPrice+
			   "\n所属商家："+this.bussinessId;
		
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodExplain() {
		return foodExplain;
	}
	public void setFoodExplain(String foodExplain) {
		this.foodExplain = foodExplain;
	}
	public double getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}
	public int getBusinessId() {
		return bussinessId;
	}
	public void setBusinessId(int bussinessId) {
		this.bussinessId = bussinessId;
	}
	

}