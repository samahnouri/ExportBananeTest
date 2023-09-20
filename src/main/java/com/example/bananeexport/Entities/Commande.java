package com.example.bananeexport.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "destinataire_id")
    @Getter
    @Setter
    @NotNull(message = "Destinataire is mandatory")
    private Destinataire destinataire;
    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Date is mandatory")
    private Date dateLivraison;
    @Getter
    @Setter
    @NotNull(message = "QuantiteBanane is mandatory")
    private int quantiteBananes;  // In kilograms
    @Getter
    @Setter
    private double prix;

}

