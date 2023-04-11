package com.demo.pichincha.security;

import java.security.Key;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	public Claims validate(String substring) {
		Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("9aIuXvIyaVwC5VbQpvZFnDxIolVog6iEKR+66JiPFF4="));		
		Claims claims = null;
		try {
			claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(substring).getBody();			
		} catch (JwtException e) {
			logger.info(e.getMessage());
		}
		return claims;
	}

}
