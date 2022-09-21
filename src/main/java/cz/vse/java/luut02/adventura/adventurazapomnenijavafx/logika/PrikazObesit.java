package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Class PrikazObesit implementuje příkaz 'obesit'.
 * Pomocí tohoto příkazu se postava může v prostoru 'domov' oběsit a zabít.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class PrikazObesit implements IPrikaz {
    private static final String NAZEV = "obesit";
    private HerniPlan herniPlan;
    private Hra hra;

    /**
     * Konstruktor pro příkaz 'obesit'.
     *
     * @param herniPlan herní plán kde se postava nachází a může se v ní pohybovat
     * @param hra       instance aktuální hry
     */
    public PrikazObesit(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }

    /**
     * Metoda provádí příkaz 'obesit'.
     * Ujistí se, pokud se postava nachází v prostoru 'domov' a že nebyl zadán žádný extra parametr.
     * Pokud ano, postavu zabije a ukončí hru.
     * Pokud ne, napíše zprávu, že se nemá postava jak oběsit.
     *
     * @param parametry kontroluje zda nebyl zadán extra parametr
     * @return vrací výsledek příkazu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 && (herniPlan.getAktualniProstor().getNazev().equals("domov"))) {
            hra.setKonecHry(true);
            return "S pocitem bezcennosti si vzala postava lano ze skříně a hodila si jej přes lustr s pomocí židle.\nSebevražedná smyčka na krku a židle pod nohama bylo všechno co postava potřebovala.\nNohou odkopla postava židli a krk už visel pouze na laně - doufala, že proces bude rychlý, ale bezmocně jenom lapala po dechu zhruba 4 minuty. Nedostatek kyslíku dokáže změnit názor na sebevraždu každému, ale už bylo pozdě.\nPostavu po pár dnech našli kamarádi...";
        } else if (parametry.length >= 1 && (herniPlan.getAktualniProstor().getNazev().equals("domov"))) {
            return "Oběsit se dá mnoha způsoby, ale necháme to na postavě jak to udělá. Použijte příkaz bez parametrů.";
        } else {
            return "Nemám se tu jak oběsit.";
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
