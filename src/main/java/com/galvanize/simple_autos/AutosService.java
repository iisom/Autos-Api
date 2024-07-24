package com.galvanize.simple_autos;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Automobiles getAutosByVin(String vin){

        Optional<Automobiles> automobiles= autoRepository.findByVinContains(vin);
        if(!automobiles.isEmpty()){
//            return automobiles;
        }
        return null;
    }

    public Automobiles addAuto(Automobiles auto) {
    return autoRepository.save(auto);
     }

    public Automobiles updateAuto(String vin, UpdateOwnerRequest update) {
        return null;
    }

    public Automobiles deleteAuto(String vin) {
        return null;
    }
}
