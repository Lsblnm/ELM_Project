package com.neusoft.elm.po;

public class Food {
	private int foodId;
	private String foodName;
	private String foodExplain;
	private double foodPrice;
	private int bussinessId;
	public String toString() {
		return "\nʳƷ��ţ�"+this.foodId+
			   "\nʳƷ���ƣ�"+this.foodName+
			   "\nʳƷ���ܣ�"+this.foodExplain+
			   "\nʳƷ�۸�"+this.foodPrice+
			   "\n�����̼ң�"+this.bussinessId;
		
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
	public int getBussinessId() {
		return bussinessId;
	}
	public void setBussinessId(int bussinessId) {
		this.bussinessId = bussinessId;
	}
	

}
