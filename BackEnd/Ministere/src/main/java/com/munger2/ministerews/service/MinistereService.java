package com.munger2.ministerews.service;

import com.munger2.ministerews.repository.MinistereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinistereService {

    @Autowired
    MinistereRepository ministereRepository;

    public boolean validerInfoCitoyen(String nassm, String prenom, String nom, int age, String ville) {
        return ministereRepository.findByNumAssuranceSocialAndPrenomAndNomAndAgeAndVille(nassm, prenom, nom, age, ville) != null;
    }

    public boolean validerVaccinationCitoyen(String nassm) {
        return ministereRepository.findByNumAssuranceSocialAndIsVaccinatedTrue(nassm) != null;
    }

    public boolean validerTestCitoyen(String nassm) {
        return ministereRepository.findByNumAssuranceSocialAndIsTestedTrue(nassm) != null;
    }

    public String validerPermisCitoyen(String nassm) {
      //  System.out.println("nassm= " + nassm);
     //   System.out.println("Test= " + validerNassmCitoyen(nassm));
        if (validerVaccinationCitoyen(nassm)) {
        System.out.println("Vaccinated= " + validerVaccinationCitoyen(nassm));
            return "Vaccinated";
        } else if (validerTestCitoyen(nassm)) {
        System.out.println("Tested= " + validerTestCitoyen(nassm));
            return "Tested";
        } else {
            System.out.println("null");
            return "None";
        }
    }


}
