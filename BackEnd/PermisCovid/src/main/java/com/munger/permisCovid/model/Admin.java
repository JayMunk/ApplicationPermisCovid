package com.munger.permisCovid.model;

import lombok.Data;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Data
public class Admin extends User implements Serializable {

    public Admin() {
    }

    public Admin(String nom, String prenom, String email, String password) {
        super(nom, prenom, email, password);
    }

}
