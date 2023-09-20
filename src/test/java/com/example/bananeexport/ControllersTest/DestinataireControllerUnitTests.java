package com.example.bananeexport.ControllersTest;


import com.example.bananeexport.Controllers.DestinataireController;
import com.example.bananeexport.Entities.Destinataire;
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

public class DestinataireControllerUnitTests {

    @InjectMocks
    private DestinataireController destinataireController;

    @Mock
    private DestinataireService destinataireService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        Destinataire destinataire = new Destinataire();
        destinataire.setNom("John Doe");

        List<Destinataire> destinataires = Arrays.asList(destinataire);
        when(destinataireService.listAll()).thenReturn(destinataires);

        List<Destinataire> result = destinataireController.getAll().getBody();

        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getNom());
        verify(destinataireService, times(1)).listAll();
    }

    @Test
    public void testCreate() {
        Destinataire destinataire = new Destinataire();
        destinataire.setNom("John Doe");
        when(destinataireService.saveOrUpdate(destinataire)).thenReturn(destinataire);

        Destinataire result = destinataireController.create(destinataire).getBody();

        assertEquals("John Doe", result.getNom());
        verify(destinataireService, times(1)).saveOrUpdate(destinataire);
    }

    @Test
    public void testUpdate() {
        Destinataire existingDestinataire = new Destinataire();
        existingDestinataire.setId(1L);
        when(destinataireService.getById(1L)).thenReturn(existingDestinataire);

        Destinataire newDetails = new Destinataire();
        newDetails.setNom("Jane Doe");
        when(destinataireService.saveOrUpdate(existingDestinataire)).thenReturn(newDetails);

        Destinataire result = destinataireController.update(1L, newDetails).getBody();

        assertEquals("Jane Doe", result.getNom());
        verify(destinataireService, times(1)).getById(1L);
        verify(destinataireService, times(1)).saveOrUpdate(existingDestinataire);
    }

    @Test
    public void testDelete() {
        doNothing().when(destinataireService).delete(1L);
        destinataireController.delete(1L);
        verify(destinataireService, times(1)).delete(1L);
    }
}
