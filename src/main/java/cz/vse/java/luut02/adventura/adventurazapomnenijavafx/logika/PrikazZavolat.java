package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Class PrikazZavolat implementuje do hry příkaz 'zavolat'.
 * Postava pomocí tohoto příkazu může zavolat svým rodičům, pokud je v prostoru 'domov'.
 * Příkaz je čistě příběhový, vůbec nemění výsledek hry.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class PrikazZavolat implements IPrikaz {

    private static final String NAZEV = "zavolat";
    private HerniPlan herniPlan;

    /**
     * Konstruktor pro příkaz 'zavolat'.
     *
     * @param herniPlan herní plán kde se postava nachází a může se v ní pohybovat
     */
    public PrikazZavolat(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * Metoda zkontroluje pokud se postava nachází v prostoru 'domov' a jestli nebyl připsán žádný extra parametr.
     * Pokud žádný extra parametr napsán nebyl, vrátí příkaz text, který má být intepretace rozhovoru s rodiči postavy.
     * Tento příkaz nemá žádný vliv na konce hry.
     * Pokud se postava nenachází v daném prostoru, příkaz napíše, že postava není schopná zavolat.
     *
     * @param parametry kontroluje zda nebyl zadán extra parametr
     * @return vrací výsledek příkazu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 && (herniPlan.getAktualniProstor().getNazev().equals("domov"))) {
            return "Postava zavolala svým rodičům a nebyla to příjemná povídaná.\nRodiče nezajímaly problémy postavy a vinu shazovaly na postavu, že si neumí správně zařídit čas.\nPostava se nyní cítí ještě více bezcenná, než předtím.";
        } else if (parametry.length >= 1 && (herniPlan.getAktualniProstor().getNazev().equals("domov"))) {
            return "Prosím použijte příkaz bez parametrů, děkuji.";
        } else {
            return "Nemohu zde volat.";
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
