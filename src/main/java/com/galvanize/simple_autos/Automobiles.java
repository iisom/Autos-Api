package com.galvanize.simple_autos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "automobiles")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Automobiles {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model_year")
    private int year;
    private String make;
    private String model;
    private String color;
    @Column(name = "owner_name")
    private String owner;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date purchaseDate;
    private String vin;


    public Automobiles(){}//no arug constructor

    public Automobiles(int year, String make, String model, String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getVin() {
        return vin;
    }
    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public String toString() {
        return "automobiles{"+
            "year=" + year +
                "make=" + make + '\'' +
                ", model=" + model + '\'' +
                ", color="+ color + '\'' +
                ", owner="+ owner + '\'' +
                "vin="+ vin +'\'' + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Automobiles that = (Automobiles) o;
        if (year != that.year) return false;
        if (!Objects.equals(make, that.make)) return false;
        if (!Objects.equals(model, that.model)) return false;
        if (!Objects.equals(color, that.color)) return false;
        if (!Objects.equals(owner, that.owner)) return false;
        if (!Objects.equals(vin, that.vin)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year,make,model,color,owner,vin);
    }

}
