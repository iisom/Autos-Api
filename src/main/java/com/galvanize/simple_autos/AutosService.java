package com.galvanize.simple_autos;


import org.springframework.stereotype.Service;

@Service
public class AutosService {



    public AutoList getAutos(){
        return null;
    }

    public AutoList getAutos(String color, String make){
        return null;
    }

    public AutoList getAutosByVin(){
        return null;
    }
    public AutoList getAutosByVin(String vin){
        return null;
    }

    public Automobiles addAuto() {
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
