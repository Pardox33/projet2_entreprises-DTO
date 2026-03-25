package com.nadia.entreprises;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.nadia.entreprises.entities.Entreprise;

@SpringBootApplication
public class EntreprisesApplication implements CommandLineRunner {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	/*
	 * @Autowired
	 * EntrepriseService entrepriseService;
	 */

	public static void main(String[] args) {
		SpringApplication.run(EntreprisesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repositoryRestConfiguration.exposeIdsFor(Entreprise.class);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/*
	 * Override public void run(String... args) throws Exception {
	 * System.out.println("Password Encoded BCRYPT :******************** ");
	 * System.out.println(passwordEncoder.encode("123"));
	 * 
	 * produitService.saveProduit(new Produit("PC Dell", 2600.0, new Date()));
	 * produitService.saveProduit(new Produit("PC Asus", 2800.0, new Date()));
	 * produitService.saveProduit(new Produit("Imp Epson", 900.0, new Date()));
	 * 
	 * 
	 * }
	 */
}
