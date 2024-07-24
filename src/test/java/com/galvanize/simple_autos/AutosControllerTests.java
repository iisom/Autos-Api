package com.galvanize.simple_autos;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(AutosController.class)
public class AutosControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AutosService autosService;//mocking the service
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String convertObjectToJson(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
//-Get:/api/autos Searches for autos
// Get:/api/autos no autos in db returns 204 no content
// Get:/api/autos?color=RED Returns red cars
// Get:/api/autos?make=Ford Returns Ford cars
// Get:/api/autos?make=Ford&color=RED Returns red Ford cars
//-Get:/api/autos/{vin} Finds an automobile by its vin

    @Test
    void getAllAutosTest() throws Exception {
        List<Automobiles> automobiles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            automobiles.add(new Automobiles(1999 + i, "Ford", "Bronco", "ASDD" + i));
        }
        when(autosService.getAutos()).thenReturn(new AutoList(automobiles));
        mockMvc.perform(get("/api/autos"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void getAutosTestNoAutosInDatabase() throws Exception {
        List<Automobiles> automobiles = new ArrayList<>();
        when(autosService.getAutos()).thenReturn(new AutoList(automobiles));
        mockMvc.perform(get("/api/autos"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void getAutosColorTest() throws Exception {
        List<Automobiles> automobiles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            automobiles.add(new Automobiles(1999 + i, "Ford", "Bronco", "ASDD" + i));
        }
        when(autosService.getAutos(anyString(), isNull())).thenReturn(new AutoList(automobiles));
        mockMvc.perform(get("/api/autos?color=RED"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void getAutosColorAndMakeTest() throws Exception {
        List<Automobiles> automobiles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            automobiles.add(new Automobiles(1999 + i, "Ford", "Bronco", "ASDD" + i));
        }
        when(autosService.getAutos(anyString(), anyString())).thenReturn(new AutoList(automobiles));
        mockMvc.perform(get("/api/autos?make=Ford&color=RED"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void getAutosMakeTest() throws Exception {
        List<Automobiles> automobiles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            automobiles.add(new Automobiles(1999 + i, "Ford", "Bronco", "ASDD" + i));
        }
        when(autosService.getAutos(isNull(), anyString())).thenReturn(new AutoList(automobiles));
        mockMvc.perform(get("/api/autos?make=Ford"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.automobiles", hasSize(5)));
    }

    @Test
    void getAutosByVinTest() throws Exception {
        Automobiles automobiles = new  Automobiles(1999, "Ford", "Bronco", "ASDD");
              String vin = "ASDD";
        when(autosService.getAutosByVin(anyString())).thenReturn(automobiles);
        mockMvc.perform(get("/api/autos/{vin}",vin))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("vin").value(vin));
    }

    @Test
    void addsAutosTest() throws Exception {
        Automobiles automobile = new Automobiles(2025, "Ford", "Bronco", "ASDD");
        String json= convertObjectToJson(automobile);
        when(autosService.addAuto(any(Automobiles.class))).thenReturn(automobile);
        mockMvc.perform(post("/api/autos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("make").value("Ford"));
    }

    @Test
    void returnsErrorBadRequestTest() throws Exception {
        when(autosService.addAuto(null)).thenReturn(null);
        mockMvc.perform(post("/api/autos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJson(null)))
                .andExpect(status().isBadRequest());
    }
//-Post:/api/autos Adds an automobile
// -Post:/api/autos returns error message due to bad request (400)

    @Test
    void updatesOwnerAndColorTest() throws Exception {
        Automobiles updatedAuto  = new Automobiles(2025, "Ford", "Bronco", "ASDD");
        when(autosService.updateAuto(anyString(), any(UpdateOwnerRequest.class))).thenReturn(updatedAuto );
        updatedAuto .setColor("BLACK");
        updatedAuto .setOwner("Max");
        mockMvc.perform(patch("/api/autos/ASDD" )
                .contentType(MediaType.APPLICATION_JSON)
                .content( "{\"color\": \"BLACK\",\"owner\":\"Max\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("color").value("BLACK"))
                .andExpect(jsonPath("owner").value("Max"));
    }
//-Patch:/api/autos/{vin}  Updates owner, or color of vehicle
// -Patch:/api/autos/{vin}  Returns NoContent auto not found
// -Patch:/api/autos/{vin}  Returns 400 bad request (no payload, no changes or already done)
    @Test
    void updateAuto_notFound_returnsNotFound() throws Exception {
            doThrow(new AutoNotFoundException()).when(autosService).updateAuto(anyString(), any(UpdateOwnerRequest.class));

            mockMvc.perform(patch("/api/autos/AABBCC")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content("{\"color\":\"RED\", \"owner\":\"Max\"}"))
                    .andDo(print())
                    .andExpect(status().isNotFound());
        }

    @Test
    void updateAuto_notFound_returnsNoContent() throws Exception {
        doThrow(new AutoNotFoundException()).when(autosService).updateAuto(isNull(), any(UpdateOwnerRequest.class));

        mockMvc.perform(patch("/api/autos/AABBCC")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"color\":\"RED\", \"owner\":\"Max\"}"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
//-Delete:/api/autos/{vin}   Deletes an automobile by its vin/returns 202
// -Delete:/api/autos/{vin}   Returns 204, vehicle not found

    @Test
    void deleteAuto_withVin_exists_Returns202() throws Exception {
        mockMvc.perform(delete("/api/autos/AABBCC"))
                .andDo(print())
                .andExpect(status().isAccepted());
        verify(autosService).deleteAuto(anyString());
    }

    @Test
    void deleteAuto_withVin_notexists_returnsNoContent() throws Exception {
        doThrow(new AutoNotFoundException()).when(autosService).deleteAuto(anyString());
        mockMvc.perform(delete("/api/autos/AABBCC"))
            .andDo(print())
            .andExpect(status().isNoContent());
        verify(autosService).deleteAuto(anyString());
}
}