package com.nadia.entreprises.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nadia.entreprises.dto.EntreprisesDTO;
import com.nadia.entreprises.service.EntrepriseService;

import jakarta.validation.Valid;

@Controller
public class EntreprisesControllers {

	@Autowired
	private EntrepriseService entrepriseService;

	@GetMapping("/accessDenied")
	public String error() {
		return "accessDenied";
	}

	@RequestMapping("/listeEntreprises")
	public String listeEntreprises(ModelMap modelMap,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {

		Page<EntreprisesDTO> ents = entrepriseService.getAllEntreprisesParPage(page, size)
				.map(entrepriseService::convertEntityToDto);
		modelMap.addAttribute("entreprises", ents.getContent());
		modelMap.addAttribute("pages", new int[ents.getTotalPages()]);
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "listeEntreprises";
	}

	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap) {
		modelMap.addAttribute("entreprise", new EntreprisesDTO());
		modelMap.addAttribute("secteurs", entrepriseService.getAllSecteur());
		return "createEntreprises";
	}

	@RequestMapping("/saveEntreprise")
	public String saveEntreprise(@Valid @ModelAttribute("entreprise") EntreprisesDTO entrepriseDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "createEntreprises";
		}
		entrepriseService.saveEntreprise(entrepriseDto);
		return "redirect:/listeEntreprises";
	}

	@RequestMapping("/supprimerEntreprise")
	public String supprimerEntreprise(@RequestParam("id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size) {

		entrepriseService.deleteEntrepriseById(id);
		Page<EntreprisesDTO> ents = entrepriseService.getAllEntreprisesParPage(page, size)
				.map(entrepriseService::convertEntityToDto);
		if (page >= ents.getTotalPages() && page > 0) {
			page--;
		}
		return "redirect:/listeEntreprises?page=" + page + "&size=" + size;
	}

	@RequestMapping("/modifierEntreprise")
	public String editerEntreprise(@RequestParam("id") Long id,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			ModelMap modelMap) {

		modelMap.addAttribute("entreprise", entrepriseService.getEntreprise(id));
		modelMap.addAttribute("secteurs", entrepriseService.getAllSecteur());
		modelMap.addAttribute("currentPage", page);
		modelMap.addAttribute("size", size);
		return "createEntreprises";
	}

	@RequestMapping("/saveOrUpdateEntreprise")
	public String saveOrUpdateEntreprise(@Valid @ModelAttribute("entreprise") EntreprisesDTO entrepriseDto,
			BindingResult bindingResult,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "2") int size,
			ModelMap modelMap) {

		if (bindingResult.hasErrors()) {
			modelMap.addAttribute("secteurs", entrepriseService.getAllSecteur());
			return "createEntreprises";
		}

		if (entrepriseDto.getIdEnt() == null) {
			entrepriseService.saveEntreprise(entrepriseDto);
		} else {
			entrepriseService.updateEntreprise(entrepriseDto);
		}

		return "redirect:/listeEntreprises?page=" + page + "&size=" + size;
	}
}