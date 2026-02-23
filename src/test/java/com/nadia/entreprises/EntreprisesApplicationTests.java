package com.nadia.entreprises;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.nadia.entreprises.entities.Entreprise;
import com.nadia.entreprises.repos.EntrepriseRepository;
import com.nadia.entreprises.service.EntrepriseService;

@SpringBootTest
class EntreprisesApplicationTests {
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private EntrepriseService entrepriseService;
	
	@Test
	public void testCreateEntreprise(){
	Entreprise ent=new Entreprise("Riot",850.0,new Date(),"riot@gmail.com");
	entrepriseRepository.save(ent);
	}
	
	@Test
	public void testFindEntreprise() {
		Entreprise e=entrepriseRepository.findById(1L).get();
		System.out.println(e);
	}
	
	@Test
	public void testUpdateEntreprise() {
		Entreprise e=entrepriseRepository.findById(1L).get();
		e.setChiffreAff(600.0);
		System.out.println("ID before save: " + e.getIdEnt());
		entrepriseRepository.save(e);
		System.out.println(e);
	}
	
	@Test
	public void testDeleteEntreprise() {
		entrepriseRepository.deleteById(5L);
	}
	
	@Test
	public void testFindAllEntreprise(){
		List<Entreprise> ents=entrepriseRepository.findAll();
		for(Entreprise e:ents) {
			System.out.println(e);
		}
;	}
	@Test
	public void testFindByNomEntContains() {

	    Page<Entreprise> ents = entrepriseService.getAllEntreprisesParPage(0, 2);

	    System.out.println("Size: " + ents.getSize());
	    System.out.println("Total Elements: " + ents.getTotalElements());
	    System.out.println("Total Pages: " + ents.getTotalPages());

	    ents.getContent().forEach(e -> {
	        System.out.println(e.toString());
	    });

	    /* OR

	    for (Entreprise e : ents) {
	        System.out.println(e);
	    }

	    */
	}

}
