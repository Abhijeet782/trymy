package com.earthzone.aa.validation;

import com.earthzone.aa.exception.CustomException;
import com.earthzone.aa.model.User;

public class UserValidation {

	public static void validateUser(User user) throws CustomException {
		if(!validateEmail(user.getEmail())) {
			throw new CustomException("Email is Incorrect!!");
		}
		if(!validateMobile(user.getMobile())) {
			throw new CustomException("Mobile No is Incorrect!!");
		}
		if(!validateName(user.getName())) {
			throw new CustomException("Enter Proper Name");
		}
		if(!validatePassword(user.getPassword())) {
			throw new CustomException("Password not in Proper format");
		}
		
	}
	
	public static boolean validateEmail(String email) {
		if(email.matches("[A-Za-z0-9._]+@[A-Za-z]+(\\.[A-Za-z]+)+")) {
			return true;
		}
		return false;
	}
	
	public static boolean validatePassword(String password) {
		if(password.matches(".*[0-9].*") && password.matches(".*[a-z].*") && password.matches(".*[A-Z].*")
				&& password.matches(".*[@#$%^&+=].*") && password.matches(".{8,}")) {
			return true;
		}
		return false;
	}
	
	public static boolean validateName(String name) {
		if(name.matches("[A-Z][a-z]{1,}\\s[A-Z][a-z]+")) {
			return true;
		}
		return false;
	}
	
	public static boolean validateMobile(Long mobile) {
		String mob = Long.toString(mobile);
		if(mob.matches("[6789][0-9]{9}")) {
			return true;
		}
		return false;
	}
	
	public static boolean validateCode(String code) {
		
		if(code.matches("[0-9]{10}")) {
			return true;
		}
		return false;
	}
}
