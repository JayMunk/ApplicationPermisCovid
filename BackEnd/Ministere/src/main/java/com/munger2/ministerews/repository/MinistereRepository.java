package com.munger2.ministerews.repository;

import com.munger2.ministerews.model.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinistereRepository extends JpaRepository<Citoyen,Integer> {
    public Citoyen findByNumAssuranceSocial(String nassm);
    public Citoyen findByNumAssuranceSocialAndPrenomAndNomAndAgeAndVille(String nassm, String prenom,String nom, int age,String ville);
    public Citoyen findByNumAssuranceSocialAndIsVaccinatedTrue(String nassm);
    public Citoyen findByNumAssuranceSocialAndIsTestedTrue(String nassm);
}
