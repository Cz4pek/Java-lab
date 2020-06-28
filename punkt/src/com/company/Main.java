package com.company;

public class Main {

    public static void main(String[] args) {
        Punkt p = new Punkt(2);
        FunkcjaKwad f = new FunkcjaKwad(1,2,3);
        System.out.println(f.odblicz(p));
        System.out.println(f.pierwiastki(p).getValue());
        System.out.println(f.pierwiastki(p).getKey());

    }
}
