package com.galvanize.simple_autos;


import org.springframework.stereotype.Service;

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
