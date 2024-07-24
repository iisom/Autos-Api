package com.galvanize.simple_autos;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

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
    }

    @Test
    void addAutoValidReturnsAuto() {
        Automobiles automobiles = new Automobiles(1999, "Ford", "Bronco", "ASDD");
        automobiles.setColor("RED");
        when(autoRepository.save(any(Automobiles.class))).thenReturn(automobiles);
        Automobiles auto = autosService.addAuto(automobiles);
        assertNotNull(auto);
        assertThat(auto.getMake()).isEqualTo("Ford");
    }

    @Test
    void updateAuto() {
    }

    @Test
    void deleteAuto() {
    }
}