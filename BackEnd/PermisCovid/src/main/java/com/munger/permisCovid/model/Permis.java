package com.munger.permisCovid.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Permis {
    @Id
    @GeneratedValue
    private int idPermis;


    private LocalDate dateExpiration;
    private boolean isValide;
    private boolean isVaccinated;
    private boolean isTestedNegative;
    private int idCitoyen;


    public Permis() {

    }

    public Permis(int idCitoyen, boolean isVaccinated, boolean isTestedNegative) {
        this.idCitoyen = idCitoyen;
        this.isVaccinated = isVaccinated;
        this.isTestedNegative = isTestedNegative;
        isValide = true;
        if (isTestedNegative) {
            this.dateExpiration = LocalDate.now().plusDays(14);
        }

    }

    public void renouveler() {
        this.dateExpiration = LocalDate.now().plusDays(14);
    }


}

