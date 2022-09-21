package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Class PrikazSeber implementuje do hry příkaz 'seber'.
 * Pomocí tohoto příkazu sbírá postava/hráč věci, které jsou v některých prostorech.
 * Při procesu přidání probíhá kontrola, zda je věc sebratelná, poté se kontroluje zda má hráč dost místa a až poté
 * se věc přidá do inventáře.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";
    private HerniPlan herniPlan;
    private Inventar inventar;

    /**
     * Konstruktor pro příkaz 'seber'.
     *
     * @param herniPlan herní plán kde se postava nachází a může se v ní pohybovat
     * @param inventar  inventář postavy/hráče
     */
    public PrikazSeber(HerniPlan herniPlan, Inventar inventar) {
        this.herniPlan = herniPlan;
        this.inventar = inventar;
    }

    /**
     * Metoda provede příkaz seber.
     * Zkontroluje se nejdřív kolik paramentrů je napsaných, jelikož postava může vzít pouze jednu věc najednou.
     * Poté proběhne kontrola jestli je daná věc v daném prostoru, pokud je věc sebratelná, jestli má postava místo v
     * inventáři.
     * Pokud má postava dost místa, věc je sebratelná a je v daném prostoru, postava věc sebere a přidá se do inventáře.
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return vrací text se zprávou pokud se věc sebrala, nebo ne.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím co mám sebrat. Specifikujte věc k sebrání.";
        } else if (parametry.length > 1) {
            return "Dokážu sebrat pouze jednu věc najednou.";
        }
        String jmenoVeciCoSeMaSebrat = parametry[0];
        Vec vecCoSeMaSebrat = herniPlan.getAktualniProstor().odeberVec(jmenoVeciCoSeMaSebrat);
        if (vecCoSeMaSebrat == null) {
            return "Tohle tu není.";
        }
        if (!(vecCoSeMaSebrat.isSebratelna())) {
            herniPlan.getAktualniProstor().pridejVec(vecCoSeMaSebrat);
            return "Tuhle věc nemůžu sebrat.";
        } else if (vecCoSeMaSebrat.isSebratelna() && !(inventar.dostMista())) {
            herniPlan.getAktualniProstor().pridejVec(vecCoSeMaSebrat);
            return "Tohle už neponesu, nemám místo kam věc dát.";
        } else {
            inventar.vlozeniVeciDoInv(vecCoSeMaSebrat);
            return "Sebral jsem " + jmenoVeciCoSeMaSebrat + ".";
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
