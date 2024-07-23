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
    public ResponseEntity<AutoList> getAutosByVin(@PathVariable(required = false) String vin) {
        AutoList autoList = autosService.getAutosByVin(vin);
        return autoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(autoList);
    }

    @PostMapping("/api/autos")
    public ResponseEntity<Automobiles> addAuto(@RequestBody Automobiles auto) {
        if (auto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(autosService.addAuto(auto));
    }

    @PatchMapping("/api/autos/{vin}")
    public ResponseEntity<Automobiles> updatesOwnerAndColor(@PathVariable String vin, @RequestBody UpdateOwnerRequest update) {
        if ((vin == null)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Automobiles updatedAuto = autosService.updateAuto(vin, update);

            if (updatedAuto == null) {
                return ResponseEntity.noContent().build(); // Return 404 Not Found if automobile with given VIN is not found
            }

            return ResponseEntity.ok(updatedAuto); // Return 200 OK with updated automobile object
        } catch (AutoNotFoundException e) {
            return ResponseEntity.notFound().build();
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

