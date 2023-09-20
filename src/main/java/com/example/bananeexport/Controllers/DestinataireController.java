package com.example.bananeexport.Controllers;

import com.example.bananeexport.Entities.Destinataire;
import com.example.bananeexport.Services.DestinataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/destinataires")
public class DestinataireController {

    @Autowired
    private DestinataireService destinataireService;

    @GetMapping
    public ResponseEntity<List<Destinataire>> getAll() {
        return ResponseEntity.ok(destinataireService.listAll());
    }

    @PostMapping
    public ResponseEntity<Destinataire> create(@Valid @RequestBody Destinataire destinataire) {
        return ResponseEntity.ok(destinataireService.saveOrUpdate(destinataire));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destinataire> update(@PathVariable Long id, @RequestBody Destinataire destinataireDetails) {
        Destinataire currentDestinataire = destinataireService.getById(id);

        if (currentDestinataire == null) {
            return ResponseEntity.notFound().build();
        }

        currentDestinataire.setNom(destinataireDetails.getNom());
        currentDestinataire.setAdresse(destinataireDetails.getAdresse());
        currentDestinataire.setCodePostal(destinataireDetails.getCodePostal());
        currentDestinataire.setVille(destinataireDetails.getVille());
        currentDestinataire.setPays(destinataireDetails.getPays());

        Destinataire updatedDestinataire = destinataireService.saveOrUpdate(currentDestinataire);

        return ResponseEntity.ok(updatedDestinataire);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        destinataireService.delete(id);
        return ResponseEntity.noContent().build();
    }
}