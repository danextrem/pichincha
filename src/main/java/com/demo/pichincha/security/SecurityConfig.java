package com.demo.pichincha.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	
	private AuthenticationManager authenticationManager;
	private SecurityContextRepository securityContextRepository;
	
	public SecurityConfig(
			AuthenticationManager authenticationManager,
			SecurityContextRepository securityContextRepository) {
		this.authenticationManager = authenticationManager;
		this.securityContextRepository = securityContextRepository;
		
	}

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
    		ServerHttpSecurity http) throws Exception{
		return http
				.csrf().disable()
				.logout().disable()
				.formLogin().disable()
				.httpBasic().disable()
				.authenticationManager(authenticationManager)
				.securityContextRepository(securityContextRepository)
				.authorizeExchange()
				.pathMatchers("/v1/login/**").permitAll()
				.pathMatchers("/v1/exchange/**").hasAnyRole("USER","ADMIN")
				.anyExchange().authenticated()
				.and()
				.build();
    }  
}
