package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

public class PrikazInventar implements IPrikaz {

    private static final String NAZEV = "inventar";
    private Inventar inventar;

    public PrikazInventar(Inventar inventar) {
        this.inventar = inventar;
    }


    @Override
    public String provedPrikaz(String... parametry) {
        return inventar.nazvyVeci();
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}
