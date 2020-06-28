package czlowiek;

import java.util.ArrayList;
import java.util.Collection;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
       Pracownik p = new Pracownik("Adam", "Mickiewicz", 30, 'm', "Wieszcz", "Gdzie się da", 100);
       Pracownik p2 = new Pracownik("Julisz", "Słowacki", 23, 'm', "Wieszcz ale ten gorszy", "Gdzie się da", 500);
       Pracownik p3 = new Pracownik("Marek", "Wojtczurowski", 300, 'm', "Informatyk", "", 3);
       Collection<Pracownik> pracownicy = new ArrayList<>();
       pracownicy.add(p);
       pracownicy.add(p2);
       pracownicy.add(p3);
       Pracodawca boss = new Pracodawca("ELiza", "Orzeszkowa", 150, 'k', "Praca u podstaw", "Informatyk", 2600);
       for (Pracownik pracownik : pracownicy) {
           Pracownik.dajRaport(pracownik);
           System.out.println();
           boss.zatrudnij(pracownik);
       }
       for (Pracownik pracownik : pracownicy) {
           if(pracownik.getMiejscePracy().equals("Praca u podstaw")) Pracownik.dajRaport(pracownik);
       }
       System.out.println();
       boss.zatrudnij(p);
    }
}
