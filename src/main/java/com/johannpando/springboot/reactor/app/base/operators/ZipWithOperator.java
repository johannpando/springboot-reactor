package com.johannpando.springboot.reactor.app.base.operators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.johannpando.springboot.reactor.app.models.Comment;
import com.johannpando.springboot.reactor.app.models.User;
import com.johannpando.springboot.reactor.app.models.UserWithComments;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

public class ZipWithOperator {
	
	private static final Logger log = LoggerFactory.getLogger(ZipWithOperator.class);
	
	public static void operatorZipWith() {
		Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<Integer> flux2 = Flux.just(1, 2, 3);

        Flux<Tuple2<String, Integer>> combinedFlux = flux1
                .zipWith(flux2);

        combinedFlux.subscribe(result ->
                log.info("Combined Result: " + result)
        ); 
	}
	
	public static void operatorZipWith3FluxsI() {
		// Paso 1: Crear tres Flux
        Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<String> flux2 = Flux.just("D", "E", "F");
        Flux<String> flux3 = Flux.just("G", "H", "I");

        // Paso 2: Usar zipWith para combinar los Flux
        Flux<String> combinedFlux = flux1.zipWith(flux2, (s1, s2) -> s1 + s2)
                                         .zipWith(flux3, (s12, s3) -> s12 + s3);

        // Paso 3: Suscribirse al Flux combinado e imprimir los elementos
        combinedFlux.subscribe(System.out::println);
	}
	
	public static void operatorZipWith3FluxII() {
		// Paso 1: Crear los tres Flux
        Flux<String> flux1 = Flux.just("A", "B", "C");
        Flux<Integer> flux2 = Flux.just(1, 2, 3);
        Flux<Double> flux3 = Flux.just(0.1, 0.2, 0.3);

        // Paso 2: Usar zipWith para combinar los Flux
        Flux<String> combinedFlux = flux1.zipWith(flux2, (s1, i2) -> s1 + i2)
                                         .zipWith(flux3, (s12, d3) -> s12 + d3);

        // Paso 3: Suscribirse al Flux combinado e imprimir los elementos
        combinedFlux.subscribe(System.out::println);
	}
	
	public static void operatorZipWithObjects() {
		Mono<User> userMono = Mono.fromCallable(() -> new User("Johann","Pando"));
		
		Mono<Comment> commentsMono = Mono.fromCallable(() -> {
			Comment comment = new Comment();
			comment.addComment("Hello");
			comment.addComment("Bye");
			return comment;
		});
		
		// BiFunction
		userMono.zipWith(commentsMono, (user, comment) -> new UserWithComments(user, comment))
		.subscribe(uc -> log.info(uc.toString()));
	}
	
	public static void operatorZipWithObjectsAndMapAndTuples() {
		Mono<User> userMono = Mono.fromCallable(() -> new User("Johann","Pando"));
		
		Mono<Comment> commentsMono = Mono.fromCallable(() -> {
			Comment comment = new Comment();
			comment.addComment("Hello");
			comment.addComment("Bye");
			return comment;
		});
		
		Mono<UserWithComments> userWithComments = userMono
			.zipWith(commentsMono)
			.map(tuple -> {
				return new UserWithComments(tuple.getT1(), tuple.getT2());
			});
		
		userWithComments.subscribe(uc -> log.info(uc.toString()));
	}

}
