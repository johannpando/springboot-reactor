package com.johannpando.springboot.reactor.app.base.operators;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class RangeOperator {
	
	private static final Logger log = LoggerFactory.getLogger(RangeOperator.class);
	
	public static void rangeOperatorExample() {
		Flux.just(1, 2, 3, 4)
		.map(i -> (i*2))
		.zipWith(Flux.range(0, 2), (first, second) -> String.format("First Flux %d, Second Flux %d", first, second))
		.subscribe(text -> log.info(text));
	}

}
