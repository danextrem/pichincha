package com.demo.pichincha.service;

import org.springframework.stereotype.Service;

import com.demo.pichincha.model.User;

//import com.example.demo.model.Persona;
//import com.example.demo.repository.PersonaRepository;

import reactor.core.publisher.Mono;

@Service
public interface UserServ {

	public Mono<User> getUser();

}
