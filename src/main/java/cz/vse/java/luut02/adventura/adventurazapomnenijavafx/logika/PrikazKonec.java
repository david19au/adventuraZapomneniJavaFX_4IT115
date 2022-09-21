package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Třída PrikazKonec implementuje pro hru příkaz konec.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author Jarmila Pavlickova, Trong Dat Luu
 * @version LS 2021/22
 */

class PrikazKonec implements IPrikaz {

    private static final String NAZEV = "konec";

    private Hra hra;

    /**
     * Konstruktor třídy
     *
     * @param hra odkaz na hru, která má být příkazem konec ukončena
     */
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     *
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            return "Co se má ukončit? Nechápu, proč jste zadal druhé slovo.";
        } else {
            hra.setKonecHry(true);
            return "Hra byla ukončena příkazem konec.";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return název příkazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
