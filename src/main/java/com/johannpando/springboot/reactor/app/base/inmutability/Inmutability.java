package com.johannpando.springboot.reactor.app.base.inmutability;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class Inmutability {
	
	private static final Logger log = LoggerFactory.getLogger(Inmutability.class);
	
	public static void inmutability() {	
		
		Flux<String> names = Flux.just("John", "Doe", "Maria");
		Flux<String> copyNames = names.map(name -> name.toUpperCase());
		
		names.subscribe(name -> log.info(name));
		copyNames.subscribe(name -> log.info(name));
		
	}
}
