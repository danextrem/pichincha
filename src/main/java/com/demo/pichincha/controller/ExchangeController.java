package com.demo.pichincha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.pichincha.dto.TypeExchangeDto;
import com.demo.pichincha.service.ExchangeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1")
public class ExchangeController {
	Logger logger = LoggerFactory.getLogger(ExchangeController.class);
	
	private ExchangeService exchangeService;

	public ExchangeController(ExchangeService exchangeService) {
		this.exchangeService = exchangeService;
	}
	

	@GetMapping("/getAllCurrency")
	public Flux<String>getAllCurrency(){
		logger.info("Lita toda las Monedas");		
		Mono<String> mono1= Mono.just("Peru");
		Mono<String> mono2= Mono.just("Estados Unidos");	  
	    return Flux.merge(mono1,mono2);		
	}
	
	@GetMapping("/getByCointExchange")
	public Mono<TypeExchangeDto>getByCointExchange(
			@RequestParam String one,
			@RequestParam String two){
		logger.info("Muestra el tipo de cambio");
	    return exchangeService.getByCointExchange(one,two);
	}
	
	
	@PutMapping("/updateByCointExchange")
	public Mono<String>updateByCointExchange(
			@RequestBody TypeExchangeDto enchangeDto){
		logger.info("Actualiza un tipo de cambio " + enchangeDto);
		return exchangeService.updateByCointExchange(enchangeDto);
	}

}
