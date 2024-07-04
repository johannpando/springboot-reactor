package com.johannpando.springboot.reactor.app.base.operators;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

public class LimitRateOperator {
	
	private static final Logger log = LoggerFactory.getLogger(LimitRateOperator.class);
	
	public static void limitRateExample() {
		Flux.range(1, 10)
		.log()
		.limitRate(5)
		.subscribe();
	}
	
	public static void implementSubscriber() {
		Flux.range(1, 10)
		.log()
		.subscribe(new Subscriber<Integer>() {

			private Subscription s;
			
			private Integer limit = 5;
			private Integer count = 0;
			
			@Override
			public void onSubscribe(Subscription s) {
				this.s = s;
				s.request(limit);
			}

			@Override
			public void onNext(Integer t) {
				log.info(t.toString());
				count++;
				if (count == limit) {
					count = 0;
					s.request(limit);
					log.info("Has reached the limit");
				}
			}

			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
