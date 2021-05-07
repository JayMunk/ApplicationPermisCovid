package com.munger2.ministerews.model;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Citoyen {
    @Id
    @GeneratedValue
    private int idCitoyen;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    private String numAssuranceSocial;

    @NotNull
    private char sexe;

    @NotNull
    private int age;

    @NotNull
    private String ville;

    private Boolean isTested;

    private Boolean isVaccinated;
}
