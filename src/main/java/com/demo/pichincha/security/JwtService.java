package com.demo.pichincha.security;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.pichincha.model.User;
import com.demo.pichincha.repository.UserDetailsRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

@Service
public class JwtService {
	private JwtUtil jwtUtil;
	private PasswordEncoder encoder;
	private UserDetailsRepository userDetailsRepository;
	
	public JwtService (
			UserDetailsRepository userDetailsRepository,
			PasswordEncoder encoder,JwtUtil jwtUtil) {
		this.userDetailsRepository = userDetailsRepository;
		this.encoder = encoder;
		this.jwtUtil = jwtUtil;
	}
	
	Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("9aIuXvIyaVwC5VbQpvZFnDxIolVog6iEKR+66JiPFF4="));	

	public Mono<String> createToken(User user) {
		System.out.println("Inicia la creacion de clave");
		//System.out.println(Runtime.getRuntime().availableProcessors());		
		return userDetailsRepository.findByUsername(user.getEmail())
				.flatMap(t -> {
					System.out.println("Lo que llega de la base de datos" + t.getUsername() + t.getPassword());
					if (encoder.matches(user.getPassword(), t.getPassword())) {
						return Mono.just(Jwts.builder()
								.setSubject(t.getUsername())
								.setIssuedAt(new Date(System.currentTimeMillis()))
								.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
								.claim("group", t.getAuthorities().toString())
								.signWith(key,SignatureAlgorithm.HS256)						
								.compact());						
					}
					return Mono.just("No es el Usuario No existe: " + t.getUsername());
					
				});
	}
	
	
	
	public Mono<String> refresht(String token) {
		Claims claims = jwtUtil.validate(token);
		return Mono.justOrEmpty(claims)
				.flatMap(t -> {							
					List<GrantedAuthority> authorities = new ArrayList<>();
					for (String role :t.getOrDefault("group", t).toString().replace("[", "").replace("]", "").split(",")) {
						authorities.add(new SimpleGrantedAuthority(role));
					}					
				return Mono.just(Jwts.builder()
						.setSubject(t.getSubject())
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
						.claim("group", authorities.toString().replaceAll("\\s+",""))
						.signWith(key,SignatureAlgorithm.HS256)						
						.compact());
		});
	}	
	
	
	
	
	

}
