package com.galvanize.simple_autos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutoRepository extends JpaRepository<Automobiles, Long> {

    List<Automobiles> findByColorContainsAndMakeContains(String color, String make);

    Automobiles findByVin(String vin);
}
