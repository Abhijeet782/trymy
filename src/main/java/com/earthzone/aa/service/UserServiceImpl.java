package com.earthzone.aa.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earthzone.aa.dao.UserDAOImpl;
import com.earthzone.aa.exception.CustomException;
import com.earthzone.aa.model.OTP;
import com.earthzone.aa.model.User;
import com.earthzone.aa.validation.UserValidation;
import com.eathzone.aa.jwt.JwtCacheManager;

@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAOImpl userDAOImpl;
	
	@Override
	public String createRetailer(User user) throws CustomException {
		UserValidation.validateUser(user);
		if(userDAOImpl.checkUserExist(user.getEmail())) {
			throw new CustomException("User Already Exists!!");
		}
		if(userDAOImpl.checkMobileExist(user.getMobile())) {
			throw new CustomException("Mobile Already Exists!!");
		}
		return userDAOImpl.createRetailer(user);
	}
	
	
	@Override
	public User getBasicDetails(String email) throws CustomException {
		if(!userDAOImpl.checkUserExist(email)) {
			throw new CustomException("User Not Found!!");
		}
		return userDAOImpl.getBasicDetails(email);
	}

	@Override
	public String updatePassword(OTP otp, String email) throws CustomException {
		// TODO Add SMS Gateway
		if(!UserValidation.validatePassword(otp.getPassword())) {
			throw new CustomException("Password is not in proper format!!");
		}
		if(!userDAOImpl.checkUserExist(email)) {
			throw new CustomException("User Not Found");
		}
		String message=userDAOImpl.updatePassword(otp, email);
		JwtCacheManager jwtCacheManager=new JwtCacheManager();
		jwtCacheManager.createInvalidCache(email);
		return message;
	}

	@Override
	public String updateMobile(OTP otp, String email) throws CustomException {
		// TODO Add SMS Gateway
		if(!UserValidation.validateMobile(otp.getMobile())){
			throw new CustomException("Mobile number is not in correct format!!");
		}
		if(!userDAOImpl.checkUserExist(email)) {
			throw new CustomException("User Not Found");
		}
		if(userDAOImpl.checkMobileExist(otp.getMobile())){
			throw new CustomException("Mobile Number already exists!!");
		}
		return userDAOImpl.updateMobile(otp, email);
	}

	@Override
	public String getOTP(String email) {
		Integer otp = userDAOImpl.createOTP();
		userDAOImpl.getOTP(email, otp);
		return "OTP sent successfully "+otp;
	}

	@Override
	public String verifyOTP(String email, OTP otpObj) throws CustomException {
		if((otpObj.getMobile()!=null && otpObj.getPassword()!=null)
			|| (otpObj.getMobile()==null && otpObj.getPassword()==null))
		{
			throw new CustomException("Send Code along with Pass or Mobile");
		}
		if(!UserValidation.validateCode(otpObj.getCode())) {
			throw new CustomException("Code is in Incorrect format!!");
		}
		
		String otp = otpObj.getCode().substring(0,6);
		String pin = otpObj.getCode().substring(6);
		if(!userDAOImpl.checkOtpPresent(email)) {
			throw new CustomException("First send the OTP to verify!!");
		}
		else {
			if(!userDAOImpl.verifyOtpTime(email)) {
				throw new CustomException("Invalid OTP or Timeout");
			}
			else {
				if(!userDAOImpl.verifyOtp(email, otp)) {
					throw new CustomException("OTP or Pin is incorrect!!");
				}
				if(!userDAOImpl.verifyPin(email, pin)) {
					throw new CustomException("OTP or Pin is incorrect!!");
				}
			}
		}
		if(otpObj.getPassword()!=null) {
			updatePassword(otpObj, email);
		}
		else if(otpObj.getMobile()!=null) {
			updateMobile(otpObj, email);
		}
		userDAOImpl.deleteOTP(email);
		return "Updated Successfully";
	}


	@Override
	public String logoutUser(String email, String token) {
		JwtCacheManager jwtCacheManager=new JwtCacheManager();
		jwtCacheManager.deleteLogoutCache(email, token);
		return "Logout Successful";
	}
	

}
