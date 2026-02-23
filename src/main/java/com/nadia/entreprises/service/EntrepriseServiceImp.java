package com.nadia.entreprises.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.nadia.entreprises.entities.Entreprise;
import com.nadia.entreprises.repos.EntrepriseRepository;

@Service
public class EntrepriseServiceImp implements EntrepriseService{
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	@Override
	public Entreprise saveEntreprise(Entreprise e) {
		return entrepriseRepository.save(e);
	}

	@Override
	public Entreprise updateEntreprise(Entreprise e) {
		return entrepriseRepository.save(e);
	}

	@Override
	public void deleteEntreprise(Entreprise e) {
		entrepriseRepository.delete(e);
	}

	@Override
	public void deleteEntrepriseById(Long id) {
		entrepriseRepository.deleteById(id);
	}

	@Override
	public Entreprise getEntreprise(Long id) {
		return entrepriseRepository.findById(id).get();
	}

	@Override
	public List<Entreprise> getAllEntreprises() {
		return entrepriseRepository.findAll();
	}

	@Override
	public Page<Entreprise> getAllEntreprisesParPage(int page, int size) {
		return entrepriseRepository.findAll(PageRequest.of(page, size));
	}

}
