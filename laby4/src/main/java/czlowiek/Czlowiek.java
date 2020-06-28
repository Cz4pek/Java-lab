package czlowiek;


public abstract class Czlowiek {
    private String imie;
    //private String zawod;
    private String nazwisko;
    private int wiek;
    private char plec;
    private boolean zatrudniony = false;

    public String jakSieNazywasz(){
        return imie +" "+ nazwisko;
    }

    public int getWiek(){
        return wiek;
    }

    public String jestesKobieta(){
        switch (plec) {
            case 'k':
                return "tak";
            case 'm':
                return "nie";
            case 'n':
                return "nie wiadomo";
            default:
                return "błędna wartość płci";
        }
    }

    protected void zatrudnij(){
        zatrudniony = true;
    }
    
    public abstract String skadDochod();

    public Czlowiek(String imie, String nazwisko, int wiek, char plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.plec = plec;
    }
      
    public Czlowiek() {        
        this("NN", "NN",0 , 'n');
    }

    public Czlowiek(String imie, String nazwisko) {
        this(imie, nazwisko,0 , 'n');
    }

    public boolean isZatrudniony() {
        return zatrudniony;
    }


}
