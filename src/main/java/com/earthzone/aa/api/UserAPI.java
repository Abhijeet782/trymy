package com.earthzone.aa.api;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.earthzone.aa.EmailDetectionFromToken;
import com.earthzone.aa.exception.CustomException;
import com.earthzone.aa.exception.CustomSQLException;
import com.earthzone.aa.model.OTP;
import com.earthzone.aa.model.User;
import com.earthzone.aa.service.UserServiceImpl;

@RestController
@RequestMapping("api/v1/user")
public class UserAPI {
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@GetMapping
	public String helloWorld(@RequestHeader(value = "authorization",required = false) String userToken) {
		String email = EmailDetectionFromToken.getEmail(userToken);
		return email;
	}
	
	@PostMapping("create/retailer")
	public ResponseEntity<String> createUser(@RequestBody User user,
			@RequestHeader(value = "authorization",required = false) String userToken) throws Exception {
		try {
			if(userToken!=null) {
				String email = EmailDetectionFromToken.getEmail(userToken);
				if(email!=null) {
					throw new CustomException("Please Logout first and then create account");
				}
			}
			String response = userServiceImpl.createRetailer(user);
			return new ResponseEntity<String>(response,HttpStatus.OK);
			
		}
		catch(SQLGrammarException ex){
			throw new CustomSQLException(ex);
		}
		catch(Exception ex) {
			throw new Exception(ex);
		}
		
	}

	@GetMapping("details")
	public ResponseEntity<User> getBasicDetails(@RequestHeader(value = "authorization", required = true) String userToken) throws Exception {
		try {
			
			String email = EmailDetectionFromToken.getEmail(userToken);
			User response = userServiceImpl.getBasicDetails(email);
			return new ResponseEntity<User>(response,HttpStatus.OK);
			
		}
		catch(SQLGrammarException ex){
			throw new CustomSQLException(ex);
		}
		catch(Exception ex) {
			throw new Exception(ex);
		}
	}
	
	@GetMapping("getOtp")
	public ResponseEntity<String> getOTP(@RequestHeader(value = "authorization", required = true) String userToken) throws Exception{
		try {	
			String email = EmailDetectionFromToken.getEmail(userToken);
			 String response = userServiceImpl.getOTP(email);
			return new ResponseEntity<String>(response,HttpStatus.OK);	
		}
		catch(SQLGrammarException ex){
			throw new CustomSQLException(ex);
		}
		catch(Exception ex) {
			throw new Exception(ex);
		}
	}
	
	
	@PostMapping("verifyOtp")
	public ResponseEntity<String> verifyOTP(@RequestHeader(value = "authorization", required = true) String userToken,
			@RequestBody OTP otp) throws Exception{
		try {	
			String email = EmailDetectionFromToken.getEmail(userToken);
			System.out.println(email);
			String response = userServiceImpl.verifyOTP(email, otp);
			return new ResponseEntity<String>(response,HttpStatus.OK);	
		}
		catch(SQLGrammarException ex){
			throw new CustomSQLException(ex);
		}
		catch(Exception ex) {
			throw new Exception(ex);
		}

	}
	
	@GetMapping("logout")
	public ResponseEntity<String> logoutUser(@RequestHeader(value = "authorization", required = true) String userToken) throws Exception {
		try {
			String email = EmailDetectionFromToken.getEmail(userToken);
			userToken = userToken.replace("Bearer ", "");
			String response = userServiceImpl.logoutUser(email, userToken);
			
			return new ResponseEntity<String>(response,HttpStatus.OK);
			
		}
		catch(SQLGrammarException ex){
			throw new CustomSQLException(ex);
		}
		catch(Exception ex) {
			throw new Exception(ex);
		}
	}
	

	
}
