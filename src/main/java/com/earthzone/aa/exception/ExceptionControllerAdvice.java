package com.earthzone.aa.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.SQLGrammarException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.io.IOException;

@RestControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler{
	
//	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<ErrorMessage> handleError405(HttpRequestMethodNotSupportedException ex) {
//		ErrorMessage error = new ErrorMessage();
//		error.setErrorCode(HttpStatus.METHOD_NOT_ALLOWED.value());
//		error.setMessage(ex.getMessage());
//		return new ResponseEntity<>(error,HttpStatus.OK);
//
//    }
	
	
	@ExceptionHandler(SQLGrammarException.class)
	public ResponseEntity<ErrorMessage> sqlException(SQLGrammarException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.CONFLICT.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error,HttpStatus.OK);
	}
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorMessage> customException(CustomException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.CONFLICT.value());
		error.setMessage(ex.getMessage());
		System.out.println(ex.getLocalizedMessage()+ex.getMessage()+"HII");
		return new ResponseEntity<>(error,HttpStatus.OK);
	}
	

	
//	
//	@ExceptionHandler({Exception.class})
//	public ResponseEntity<ErrorMessage> exceptionHandler(Exception ex) {
//		ErrorMessage error = new ErrorMessage();
//		if(ex.getMessage().startsWith("Error ")) {
//			error.setErrorCode(Integer.parseInt(ex.getMessage().substring(6, 9)));
//		}
//		else {
//			error.setErrorCode(HttpStatus.BAD_REQUEST.value());
//		}
//		error.setMessage(ex.getMessage());
//		return new ResponseEntity<>(error, HttpStatus.OK);
//	}
	
//	@ExceptionHandler(ResourceNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String pageNotFound(Exception ex) {
//		ErrorMessage error = new ErrorMessage();
//		error.setErrorCode(HttpStatus.BAD_GATEWAY.value());
//		error.setMessage(ex.getMessage());
//        return "404";
//    }
}