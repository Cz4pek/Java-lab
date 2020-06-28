package czlowiek;

public class Pracodawca extends Czlowiek {

    private String nazwaFirmy;
    private String preferencje;
    private double pensjaPracownicza;

    public Pracodawca(String imie, String nazwisko, int wiek, char plec, String nazwaFirmy, String preferencje,
            double pensjaPracownicza) {
        super(imie, nazwisko, wiek, plec);
        this.nazwaFirmy = nazwaFirmy;
        this.preferencje = preferencje;
        this.pensjaPracownicza = pensjaPracownicza;
    }

    @Override
    public String skadDochod() {
       
        return null;
    }

    public void zatrudnij(Pracownik p) {
        if (p.getZawod().equals(this.preferencje) && p.getMiejscePracy().isBlank())
            p.zatrudnij(nazwaFirmy, pensjaPracownicza);
        else
            System.out.println("Pracownik już jest zatrudniony w innym miejscu, spróbuj go przekupić premią w postaci masła orzechowego");
    }
}