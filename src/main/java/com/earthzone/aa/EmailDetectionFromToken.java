package com.earthzone.aa;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class EmailDetectionFromToken {
	
	public static String getEmail(String userToken) {
		
		String key="securesecuresecuresecuresecuresecuresecure";		
		String token = userToken.replace("Bearer ", "");
		Jws<Claims> parseClaimsJws = Jwts.parser()
			.setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
			.parseClaimsJws(token);
		
		Claims body= parseClaimsJws.getBody();
		String email = body.getSubject();
		return email;
		
	}

}
