package com.literalura.catalogo;

import com.literalura.catalogo.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);
	}
	private Principal principal;

	public CatalogoApplication(Principal principal) {
		this.principal = principal;
	}

	@Override
	public void run(String... args) throws Exception {
		principal.menuPrincipal();
	}
}
