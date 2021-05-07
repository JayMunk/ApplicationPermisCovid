package com.munger.permisCovid.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
public class Citoyen extends User implements Serializable {
    @NotNull
    private String numAssuranceSocial;

    @NotNull
    private String sexe;

    @NotNull
    private int age;

    @NotNull
    private String numTelephone;

    @NotNull
    private String ville;

    @OneToOne
    private Permis permis;

   @Column(nullable = true)
    private Integer idTuteur;


    public Citoyen() {
        super();
    }

    public Citoyen(String nom, String prenom, String email, String password, String numAssuranceSocial, String sexe, int age, String numTelephone, String ville) {
        super(nom, prenom, email, password);
        this.numAssuranceSocial = numAssuranceSocial;
        this.sexe = sexe;
        this.age = age;
        this.numTelephone = numTelephone;
        this.ville = ville;
    }
}