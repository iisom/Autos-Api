package com.galvanize.simple_autos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
public class AutosController {

    private List<AutoList> autoList;

    AutosService autosService;

    public AutosController(AutosService autosService) {
        this.autosService = autosService;
    }


    @GetMapping("/api/autos")
    public ResponseEntity<AutoList> getAutos(@RequestParam(required = false) String color, @RequestParam(required = false) String make) {
        AutoList autoList;
        if (color == null && make == null) {
            autoList = autosService.getAutos();
        } else {
            autoList = autosService.getAutos(color, make);
        }
        return autoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(autoList);
    }

    @GetMapping("/api/autos/{vin}")
    public ResponseEntity<Automobiles> getAutosByVin(@PathVariable String vin) {
        Automobiles auto = autosService.getAutosByVin(vin);
        return auto== null ? ResponseEntity.noContent().build() : ResponseEntity.ok(autosService.getAutosByVin(vin));
    }

    @PostMapping("/api/autos")
    public ResponseEntity<Automobiles> addAuto(@RequestBody Automobiles auto) {
        if (auto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(autosService.addAuto(auto));
    }

    @PatchMapping("/api/autos/{vin}")
         public ResponseEntity<Automobiles>  updateAuto(@PathVariable String vin,
                @RequestBody UpdateOwnerRequest update) {
        try {
            autosService.updateAuto(vin, update);
            Automobiles updatedAuto = autosService.updateAuto(vin, update);
            if (updatedAuto == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(updatedAuto); // Return 200 OK with updated automobile object
        } catch (AutoNotFoundException e) {
            return ResponseEntity.noContent().build();

        }}


        @DeleteMapping("/api/autos/{vin}")
        public ResponseEntity deleteAuto (@PathVariable String vin){
            try {
                autosService.deleteAuto(vin);
            } catch (Exception e) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.accepted().build();
        }


    }

