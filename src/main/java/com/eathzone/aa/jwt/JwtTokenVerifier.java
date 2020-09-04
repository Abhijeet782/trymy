package com.eathzone.aa.jwt;

import java.io.IOException;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ResponseStatusException;

import com.earthzone.aa.exception.CustomException;
import com.earthzone.aa.exception.ErrorMessage;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifier extends OncePerRequestFilter {

	public String email;



	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		
		String key="securesecuresecuresecuresecuresecuresecure";
		
		String authorizationHeader=request.getHeader("Authorization");
		if(authorizationHeader==null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		try {
			String token = authorizationHeader.replace("Bearer ", "");
//			System.out.println(jwtPropertiesConfiguration.getSecretKey());
			Jws<Claims> parseClaimsJws = Jwts.parser()
				.setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
				.parseClaimsJws(token);
			
			Claims body= parseClaimsJws.getBody();
			email = body.getSubject();
			
			//CacheManager
			JwtCacheManager jwtCacheManager=new JwtCacheManager();
			List<String> invalidTokens = jwtCacheManager.getAllInvalidCache(email);
			System.out.println("INVALID TOKENS VERIFY"+ invalidTokens.size());
			for(String t:invalidTokens) {
				if(t.equals(token)) {
					throw new JwtException("Old Token!!");
				}
			}
			
			
			List<Map<String,String>> roles = (List<Map<String,String>>)body.get("role");
			Set<SimpleGrantedAuthority> role = roles.stream().map(r -> new SimpleGrantedAuthority(r.get("authority"))).collect(Collectors.toSet());
			
			
			System.out.println(email);
			
			Authentication authentication = new UsernamePasswordAuthenticationToken(
					email, 	null, role);
			

			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		catch(JwtException ex) {
			throw new IllegalStateException("Invalid Token");
		}
//	    }catch (MalformedJwtException ex){
//	        System.out.println("Invalid JWT token");
//	    }catch (ExpiredJwtException ex){
//	        System.out.println("Expired JWT token");
//	        //request.setAttribute("expired",ex.getMessage());
//	    }catch(JwtException ex){
//	    	System.out.println("Old Token");
//	    	//request.setAttribute("old",ex.getMessage());
//	    }
		
		filterChain.doFilter(request, response);
		
		
	}

}
