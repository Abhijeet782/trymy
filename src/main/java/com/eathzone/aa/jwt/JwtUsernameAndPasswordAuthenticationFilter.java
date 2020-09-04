package com.eathzone.aa.jwt;

import java.io.IOException;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private AuthenticationManager authenticationManager ;
	
	public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}



	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			
			UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
					.readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
	
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(),
					authenticationRequest.getPassword());
			return authenticationManager.authenticate(authentication);
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e.getMessage());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	
	}



	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String key="securesecuresecuresecuresecuresecuresecure";
		
		Date currentDate = new Date();
		Calendar expDate = Calendar.getInstance();
		expDate.setTime(currentDate);
		expDate.add(Calendar.DATE, 7);
        
		String token = Jwts.builder()
			.setSubject(authResult.getName())
			.claim("role",authResult.getAuthorities())
			.setIssuedAt(currentDate)
			.setExpiration(expDate.getTime())
			.signWith(Keys.hmacShaKeyFor(key.getBytes()))
			.compact();
		response.addHeader("Authorization", "Bearer "+token);
		
		JwtCacheManager jwtCacheManager=new JwtCacheManager();
		jwtCacheManager.createValidCache(authResult.getName(),token);
		
		

	}

	
	
}
