package com.cts.model;

public class RechargePackage {
	private int id;
	private String packageName;
	private String carrierType;
	private int validity;
	private int price;
	public RechargePackage() {
		
	}
	public RechargePackage(int id, String packageName, String carrierType, int validity, int price) {
		super();
		this.id = id;
		this.packageName = packageName;
		this.carrierType = carrierType;
		this.validity = validity;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getCarrierType() {
		return carrierType;
	}
	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}
	public int getValidity() {
		return validity;
	}
	public void setValidity(int validity) {
		this.validity = validity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
