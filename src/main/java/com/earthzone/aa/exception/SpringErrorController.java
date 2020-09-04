package com.earthzone.aa.exception;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;


@RestController
public class SpringErrorController implements ErrorController{

	@GetMapping("/error")
	public ResponseEntity<ErrorMessage> error(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println(status.toString());
		ErrorMessage error=new ErrorMessage();
		if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        if(statusCode == HttpStatus.NOT_FOUND.value()) {
	        	error.setErrorCode(HttpStatus.NOT_FOUND.value());
	        	error.setMessage("Page Not Found!!");
	        }
	        else if(statusCode == HttpStatus.FORBIDDEN.value()) {
	        	error.setErrorCode(HttpStatus.FORBIDDEN.value());
	        	error.setMessage("Forbidden!!");  	
	        }
	        else if(statusCode == HttpStatus.UNAUTHORIZED.value()){
	        	error.setErrorCode(HttpStatus.UNAUTHORIZED.value());
	        	error.setMessage("Unauthorized!!"); 
	        }
//	        else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()){
//	        	error.setErrorCode(HttpStatus.METHOD_NOT_ALLOWED.value());
//	        	error.setMessage("Method Not Allowed!!"); 
//	        }
	        
	        
	    }
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.OK);
		
	}
	






	@Override
	public String getErrorPath() {
		return null;
	}

}

