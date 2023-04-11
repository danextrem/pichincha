package com.demo.pichincha.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {
	private AuthenticationManager authenticationManager;
	
	public SecurityContextRepository(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		
	}

	@Override
	public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Mono<SecurityContext> load(ServerWebExchange exchange) {		
		Mono<String> stringMono = Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
        return stringMono.flatMap(this::getSecurityContext);
		}
	private Mono<? extends SecurityContext> getSecurityContext(String token) {
        Authentication auth = new UsernamePasswordAuthenticationToken(token, "");
        return authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
    }

}
