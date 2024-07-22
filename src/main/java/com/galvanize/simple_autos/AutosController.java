package com.galvanize.simple_autos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AutosController {

    private List<AutoList> autoList;

AutosService autosService;

public AutosController(AutosService autosService) {
    this.autosService = autosService;
}

    @GetMapping("/api/autos")
    public AutoList getAutos(){
        return autosService.getAutos();
    }

}
