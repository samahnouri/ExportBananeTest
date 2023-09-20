package com.example.bananeexport.Services;

import com.example.bananeexport.Entities.Destinataire;
import com.example.bananeexport.Exceptions.DestinataireException;
import com.example.bananeexport.Repositories.DestinataireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinataireService {

    @Autowired
    private DestinataireRepository destinataireRepository;

    public List<Destinataire> listAll() {
        return destinataireRepository.findAll();
    }

    public Destinataire saveOrUpdate(Destinataire destinataire) {
        if (destinataireRepository.existsByNomAndAdresseAndCodePostalAndVilleAndPays(destinataire.getNom(), destinataire.getAdresse(), destinataire.getCodePostal(), destinataire.getVille(), destinataire.getPays())) {
            throw new DestinataireException("Destinataire already exists!");
        }
        return destinataireRepository.save(destinataire);
    }

    public void delete(Long id) {
        destinataireRepository.deleteById(id);
    }

    public Destinataire getById(Long id) {
        return destinataireRepository.findById(id).orElse(null);
    }
}


