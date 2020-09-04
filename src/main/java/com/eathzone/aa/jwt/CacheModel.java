package com.eathzone.aa.jwt;

import java.io.Serializable;
import java.util.List;


public class CacheModel implements Serializable{
	
	private List<String> tokens;

	
	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}


	

	
	
	
}
