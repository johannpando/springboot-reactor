package com.johannpando.springboot.reactor.app.base.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johannpando.springboot.reactor.app.models.User;

import reactor.core.publisher.Flux;

public class FilterOperator {
	
	private static final Logger log = LoggerFactory.getLogger(FilterOperator.class);
	
	public static void filterOperator() {
		Flux<User> names = Flux.just("Juan", "Ana", "Alberto", "Luis")
				// It transforms the data emitted by Flux
				.map(name -> new User(name.toUpperCase(), null))
				// Evalúa una expresión booleana
				.filter(user -> user.getName().equalsIgnoreCase("Juan"))
				.doOnNext(user -> {
					if (user == null) {
						throw new RuntimeException("The name can't be empty");
					}
					System.out.println("First map:: " + user.getName());
				})
				// We transform the data emitted by Flux again
				.map(user -> {
					user.setName(user.getName().toLowerCase());
					return user;
				})
				.doOnNext(user -> System.out.println("Second map:: " + user.getName()));
		
		// We subscribe
		names.subscribe(name -> log.info(name.getName()),
				error -> log.error(error.getMessage()),
				new Runnable() {
					
					@Override
					public void run() {
						log.info("The execution by Observable has been terminated");
					}
				});
	}

}
