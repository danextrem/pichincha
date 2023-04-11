package com.demo.pichincha.repository;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

@Repository
public class UserDetailsRepository  {

	private ConnectionFactory connectionFactory;

	public UserDetailsRepository( ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public Mono<User> findByUsername(String email ) {
		//return null;
		return Mono.from(connectionFactory.create())
		.flatMapMany(connection -> connection
				.createStatement("SELECT email, password, rol FROM Users where email=$1")
				.bind("$1", email)
				.execute())
		.flatMap(result -> result
				.map((row, rowMetadata) ->
				new User(row.get("email",String.class),
						row.get("password",String.class),
						//Arrays.asList(new SimpleGrantedAuthority("ADMIN,USER")))))
						Arrays.asList(new SimpleGrantedAuthority(row.get("rol",String.class))))))
		
		.next();
	}
	
	


}
