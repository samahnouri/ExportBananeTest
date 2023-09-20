package com.example.bananeexport.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"nom", "adresse", "codePostal", "ville", "pays"}))
public class Destinataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    @NotNull(message = "Nom is mandatory")
    private String nom;
    @Getter
    @Setter
    @NotNull(message = "Adresse is mandatory")
    private String adresse;
    @Getter
    @Setter
    @NotNull(message = "codePostal is mandatory")
    private String codePostal;
    @Getter
    @Setter
    @NotNull(message = "Ville is mandatory")
    private String ville;
    @Getter
    @Setter
    @NotNull(message = "Pays is mandatory")
    private String pays;

}

