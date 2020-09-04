package com.earthzone.aa.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;


import com.earthzone.aa.enums.Role;

@Entity
@Table(name = "USER")
public class UserEntity {

	@Id
	private String email;
	
	private String password;
	
	private String name;
	
	private Long mobile;
	
	private Boolean enabled;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private String pin;
	
	private Integer otp;
	
	private String otpTime;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(Integer otp) {
		this.otp = otp;
	}
	public String getOtpTime() {
		return otpTime;
	}
	public void setOtpTime(String otpTime) {
		this.otpTime = otpTime;
	}
	
	
	
}
