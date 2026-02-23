package com.nadia.entreprises.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadia.entreprises.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}
