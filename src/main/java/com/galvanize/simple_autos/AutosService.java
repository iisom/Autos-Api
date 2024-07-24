package com.galvanize.simple_autos;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutosService {


    AutoRepository autoRepository;

    public AutosService(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    public AutoList getAutos(){
        return new AutoList(autoRepository.findAll());
    }

    public AutoList getAutos(String color, String make){
        List<Automobiles> automobiles= autoRepository.findByColorContainsAndMakeContains(color,make);
        if(!automobiles.isEmpty()){
            return new AutoList(automobiles);
        }
        return null;
    }

    public AutoList getAutosByVin(String vin){
        return null;
    }

    public Automobiles addAuto(Automobiles auto) {
        return null;
    }

    public Automobiles updateAuto(String vin, UpdateOwnerRequest update) {
        return null;
    }

    public Automobiles deleteAuto(String vin) {
        return null;
    }
}
