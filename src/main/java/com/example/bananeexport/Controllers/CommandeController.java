package com.example.bananeexport.Controllers;

import com.example.bananeexport.Entities.Commande;
import com.example.bananeexport.Entities.Destinataire;
import com.example.bananeexport.Services.CommandeService;
import com.example.bananeexport.Services.DestinataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private DestinataireService destinataireService;

    @GetMapping("/byDestinataire/{destinataireId}")
    public ResponseEntity<List<Commande>> getByDestinataire(@PathVariable Long destinataireId) {
        Destinataire destinataire = destinataireService.getById(destinataireId);
        if (destinataire == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commandeService.listByDestinataire(destinataire));
    }

    @PostMapping
    public ResponseEntity<Commande> create(@Valid @RequestBody Commande commande) {
        return ResponseEntity.ok(commandeService.saveOrUpdate(commande));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> update(@PathVariable Long id, @RequestBody Commande commandeDetails) {
        Commande currentCommande = commandeService.getById(id);

        if (currentCommande == null) {
            return ResponseEntity.notFound().build();
        }

        currentCommande.setDestinataire(commandeDetails.getDestinataire());
        currentCommande.setDateLivraison(commandeDetails.getDateLivraison());
        currentCommande.setQuantiteBananes(commandeDetails.getQuantiteBananes());

        Commande updatedCommande = commandeService.saveOrUpdate(currentCommande);

        return ResponseEntity.ok(updatedCommande);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commandeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

