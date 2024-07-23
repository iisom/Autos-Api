package com.galvanize.simple_autos;

import java.util.List;
import java.util.Objects;

public class AutoList {

    private List<Automobiles> automobiles;

    public AutoList() {}

    public AutoList(List<Automobiles> automobiles) {
        this.automobiles = automobiles;
    }
    public List<Automobiles> getAutomobiles() {
        return automobiles;
    }
    public void setAutomobiles(List<Automobiles> automobiles) {
        this.automobiles = automobiles;
    }

    public boolean isEmpty(){
        return automobiles.isEmpty();
    }

    @Override
    public String toString() {
        return "AutoList [automobiles=" + automobiles + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoList autoList = (AutoList) o;
        return Objects.equals(automobiles, autoList.automobiles);
    }

    @Override
    public int hashCode() {
       return Objects.hash(automobiles);
    }











}
