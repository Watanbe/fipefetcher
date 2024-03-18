package br.com.eduardo.fipefetcher;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import principal.Principal;

@SpringBootApplication
public class FipefetcherApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipefetcherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.mainMenu();
	}
}
