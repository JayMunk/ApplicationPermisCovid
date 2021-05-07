package com.munger2.ministerews.controller;

import com.munger2.ministerews.service.MinistereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MinistereController {

    @Autowired
    MinistereService service;

    @GetMapping("/ministere/{nassm}/{prenom}/{nom}/{age}/{ville}")
    public boolean checkCitoyenValidity(@PathVariable("nassm") String nassm, @PathVariable("prenom") String prenom, @PathVariable("nom") String nom, @PathVariable("age") int age, @PathVariable("ville") String ville) {
        return service.validerInfoCitoyen(nassm, prenom, nom, age, ville);
    }

    @GetMapping("/ministere/{nassm}")
    public String checkCitizenPermisValidity(@PathVariable("nassm") String nassm) {
        return service.validerPermisCitoyen(nassm);
    }
}