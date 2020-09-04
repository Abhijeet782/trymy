package com.earthzone.aa.model;

import com.earthzone.aa.enums.States;

public class Retailer {
//	Aadhar,Pan, GST, Shop Details(Name, Address,Pin,City,State)
//	http://postalpincode.in/api/pincode/<your_pin_code>
	
	private String aadhaar;
	private String pan;
	private String gst;
	private String shopName;
	private String shopAddress;
	private Integer shopPincode;
	private String shopCity;
	private States shopState;
	
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getGst() {
		return gst;
	}
	public void setGst(String gst) {
		this.gst = gst;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Integer getShopPincode() {
		return shopPincode;
	}
	public void setShopPincode(Integer shopPincode) {
		this.shopPincode = shopPincode;
	}
	public String getShopCity() {
		return shopCity;
	}
	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}
	public States getShopState() {
		return shopState;
	}
	public void setShopState(States shopState) {
		this.shopState = shopState;
	}
	
	
	
	
}
