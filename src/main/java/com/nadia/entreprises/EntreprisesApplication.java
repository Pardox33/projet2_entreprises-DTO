package com.nadia.entreprises;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nadia.entreprises.entities.Entreprise;
import com.nadia.entreprises.service.EntrepriseService;

@SpringBootApplication
public class EntreprisesApplication implements CommandLineRunner{
	
	@Autowired
	EntrepriseService entrepriseService;
	
	public static void main(String[] args) {
		SpringApplication.run(EntreprisesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		entrepriseService.saveEntreprise(new Entreprise("aaa",566.4,new Date(),"aaa.@gmail.com"));
		entrepriseService.saveEntreprise(new Entreprise("bbb",751.4,new Date(),"bbb.@gmail.com"));
		entrepriseService.saveEntreprise(new Entreprise("ccc",940.4,new Date(),"ccc.@gmail.com"));
		
	}

}
