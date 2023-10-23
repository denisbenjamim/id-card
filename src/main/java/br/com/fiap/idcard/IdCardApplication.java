package br.com.fiap.idcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IdCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(IdCardApplication.class, args);
	}

}
