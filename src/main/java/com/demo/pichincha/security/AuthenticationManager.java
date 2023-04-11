package com.demo.pichincha.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {
	private JwtUtil jwtUtil;
	public AuthenticationManager(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;		
	}

	@Override
	public Mono<Authentication> authenticate(Authentication authentication) {
		Claims claims = jwtUtil.validate(authentication.getPrincipal().toString().substring(7));
		return Mono.justOrEmpty(claims).map(t -> {
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (String role :claims.getOrDefault("group", claims).toString().replace("[", "").replace("]", "").split(",")) {
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			}
			return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
		});
	}	
	
	
	


	

}
