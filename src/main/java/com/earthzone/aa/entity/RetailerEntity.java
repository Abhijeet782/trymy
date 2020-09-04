package com.earthzone.aa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.earthzone.aa.enums.States;

@Entity
@Table(name="RETAILER")
public class RetailerEntity {

	@Id
	private String email;
	
	private String aadhaar;
	private String pan;
	private String gst;
	private String shopName;
	private String shopAddress;
	private Integer shopPincode;
	private String shopCity;
	private States shopState;
	private Boolean verified;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	
	
	
}
