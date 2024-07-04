package com.johannpando.springboot.reactor.app.base.operators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FlatMapOperator {

	private static final Logger log = LoggerFactory.getLogger(FlatMapOperator.class);

	public static void flatOperator() {
		List<String> userList = new ArrayList<>();
		userList.add("A");
		userList.add("B");
		userList.add("C");
		userList.add("D");

		Flux.fromIterable(userList).map(element -> element.toLowerCase()).flatMap(e -> {
			if (e.equalsIgnoreCase("C")) {
				return Mono.just(e);
			}
			return Mono.empty();
			
		}).subscribe(e -> log.info(e));
	}
	
	public static void explainFlattening() {
		Flux<Flux<String>> fluxOfFluxs = Flux.just(
		    Flux.just("A", "B", "C"),
		    Flux.just("D", "E"),
		    Flux.just("F", "G", "H")
		);
		
		fluxOfFluxs.subscribe(flux -> log.info("fluxOfFlux:: " + flux.toString()));

		Flux<String> flatternFlux = fluxOfFluxs.flatMap(flux -> flux);
		
		// java version 7
		/*
		 * Aquí, estamos creando una clase anónima 
		 * que implementa la interfaz Function<Flux<String>, Flux<String>> 
		 * con el método apply que simplemente devuelve el flujo que recibe.
		 * 
		   Flux<String> flatternFlux = fluxOfFluxs.flatMap(new Function<Flux<String>, Flux<String>>() {
		    @Override
		    public Flux<String> apply(Flux<String> flux) {
		        return flux;
		    }
		});*/

			
		flatternFlux.subscribe(e -> log.info(e));
		
		// java version 7
		/*
		 * De manera similar, estamos utilizando una clase anónima para la interfaz Consumer<String> 
		 * con el método accept para registrar cada elemento.
		 * 
		  flatternFlux.subscribe(new java.util.function.Consumer<String>() {
		    @Override
		    public void accept(String e) {
		        log.info(e);
		    }
		});*/

	}
	
	public static void combineMonoWithCallable() {
		// Operaciones síncronas representadas como Callables
        Callable<String> operation1 = () -> {
            // Simula una operación síncrona
            return "Hello";
        };

        Callable<String> operation2 = () -> {
            // Simula otra operación síncrona
            return "World";
        };

        // Crear Monos a partir de las operaciones Callables
        Mono<String> mono1 = Mono.fromCallable(operation1);
        Mono<String> mono2 = Mono.fromCallable(operation2);

        // Combinar los Monos usando flatMap
        Mono<String> combinedMono = mono1.flatMap(result1 ->
            mono2.map(result2 -> result1 + " " + result2)
        );

        // Suscribirse al Mono combinado y registrar el resultado
        combinedMono.subscribe(result -> log.info("Combined Result: " + result),
                               error -> log.error("Error: ", error));
	}

}
