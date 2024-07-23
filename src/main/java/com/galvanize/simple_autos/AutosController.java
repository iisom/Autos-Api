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
    public ResponseEntity <AutoList> getAutos(@RequestParam(required =false) String color, @RequestParam(required =false) String make){
   AutoList autoList;
    if (color== null && make == null){
         autoList =autosService.getAutos();
    } else{
        autoList=autosService.getAutos(color, make);
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

    }