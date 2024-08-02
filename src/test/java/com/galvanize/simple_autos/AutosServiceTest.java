package com.galvanize.simple_autos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutosServiceTest {

    private AutosService autosService;

    @Mock
    private AutoRepository autoRepository;

    @BeforeEach
    void setUp() {
        autosService = new AutosService(autoRepository);
    }

    @Test
    void getAutosNoArgsReturnsList() {
        AutoList autoList = autosService.getAutos();
              assertNotNull(autoList);
    }

    @Test
    void getAutosSearchReturnsList() {
        Automobiles automobiles = new Automobiles(1999, "Ford", "Bronco", "ASDD");
        automobiles.setColor("RED");
    when(autoRepository.findByColorContainsAndMakeContains(anyString(), anyString())).thenReturn(Arrays.asList(automobiles));
    AutoList autoList = autosService.getAutos("RED", "Ford");
    assertNotNull(autoList);
    assertFalse(autoList.isEmpty());
    }

    @Test
    void getAutosByVin() {
        Automobiles automobiles = new Automobiles(1999, "Ford", "Bronco", "ASDD");
        automobiles.setVin("ASDA");
        when(autoRepository.findByVin(anyString())).thenReturn((automobiles));
        Automobiles auto = autosService.getAutosByVin(automobiles.getVin());
        assertNotNull(auto);
        assertThat(auto.getVin()).isEqualTo(automobiles.getVin());
    }

    @Test
    void addAutoValidReturnsAuto() {
        Automobiles automobiles = new Automobiles(1999, "Ford", "Bronco", "ASDD");
        when(autoRepository.save(any(Automobiles.class))).thenReturn(automobiles);
        Automobiles auto = autosService.addAuto(automobiles);
        assertThat(auto).isNotNull();
        assertThat(auto.getMake()).isEqualTo("Ford");
    }

    @Test
    void updateAuto() {
        Automobiles auto = new Automobiles(2021, "Toyota", "Camry", "1234567890");
        auto.setColor("red");
        auto.setOwner("John Doe");
        UpdateOwnerRequest update = new UpdateOwnerRequest("red", "John Doe");
        when(autoRepository.findByVin(any())).thenReturn((auto));
        when(autoRepository.save(any())).thenReturn(auto);
        Automobiles updatedAuto = autosService.updateAuto("1234567890", update);
        assertThat(updatedAuto).isNotNull();
        assertThat(updatedAuto.getColor()).isEqualTo("red");
        assertThat(updatedAuto.getOwner()).isEqualTo("John Doe");
    }


    @Test
    void deleteAuto() {
        when(autoRepository.findByVin(any())).thenReturn(null);
        assertThrows(AutoNotFoundException.class, () -> autosService.deleteAuto("1234567890"));
    }


}