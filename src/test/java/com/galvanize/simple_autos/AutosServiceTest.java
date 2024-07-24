package com.galvanize.simple_autos;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertEquals;

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
    void getAutosSearch() {

    }

    @Test
    void getAutosByVin() {
    }

    @Test
    void addAuto() {
    }

    @Test
    void updateAuto() {
    }

    @Test
    void deleteAuto() {
    }
}