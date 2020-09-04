package com.eathzone.aa.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public class JwtPropertiesConfiguration {
	
	private String secretKey;
	private String tokenPrefix;
	private Integer tokenExpirationDays;
	private String authorizationHeader;
	
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getTokenPrefix() {
		return tokenPrefix;
	}
	public void setTokenPrefix(String tokenPrefix) {
		this.tokenPrefix = tokenPrefix;
	}
	public Integer getTokenExpirationDays() {
		return tokenExpirationDays;
	}
	public void setTokenExpirationDays(Integer tokenExpirationDays) {
		this.tokenExpirationDays = tokenExpirationDays;
	}
	public String getAuthorizationHeader() {
		return authorizationHeader;
	}
	public void setAuthorizationHeader(String authorizationHeader) {
		this.authorizationHeader = authorizationHeader;
	}
	
	

}
