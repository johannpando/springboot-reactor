package com.johannpando.springboot.reactor.app.base.operators;


import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class CreateOperator {
	
	private static final Logger log = LoggerFactory.getLogger(CreateOperator.class);
	
	public static void createOperatorExample() {
		Flux.create(emitter -> {
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {

				private Integer count = 0;
				@Override
				public void run() {
					emitter.next(++count);
					if(count == 5) {
						timer.cancel();
						emitter.complete();
					}
					
					if (count == 2) {
						timer.cancel();
						emitter.error(new InterruptedException("ERROR: The flux has stopped in " + count));
					}
				}
			}, 1000, 1000);
		})
		.subscribe(number -> log.info(number.toString()),
			error -> log.error(error.getMessage()),
			() -> log.info("We finished!"));
	}

}
