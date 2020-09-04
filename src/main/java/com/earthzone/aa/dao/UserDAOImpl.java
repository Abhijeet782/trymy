package com.earthzone.aa.dao;


import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.earthzone.aa.entity.UserEntity;
import com.earthzone.aa.enums.Role;
import com.earthzone.aa.model.OTP;
import com.earthzone.aa.model.User;
import com.earthzone.aa.sms.CreateSMS;

@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public String createRetailer(User user) {
		UserEntity entity=new UserEntity();
		entity.setEmail(user.getEmail());
		entity.setPassword(encoder.encode(user.getPassword()));
		entity.setName(user.getName());
		entity.setPin(encoder.encode(user.getPin()));
		entity.setMobile(user.getMobile());
		entity.setRole(Role.RETAILER);
		entity.setEnabled(true);
		entityManager.persist(entity);
		return "User created successfully";
	}
	
	
	@Override
	public User getBasicDetails(String email) {
//		Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.email=?1");
//		query.setParameter(1, email);
//		UserEntity userEntity = (UserEntity)query.getSingleResult();
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		User user=new User();
		user.setEmail(userEntity.getEmail());
		user.setName(userEntity.getName());
		user.setMobile(userEntity.getMobile());
		user.setRole(userEntity.getRole());
		return user;
	}

	@Override
	public Boolean checkUserExist(String email) {
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		if(userEntity!=null) {
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean checkMobileExist(Long mobile) {
		Query query = entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.mobile=?1");
		query.setParameter(1, mobile);
		UserEntity userEntity = (UserEntity)query.getSingleResult();
		if(userEntity!=null) {
			return true;
		}
		return false;
	}

	@Override
	public String updatePassword(OTP otp, String email) {
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		userEntity.setPassword(encoder.encode(otp.getPassword()));
		entityManager.persist(userEntity);
		return "Password Changed Successfully";
	}


	@Override
	public String updateMobile(OTP otp, String email) {
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		userEntity.setMobile(otp.getMobile());
		entityManager.persist(userEntity);
		return "Mobile Updated Successfully";
	}

	
	@Override
	public Integer createOTP() {
		
		SecureRandom random = new SecureRandom();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<6;i++) {
			sb.append(random.nextInt(10));
		}
		return Integer.valueOf(sb.toString());
	}

	@Override
	public void getOTP(String email, Integer otp) {
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		userEntity.setOtp(otp);
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat sdf= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String otpTime = sdf.format(dt);
		userEntity.setOtpTime(otpTime);
		CreateSMS.sendSMSGateway(email, userEntity.getMobile(), otp);
	}
	
	@Override
	public void deleteOTP(String email){

		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		userEntity.setOtp(null);
		userEntity.setOtpTime(null);
		
	}

	@Override
	public Boolean checkOtpPresent(String email) {
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		if(userEntity.getOtp()==null) {
			return false;
		}
		return true;
	}
	
	
	@Override
	public Boolean verifyOtpTime(String email) {
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		Date otpTime;
		try {
			otpTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(userEntity.getOtpTime());
			if(DateUtils.addMinutes(otpTime, 30).compareTo(new Date())<0) {
				return false;
			}		
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

	

	@Override
	public Boolean verifyPin(String email, String pin) {
		
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		if(!encoder.matches(pin, userEntity.getPin())) {
			return false;
		}
		return true;
	}


	@Override
	public Boolean verifyOtp(String email, String otp) {
		
		UserEntity userEntity = entityManager.find(UserEntity.class, email);
		if(!otp.equals(Integer.toString(userEntity.getOtp()))) {
			return false;
		}
		return true;
	}


	

}
