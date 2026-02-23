package com.nadia.entreprises.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nadia.entreprises.entities.Entreprise;

public interface EntrepriseService {
	Entreprise saveEntreprise(Entreprise e);
	Entreprise updateEntreprise(Entreprise e);
	void deleteEntreprise(Entreprise e);
	void deleteEntrepriseById(Long id);
	Entreprise getEntreprise(Long id);
	List<Entreprise> getAllEntreprises();
	Page<Entreprise> getAllEntreprisesParPage(int page,int size);
}
