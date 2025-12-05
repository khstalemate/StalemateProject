package com.stale.mate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class StalemateProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StalemateProjectApplication.class, args);
	}

}
