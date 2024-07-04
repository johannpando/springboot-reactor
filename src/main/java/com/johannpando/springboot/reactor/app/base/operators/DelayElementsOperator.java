package com.johannpando.springboot.reactor.app.base.operators;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class DelayElementsOperator {
	
	private static final Logger log = LoggerFactory.getLogger(DelayElementsOperator.class);
	
	public static void delayElementsExample() {
		Flux<Integer> range = Flux.range(1, 12)
			.delayElements(Duration.ofSeconds(1))
			.doOnNext(e -> log.info(e.toString()));
		
		// In this case, the method subscribe does not print the elements
		range.subscribe();
		
		// If we need to print the elements could be to use the "blockLast" method. It is not recommended to use it.
		range.blockLast();
	}

}
