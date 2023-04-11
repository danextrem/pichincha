package com.demo.pichincha.service;

import org.springframework.stereotype.Service;

import com.demo.pichincha.dto.TypeExchangeDto;
import com.demo.pichincha.model.Currency;
import com.demo.pichincha.repository.ExchangeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ExchangeService {
	private ExchangeRepository exchangeRepository;

	public ExchangeService(ExchangeRepository exchangeRepository) {
		this.exchangeRepository = exchangeRepository;
	}

	public Flux<Currency> getAllCurrency() {
		return null;
	}

	public Mono<TypeExchangeDto> getByCointExchange(String one, String two) {
		return exchangeRepository.getByCointExchange(one, two);
	}
	
	
	public Mono<String> updateByCointExchange(TypeExchangeDto enchangeDto ) {		
		Mono<TypeExchangeDto> mono = exchangeRepository.getByCointExchange(enchangeDto.getCoint_one().toString(), enchangeDto.getCoint_two().toString());
		 mono.subscribe(t -> {
			 System.out.println("Lo que se va a actualizar " + t.toString());			 
			 exchangeRepository.insertAuditExchange(t).subscribe();			 
		}); 
		return exchangeRepository.updateByCointExchange(enchangeDto);
	}

}
