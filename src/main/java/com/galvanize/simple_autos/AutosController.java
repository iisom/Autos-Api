package com.galvanize.simple_autos;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;


@RestController
public class AutosController {

    private List<AutoList> autoList;

AutosService autosService;

public AutosController(AutosService autosService) {
    this.autosService = autosService;
}


    @GetMapping("/api/autos")
    public ResponseEntity <AutoList> getAutos(@RequestParam(required =false) String color, @RequestParam(required =false) String make) {
   AutoList autoList;
    if (color== null && make == null){
         autoList =autosService.getAutos();
    } else{
        autoList=autosService.getAutos(color, make);
    }
        return autoList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(autoList);
    }

}
