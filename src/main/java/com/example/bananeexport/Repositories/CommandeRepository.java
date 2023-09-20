package com.example.bananeexport.Repositories;

import com.example.bananeexport.Entities.Commande;
import com.example.bananeexport.Entities.Destinataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByDestinataire(Destinataire destinataire);
}
