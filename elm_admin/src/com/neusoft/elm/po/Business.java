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
		return "\n�̼ұ�ţ�"+this.businessId+
			   "\n�̼����ƣ�"+this.businessName+
			   "\n�̼ҵ�ַ��"+this.businessAddress+
			   "\n�̼ҽ��ܣ�"+this.businessExplain+
			   "\n���ͷѣ�"+this.starPrice+
			   "\n���ͷѣ�"+this.deliveryPrice;
		
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
