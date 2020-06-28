package com.company;

public class Punkt {
    private double  []tab = new double[2];

    public Punkt(double x) {
        tab[0] = x;
    }

    public double getx() {
        return tab[0];
    }

    public double gety() {
        return tab[1];
    }

    public void sety(double y) {
        tab[1] = y;
    }
}
