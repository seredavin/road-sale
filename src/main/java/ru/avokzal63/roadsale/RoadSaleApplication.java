package ru.avokzal63.roadsale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RoadSaleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoadSaleApplication.class, args);
	}

}
