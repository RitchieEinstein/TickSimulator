package com.ritchieeinstein.stockproject.ticksimulator;

import com.ritchieeinstein.stockproject.ticksimulator.service.TickGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class TicksimulatorApplication {

	public static void main(String[] args) throws IOException, InterruptedException {

		ApplicationContext context = SpringApplication.run(TicksimulatorApplication.class, args);
		TickGenerator generator = (TickGenerator)context.getBean(TickGenerator.class);
		generator.run();
	}

}
