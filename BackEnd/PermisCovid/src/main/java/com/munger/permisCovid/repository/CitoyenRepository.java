package com.munger.permisCovid.repository;

import com.munger.permisCovid.model.Citoyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitoyenRepository extends JpaRepository<Citoyen, Integer> {
    public Citoyen findCitoyenByEmailAndPassword(String email, String pwd);

    public Citoyen findCitoyenByIdUser(int id);

    public Citoyen findCitoyenByEmail(String email);

    public Citoyen findCitoyenByEmailAndNumTelephoneAndVille(String email, String numTelephone, String ville);

    public default Citoyen save2(Citoyen c) {
        return new Citoyen(c.getNom(), c.getPrenom(), c.getEmail(), c.getPassword(), c.getNumAssuranceSocial(), c.getSexe(), c.getAge(), c.getNumTelephone(), c.getVille());
    }

    List<Citoyen> findEnfantByIdTuteur(int id);
}
