package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Třída PrikazInventar implementuje příkaz 'inventar'.
 * Tento příkaz vypíše uživateli co má momentálně v inventáři.
 *
 * @author Trong Dat Luu
 * @version ZS 2022/23
 */
public class PrikazInventar implements IPrikaz {

    private static final String NAZEV = "inventar";
    private Inventar inventar;

    /**
     * Konstruktor třídy
     *
     * @param inventar je hráčův inventář
     */
    public PrikazInventar(Inventar inventar) {
        this.inventar = inventar;
    }

    /**
     * Tato metoda provádí příkaz 'inventar'.
     * Vypíše co má hráč v inventáři za věci.
     *
     * @param parametry v tomhle případě nejsou třeba, takže se kontroluje jenom, zda hráč jich nenapsal víc.
     * @return vrací buď text s vypsaným inventářem anebo zprávu o parametrech.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return inventar.nazvyVeci();
        } else {
            return "Tento příkaz nepotřebuje další parametry. Smažte je prosím.";
        }

    }

    /**
     * Metoda vrací název příkazu.
     *
     * @return vrátí název příkazu.
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
