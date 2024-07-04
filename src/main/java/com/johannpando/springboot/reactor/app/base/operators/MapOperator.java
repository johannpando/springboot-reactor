package com.johannpando.springboot.reactor.app.base.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johannpando.springboot.reactor.app.models.User;

import reactor.core.publisher.Flux;

public class MapOperator {
	
	private static final Logger log = LoggerFactory.getLogger(MapOperator.class);
	
	public static void mapOperator() {
		Flux<User> names = Flux.just("Juan", "Ana", "Alberto", "Luis")
				// Transforma los datos emitidos por Flux
				.map(name -> new User(name.toUpperCase(), null))
				.doOnNext(user -> {
					if (user == null) {
						throw new RuntimeException("El nombre no puede ser vacío");
					}
					System.out.println(user.getName());
				})
				// Volvemos a transformar los datos emitidos por Flux
				.map(user -> {
					user.setName(user.getName().toLowerCase());
					return user;
				})
				.doOnNext(user -> System.out.println(user.getName()));
		
		// Nos subscribimos
		names.subscribe(name -> log.info(name.getName()),
				error -> log.error(error.getMessage()),
				new Runnable() {
					
					@Override
					public void run() {
						log.info("Ha finalizado la ejecución del observable");
					}
				});
	}

}
