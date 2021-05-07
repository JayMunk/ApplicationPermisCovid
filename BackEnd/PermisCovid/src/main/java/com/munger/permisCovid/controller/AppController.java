package com.munger.permisCovid.controller;

import com.munger.permisCovid.model.Citoyen;
import com.munger.permisCovid.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AppController {

    @Autowired
    SystemService service;

    //login, createUser
    @GetMapping("/citoyens/{email}/{password}")
    public Citoyen login(@PathVariable("email") String email, @PathVariable("password") String password) {
        return  service.login(email, password);
    }

    //createChild, createUser
    // @GetMapping("/citoyens/{citoyen}")
    // public Citoyen save(@PathVariable("citoyen") Citoyen T) {
    @RequestMapping(method = RequestMethod.POST)
    public Citoyen save(@RequestParam(value = "citoyen") Citoyen c) {
        System.out.println("allo");
        return service.createUser(c);
    }

    //dashboard
    @RequestMapping(method = RequestMethod.GET)
    //@GetMapping("/enfants/{email}")
    public List<Citoyen> findEnfantsByTuteurEmail(@RequestParam(value = "email") String email) {
        return service.findChild(email);
    }

    //requestPermis
    // @RequestMapping(method = RequestMethod.POST)
    @GetMapping("/citoyens/{id}&{typePermis}")
    // public Citoyen updatePermis(@RequestParam(value="id")String id, @RequestParam(value="typePermis")String typePermis){
    public Citoyen createPermis(@PathVariable("id") int id, @PathVariable("typePermis") String typePermis) {
        System.out.println("id= " + id + "// typePermis= " + typePermis);
        return service.requestPermis(id, typePermis);
    }

    //renewPermis
    @GetMapping("/citoyens/renew/{id}")
     public Citoyen updatePermis(@RequestParam(value="id")int id){
        return service.renewPermis(id);
    }

    //renewPermis
    @GetMapping("/citoyens/{email}&{numTelephone}&{ville}")
    public boolean checkCitoyenValidityToRenewPermis(@PathVariable("email") String email, @PathVariable("numTelephone") String numTelephone, @PathVariable("ville") String ville) {
        return service.checkCitoyenValidityToRenewPermis(email, numTelephone, ville);
    }
}
