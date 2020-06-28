package com.company;

import javafx.util.Pair;

public class FunkcjaKwad extends Funkcja {
    private double a,b,c;

    public FunkcjaKwad(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double odblicz(Punkt p) {
        double x = p.getx();
        double y = a*Math.pow(x,2) + b*x + c;
        p.sety(y);
        return y;
    }

    public Pair<Double, Double> pierwiastki( Punkt p){
        double c = this.c - p.gety();
        double delta = Math.pow(b,2) - 4*a*c;
        double x1= ((-b - Math.sqrt(delta)) / 2 * a);
        double x2= ((-b + Math.sqrt(delta)) / 2 * a);
        Pair<Double, Double> pair = new Pair<Double, Double>(x1, x2);
        return pair;
    }
}
