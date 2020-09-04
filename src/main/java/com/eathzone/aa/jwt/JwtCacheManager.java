package com.eathzone.aa.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class JwtCacheManager {

	
	@Bean
	JedisConnectionFactory jedisConnectionFactory(){
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String, CacheModel> redisTemplate(){
		RedisTemplate<String, CacheModel> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	
	
	private HashOperations<String, String, CacheModel> hashOperations=redisTemplate().opsForHash();
	private String validHashKey="VALID_TOKENS";
	private String invalidHashKey="INVALID_TOKENS";
	
	public void createValidCache(String email,String token) {
		
		CacheModel model = new CacheModel();
		
		List<String> tokens = new ArrayList<>();
		if(hashOperations.get(validHashKey, email)!=null) {
			tokens = hashOperations.get(validHashKey, email).getTokens();
		}
		tokens.add(token);
		model.setTokens(tokens);
		hashOperations.put(validHashKey, email, model);
		
	}

	public List<String> getAllValidCache(String email){
		List<String> validTokens = hashOperations.get(validHashKey, email).getTokens();
		return validTokens;
	}
	
	public void deleteValidCache(String email) {
		hashOperations.delete(validHashKey, email);	
	}
	
	public void deleteLogoutCache(String email,String token) {
		CacheModel model = new CacheModel();
		if(hashOperations.get(validHashKey, email)!=null) {
			List<String> invalidTokens = getAllInvalidCache(email);
			List<String> validTokens = hashOperations.get(validHashKey, email).getTokens();
			
			validTokens.remove(token);
			model.setTokens(validTokens);
			hashOperations.put(validHashKey, email, model);
			
			invalidTokens.add(token);
			model.setTokens(invalidTokens);
			hashOperations.put(invalidHashKey, email, model);
		}
	}
	
	public void createInvalidCache(String email) {
			
			CacheModel model = new CacheModel();
			if(hashOperations.get(validHashKey, email)!=null) {
				List<String> invalidTokens = getAllInvalidCache(email);
				List<String> validTokens = hashOperations.get(validHashKey, email).getTokens();
				invalidTokens.addAll(validTokens);
				model.setTokens(invalidTokens);
				hashOperations.put(invalidHashKey, email, model);
				deleteValidCache(email);
			}
	}
	
	public List<String> getAllInvalidCache(String email){
		List<String> invalidTokens=new ArrayList<>();
		if(hashOperations.get(invalidHashKey, email)==null) {
			return invalidTokens;
		}
		invalidTokens = hashOperations.get(invalidHashKey, email).getTokens();
		return invalidTokens;
	}
	
	

}
