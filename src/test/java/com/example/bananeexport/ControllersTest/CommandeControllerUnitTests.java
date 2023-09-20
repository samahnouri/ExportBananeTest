package com.example.bananeexport.ControllersTest;

import com.example.bananeexport.Controllers.CommandeController;
import com.example.bananeexport.Entities.Commande;
import com.example.bananeexport.Entities.Destinataire;
import com.example.bananeexport.Services.CommandeService;
import com.example.bananeexport.Services.DestinataireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CommandeControllerUnitTests {

    @InjectMocks
    private CommandeController commandeController;

    @Mock
    private CommandeService commandeService;
    @Mock
    private DestinataireService destinataireService;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetByDestinataire() {
        Commande commande = new Commande();
        Destinataire destinataire = new Destinataire();
        destinataire.setId(1L);
        commande.setDestinataire(destinataire);

        when(destinataireService.getById(1L)).thenReturn(destinataire); // Mock the retrieval of Destinataire

        List<Commande> commandes = Arrays.asList(commande);
        when(commandeService.listByDestinataire(any(Destinataire.class))).thenReturn(commandes);

        List<Commande> result = commandeController.getByDestinataire(1L).getBody();

        assertEquals(1, result.size());
        assertEquals(destinataire.getId(), result.get(0).getDestinataire().getId());
        verify(commandeService, times(1)).listByDestinataire(destinataire);
    }


    @Test
    public void testCreate() {
        Commande commande = new Commande();
        when(commandeService.saveOrUpdate(commande)).thenReturn(commande);

        Commande result = commandeController.create(commande).getBody();

        assertEquals(commande, result);
        verify(commandeService, times(1)).saveOrUpdate(commande);
    }

    @Test
    public void testUpdate() {
        Commande commande = new Commande();
        when(commandeService.getById(1L)).thenReturn(commande);

        Commande newCommande = new Commande();
        when(commandeService.saveOrUpdate(commande)).thenReturn(newCommande);

        Commande result = commandeController.update(1L, newCommande).getBody();

        assertEquals(newCommande, result);
        verify(commandeService, times(1)).getById(1L);
        verify(commandeService, times(1)).saveOrUpdate(commande);
    }

    @Test
    public void testDelete() {
        doNothing().when(commandeService).delete(1L);
        commandeController.delete(1L);
        verify(commandeService, times(1)).delete(1L);
    }
}
