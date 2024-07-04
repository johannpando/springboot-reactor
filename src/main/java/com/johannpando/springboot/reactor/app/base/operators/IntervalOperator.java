package com.johannpando.springboot.reactor.app.base.operators;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class IntervalOperator {
	
	private static final Logger log = LoggerFactory.getLogger(IntervalOperator.class);
	
	public static void intervalExample() throws InterruptedException {
		
		CountDownLatch latch = new CountDownLatch(1);
		
		Flux.interval(Duration.ofSeconds(1))
		.flatMap(i -> {
			if(i == 5) {
				return Flux.error(new InterruptedException("Just to 5"));
			}
			return Flux.just(i);
		})
		.map(i -> "Hello " + i)
		.retry(2)
		.doOnTerminate(latch::countDown)
		.subscribe(s -> log.info(s),
			e -> log.error(e.getMessage()));
		
		latch.await();
	}

}
