package com.example.bananeexport.ServicesTest;

import com.example.bananeexport.Entities.Destinataire;
import com.example.bananeexport.Exceptions.DestinataireException;
import com.example.bananeexport.Repositories.DestinataireRepository;
import com.example.bananeexport.Services.DestinataireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DestinataireServiceUnitTests {

    @InjectMocks
    private DestinataireService destinataireService;

    @Mock
    private DestinataireRepository destinataireRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testListAll() {
        Destinataire destinataire = new Destinataire();
        destinataire.setNom("John Doe");

        List<Destinataire> expectedList = Arrays.asList(destinataire);
        when(destinataireRepository.findAll()).thenReturn(expectedList);

        List<Destinataire> result = destinataireService.listAll();

        assertEquals(expectedList, result);
        verify(destinataireRepository, times(1)).findAll();
    }

    @Test
    public void testSaveOrUpdate_New() {
        Destinataire destinataire = new Destinataire();
        destinataire.setNom("John Doe");

        when(destinataireRepository.existsByNomAndAdresseAndCodePostalAndVilleAndPays(any(), any(), any(), any(), any())).thenReturn(false);
        when(destinataireRepository.save(destinataire)).thenReturn(destinataire);

        Destinataire result = destinataireService.saveOrUpdate(destinataire);

        assertEquals(destinataire, result);
        verify(destinataireRepository, times(1)).save(destinataire);
    }

    @Test
    public void testSaveOrUpdate_AlreadyExists() {
        Destinataire destinataire = new Destinataire();
        destinataire.setNom("John Doe");

        when(destinataireRepository.existsByNomAndAdresseAndCodePostalAndVilleAndPays(any(), any(), any(), any(), any())).thenReturn(true);

        assertThrows(DestinataireException.class, () -> destinataireService.saveOrUpdate(destinataire));
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doNothing().when(destinataireRepository).deleteById(id);

        destinataireService.delete(id);

        verify(destinataireRepository, times(1)).deleteById(id);
    }

    @Test
    public void testGetById() {
        Long id = 1L;
        Destinataire destinataire = new Destinataire();
        destinataire.setNom("John Doe");

        when(destinataireRepository.findById(id)).thenReturn(Optional.of(destinataire));

        Destinataire result = destinataireService.getById(id);

        assertEquals(destinataire, result);
    }

    @Test
    public void testGetById_NotFound() {
        Long id = 1L;
        when(destinataireRepository.findById(id)).thenReturn(Optional.empty());

        Destinataire result = destinataireService.getById(id);

        assertNull(result);
    }
}
