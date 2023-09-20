package com.example.bananeexport.Repositories;

import com.example.bananeexport.Entities.Destinataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinataireRepository extends JpaRepository<Destinataire, Long> {
    boolean existsByNomAndAdresseAndCodePostalAndVilleAndPays(String nom, String adresse, String codePostal, String ville, String pays);
}
