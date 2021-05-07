package com.munger.permisCovid.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Data
public class User {
    @Id
    @GeneratedValue
    private int idUser;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    private String email;
    @NotNull
    private String password;


    public User(String nom, String prenom, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

}