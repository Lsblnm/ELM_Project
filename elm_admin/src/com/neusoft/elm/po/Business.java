package com.neusoft.elm.po;

public class Business {
	private int businessId;
	private String password;
	private String businessName;
	private String businessAddress;
	private String businessExplain;
	private double starPrice;
	private double deliveryPrice;
	
	public String toString() {
		return "\n商家编号："+this.businessId+
			   "\n商家名称："+this.businessName+
			   "\n商家地址："+this.businessAddress+
			   "\n商家介绍："+this.businessExplain+
			   "\n起送费："+this.starPrice+
			   "\n配送费："+this.deliveryPrice;
		
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public String getBusinessExplain() {
		return businessExplain;
	}
	public void setBusinessExplain(String businessExplain) {
		this.businessExplain = businessExplain;
	}
	public double getStarPrice() {
		return starPrice;
	}
	public void setStarPrice(double starPrice) {
		this.starPrice = starPrice;
	}
	public double getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	

}
