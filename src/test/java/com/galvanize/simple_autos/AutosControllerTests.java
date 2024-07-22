package com.galvanize.simple_autos;


import org.junit.jupiter.api.Test;
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(AutosController.class)
public class AutosControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutosService autosService;//mocking the service
//-Get:/api/autos Searches for autos
// Get:/api/autos no autos in db returns 204 no content
// Get:/api/autos?color=RED Returns red cars
// Get:/api/autos?make=Ford Returns Ford cars
// Get:/api/autos?make=Ford&color=RED Returns red Ford cars
//-Get:/api/autos/{vin} Finds an automobile by its vin

    @Test
    void getAllAutosTest() throws Exception {
        List<Automobiles> automobiles= new ArrayList<>();
        for (int i=0; i<5; i++){
            automobiles.add(new Automobiles(1999+i, "Ford", "Bronco",  "ASDD"+i ));}
        when(autosService.getAutos()).thenReturn(new AutoList(automobiles));
        mockMvc.perform(get("/api/autos"))
                .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void getAutosTestNoAutosInDatabase() throws Exception {
        List<Automobiles> automobiles= new ArrayList<>();
        when(autosService.getAutos()).thenReturn(new AutoList(automobiles));
            mockMvc.perform(get("/api/autos"))
                    .andDo(print())
                    .andExpect(status().isNoContent());
        }

//    @Test
//    void getAutosColorTest() throws Exception {
//        List<Automobiles> automobiles= new ArrayList<>();
//        for (int i=0; i<5; i++){
//            automobiles.add(new Automobiles(1999+i, "Ford", "Bronco",  "ASDD"+i ));}
//when(autosService.getAutos(anyString(), isNull())).thenReturn(new AutoList(automobiles));
//        mockMvc.perform(get("/api/autos?color=RED"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.color", automobiles.contains("red"));
//        }

    @Test
    void getAutosColorAndMakeTest() throws Exception {
        List<Automobiles> automobiles= new ArrayList<>();
     for (int i=0; i<5; i++){
        automobiles.add(new Automobiles(1999+i, "Ford", "Bronco",  "ASDD"+i ));}
        when(autosService.getAutos(anyString(), anyString())).thenReturn(new AutoList(automobiles));
        mockMvc.perform(get("/api/autos?make=Ford&color=RED"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }







    }
//-Post:/api/autos Adds an automobile
// -Post:/api/autos returns error message due to bad request (400)

//-Patch:/api/autos/{vin}  Updates owner, or color of vehicle
// -Patch:/api/autos/{vin}  Returns NoContent auto not found
// -Patch:/api/autos/{vin}  Returns 400 bad request (no payload, no changes or already done)

//-Delete:/api/autos/{vin}   Deletes an automobile by its vin/returns 202
// -Delete:/api/autos/{vin}   Returns 204, vehicle not found



