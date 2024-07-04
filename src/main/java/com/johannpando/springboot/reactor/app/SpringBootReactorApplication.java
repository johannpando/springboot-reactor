package com.johannpando.springboot.reactor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.johannpando.springboot.reactor.app.base.inmutability.Inmutability;
import com.johannpando.springboot.reactor.app.base.operators.CreateOperator;
import com.johannpando.springboot.reactor.app.base.operators.DelayElementsOperator;
import com.johannpando.springboot.reactor.app.base.operators.FilterOperator;
import com.johannpando.springboot.reactor.app.base.operators.FlatMapOperator;
import com.johannpando.springboot.reactor.app.base.operators.IntervalOperator;
import com.johannpando.springboot.reactor.app.base.operators.LimitRateOperator;
import com.johannpando.springboot.reactor.app.base.operators.MapOperator;
import com.johannpando.springboot.reactor.app.base.operators.RangeOperator;
import com.johannpando.springboot.reactor.app.base.operators.ZipWithOperator;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(SpringBootApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// MapOperator.mapOperator();
		// FilterOperator.filterOperator();
		//Inmutability.inmutability();
		//FlatMapOperator.combineMonoWithCallable();
		//ZipWithOperator.operatorZipWith();
		//ZipWithOperator.operatorZipWith3FluxII();
		//ZipWithOperator.operatorZipWithObjects();
		//ZipWithOperator.operatorZipWithObjectsAndMapAndTuples();
		//RangeOperator.rangeOperatorExample();
		//DelayElementsOperator.delayElementsExample();
		//IntervalOperator.intervalExample();
		//CreateOperator.createOperatorExample();
		//LimitRateOperator.limitRateExample();
		LimitRateOperator.implementSubscriber();
	}

}
