package com.earthzone.aa.dao;

import com.earthzone.aa.model.OTP;
import com.earthzone.aa.model.User;

public interface UserDAO {
	
	public String createRetailer(User user);
	
	public User getBasicDetails(String email);
	
	public Boolean checkUserExist(String email);
	
	public Boolean checkMobileExist(Long email);

	public String updatePassword(OTP otp,String email);
	
	public String updateMobile(OTP otp, String email);
	
	public Integer createOTP();
	
	public void getOTP(String email,Integer otp);
	
	public void deleteOTP(String email);
	
	public Boolean checkOtpPresent(String email);
	
	public Boolean verifyOtpTime(String email);
	
	public Boolean verifyPin(String email,String pin);
	
	public Boolean verifyOtp(String email,String otp);
	
	
	
}
