package czlowiek;

public class Pracownik extends Czlowiek {
    private String zawod;
    private String miejscePracy;
    private double dochod;

    public Pracownik(String imie, String nazwisko, int wiek, char plec, String zawod, String miejscePracy,
            double dochod) {
        super(imie, nazwisko, wiek, plec);
        if (!miejscePracy.isBlank())
            super.zatrudnij();
        this.zawod = zawod;
        this.miejscePracy = miejscePracy;
        this.dochod = dochod;
    }

    public void zatrudnij(String miejscePracy, double dochod) {
        super.zatrudnij();
        this.miejscePracy = miejscePracy;
        this.dochod = dochod;
    }

    @Override
    public String skadDochod() {
        if (super.isZatrudniony())
            return miejscePracy + ", " + dochod;
        else
            return "Brak dochodu";
    }

    public static void dajRaport(Pracownik p) {
        System.out.println("Imie i nazwisko: " + p.jakSieNazywasz() + "\nWiek: " + p.getWiek() + "\nPlec: "
                + p.jestesKobieta() + "\nZawód: " + p.zawod + "\nMiejsce pracy oraz dochód: " + p.skadDochod());
    }

    public String getZawod() {
        return zawod;
    }

    public String getMiejscePracy() {
        return miejscePracy;
    }

}