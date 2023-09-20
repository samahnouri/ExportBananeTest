package com.example.bananeexport.Services;


import com.example.bananeexport.Entities.Commande;
import com.example.bananeexport.Entities.Destinataire;
import com.example.bananeexport.Exceptions.CommandeException;
import com.example.bananeexport.Repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommandeService {

    private static final double PRIX_PAR_KILO = 2.50;

    @Autowired
    private CommandeRepository commandeRepository;

    public List<Commande> listByDestinataire(Destinataire destinataire) {
        return commandeRepository.findByDestinataire(destinataire);
    }

    public Commande saveOrUpdate(Commande commande) {
        validateCommande(commande);
        commande.setPrix(commande.getQuantiteBananes() * PRIX_PAR_KILO);
        return commandeRepository.save(commande);
    }

    private void validateCommande(Commande commande) {
        Date oneWeekFromNow = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L);
        if (commande.getDateLivraison().before(oneWeekFromNow)) {
            throw new CommandeException("Date de livraison must be at least one week in the future!");
        }

        if (commande.getQuantiteBananes() <= 0 || commande.getQuantiteBananes() > 10000 || commande.getQuantiteBananes() % 25 != 0) {
            throw new CommandeException("Invalid quantité de bananes!");
        }
    }

    public void delete(Long id) {
        commandeRepository.deleteById(id);
    }

    public Commande getById(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }
}
