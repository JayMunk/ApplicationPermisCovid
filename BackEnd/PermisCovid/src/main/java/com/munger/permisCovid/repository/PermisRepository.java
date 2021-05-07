package com.munger.permisCovid.repository;

import com.munger.permisCovid.model.Permis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface PermisRepository extends JpaRepository<Permis, Integer> {
    public Permis findPermisByIdCitoyen(int id);
    public Permis findPermisByIdCitoyenAndAndIsVaccinatedIsFalse(int id);

    public Permis findPermisByIdCitoyenAndIsValideTrue(int id);
}
