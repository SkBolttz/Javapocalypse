package com.br.consultarFipe.ConsultarFipe;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.consultarFipe.Principal.ConsultarFipe;

@SpringBootApplication
public class ConsultarFipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultarFipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		ConsultarFipe consultarFipe = new ConsultarFipe();
		consultarFipe.verificarFipe();
	}

}
