package com.eathzone.aa.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

//public class JwtExceptionHandler implements AuthenticationEntryPoint{
//
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		final String expired = (String) request.getAttribute("expired");
//		final String old = (String) request.getAttribute("old");
//        if (expired!=null){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,expired);
//        }
//        else if(old!=null){
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Old Token");
//        }
//        else {
//        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid Login details");
//        }
//	}
//
//}
