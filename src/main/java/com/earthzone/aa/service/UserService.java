package com.earthzone.aa.service;

import com.earthzone.aa.exception.CustomException;
import com.earthzone.aa.model.OTP;
import com.earthzone.aa.model.User;

public interface UserService {
	
	public String createRetailer(User user) throws CustomException;
	
	public User getBasicDetails(String email) throws CustomException;

	public String updatePassword(OTP otp,String email) throws CustomException;
	
	public String updateMobile(OTP otp, String email) throws CustomException;
	
	public String verifyOTP(String email,OTP otp) throws CustomException;
	
	public String getOTP(String email);
	
	public String logoutUser(String email,String token);
}
