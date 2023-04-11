package com.demo.pichincha.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.demo.pichincha.model.User;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class AuthenticatedController {
	Logger logger = LoggerFactory.getLogger(AuthenticatedController.class);

	private JwtService jwtService;

	public AuthenticatedController(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
	public Mono<String> createToken(@RequestBody User user) {
		logger.info("To Create Token");
		return jwtService.createToken(user);
	}

	@PostMapping("/refresh")
	public Mono<String> refresht(ServerWebExchange exchange) {
		return jwtService.refresht(
				exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION).toString().substring(7));
	}

}
