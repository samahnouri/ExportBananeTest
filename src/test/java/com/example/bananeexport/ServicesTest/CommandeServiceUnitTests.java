package com.example.bananeexport.ServicesTest;


import com.example.bananeexport.Entities.Commande;
import com.example.bananeexport.Entities.Destinataire;
import com.example.bananeexport.Exceptions.CommandeException;
import com.example.bananeexport.Repositories.CommandeRepository;
import com.example.bananeexport.Services.CommandeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandeServiceUnitTests {

    @InjectMocks
    private CommandeService commandeService;

    @Mock
    private CommandeRepository commandeRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListByDestinataire() {
        Destinataire destinataire = new Destinataire();
        Commande commande = new Commande();
        commande.setDestinataire(destinataire);

        List<Commande> expectedList = Arrays.asList(commande);
        when(commandeRepository.findByDestinataire(destinataire)).thenReturn(expectedList);

        List<Commande> result = commandeService.listByDestinataire(destinataire);

        assertEquals(expectedList, result);
        verify(commandeRepository, times(1)).findByDestinataire(destinataire);
    }

    @Test
    public void testSaveOrUpdate_Valid() {
        Commande commande = new Commande();
        Date futureDate = new Date(System.currentTimeMillis() + 8 * 24 * 60 * 60 * 1000L); // More than one week from now
        commande.setDateLivraison(futureDate);
        commande.setQuantiteBananes(100);
        when(commandeRepository.save(commande)).thenReturn(commande);

        Commande result = commandeService.saveOrUpdate(commande);

        assertEquals(250.0, result.getPrix()); // 100 * 2.5
    }

    @Test
    public void testSaveOrUpdate_InvalidDate() {
        Commande commande = new Commande();
        Date nearFuture = new Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000L); // Less than one week from now
        commande.setDateLivraison(nearFuture);

        assertThrows(CommandeException.class, () -> commandeService.saveOrUpdate(commande));
    }

    @Test
    public void testSaveOrUpdate_InvalidBananes() {
        Commande commande = new Commande();
        Date futureDate = new Date(System.currentTimeMillis() + 8 * 24 * 60 * 60 * 1000L);
        commande.setDateLivraison(futureDate);
        commande.setQuantiteBananes(-5);

        assertThrows(CommandeException.class, () -> commandeService.saveOrUpdate(commande));
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(commandeRepository).deleteById(id);

        commandeService.delete(id);

        verify(commandeRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        Commande commande = new Commande();

        when(commandeRepository.findById(id)).thenReturn(Optional.of(commande));

        Commande result = commandeService.getById(id);

        assertEquals(commande, result);
    }

    @Test
    public void testGetById_NotFound() {
        Long id = 1L;
        when(commandeRepository.findById(id)).thenReturn(Optional.empty());

        Commande result = commandeService.getById(id);

        assertNull(result);
    }
}

