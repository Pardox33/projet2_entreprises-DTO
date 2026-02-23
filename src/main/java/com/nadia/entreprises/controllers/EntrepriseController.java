package com.nadia.entreprises.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nadia.entreprises.entities.Entreprise;
import com.nadia.entreprises.service.EntrepriseService;

@Controller
public class EntrepriseController {

    @Autowired
    EntrepriseService entrepriseService;

    
    @RequestMapping("/listeEntreprises")
    public String listeEntreprises(ModelMap modelMap,
    		@RequestParam (name="page",defaultValue="0")int page,
    		@RequestParam (name="size",defaultValue="3") int size) {
    	Page<Entreprise> ents = entrepriseService.getAllEntreprisesParPage(page, size);

    	modelMap.addAttribute("entreprises", ents);
    	modelMap.addAttribute("pages", new int[ents.getTotalPages()]);
    	modelMap.addAttribute("currentPage", page);

    	return "listeEntreprises";
    }

    
    @RequestMapping("/showCreate")
    public String showCreate() {
        return "createEntreprise";
    }

    
    @RequestMapping("/saveEntreprise")
    public String saveEntreprise(@ModelAttribute("entreprise") Entreprise entreprise,
                                 @RequestParam("date") String date,
                                 ModelMap modelMap) throws ParseException {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(date);
        entreprise.setDateCre(dateCreation);

        Entreprise saved = entrepriseService.saveEntreprise(entreprise);

        String msg = "Entreprise enregistrée avec Id " + saved.getIdEnt();
        modelMap.addAttribute("msg", msg);

        return "createEntreprise";
    }

   
    @RequestMapping("/supprimerEntreprise")
    public String supprimerEntreprise(@RequestParam("id") Long id,
                                      ModelMap modelMap,
                                      @RequestParam (name="page",defaultValue = "0") int page, 
                                      @RequestParam (name="size", defaultValue = "2") int size)  {

        entrepriseService.deleteEntrepriseById(id);
        Page<Entreprise> ents = entrepriseService.getAllEntreprisesParPage(page, size);

        modelMap.addAttribute("entreprises", ents);           
        modelMap.addAttribute("pages", new int[ents.getTotalPages()]);     
        modelMap.addAttribute("currentPage", page);                        
        modelMap.addAttribute("size", size);                               

        return "listeEntreprises";
    }

   
    @RequestMapping("/modifierEntreprise")
    public String editerEntreprise(@RequestParam("id") Long id,
                                   ModelMap modelMap) {

        Entreprise e = entrepriseService.getEntreprise(id);
        modelMap.addAttribute("entreprise", e);

        return "editerEntreprise";
    }

    
    @RequestMapping("/updateEntreprise")
    public String updateEntreprise(@ModelAttribute("entreprise") Entreprise entreprise,
                                   @RequestParam("date") String date,
                                   ModelMap modelMap) throws ParseException {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateCreation = dateformat.parse(date);
        entreprise.setDateCre(dateCreation);

        entrepriseService.updateEntreprise(entreprise);

        List<Entreprise> ents = entrepriseService.getAllEntreprises();
        modelMap.addAttribute("entreprises", ents);

        return "listeEntreprises";
    }
}