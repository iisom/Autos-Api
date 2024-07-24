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

          return autoRepository.findByVinContains(vin);
    }

    public Automobiles addAuto(Automobiles auto) {
    return autoRepository.save(auto);
     }

    public Automobiles updateAuto(String vin, UpdateOwnerRequest update) {
        Automobiles auto = autoRepository.findByVinContains(vin);
        auto.setColor(update.getColor());
        auto.setMake(update.getOwner());
        return autoRepository.save(auto);
    }


    public Automobiles deleteAuto(String vin) {
        return null;
    }
}
