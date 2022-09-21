package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Class PrikazZazvonit implementuje do hry příkaz 'zazvonit'.
 * Postava pomocí tohoto příkazu zazvoní postava u kamaráda, který mu pomůže se dostat přes sebevražedné pocity.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class PrikazZazvonit implements IPrikaz {

    private static final String NAZEV = "zazvonit";
    private HerniPlan herniPlan;
    private Hra hra;

    /**
     * Konstruktor pro příkaz 'zazvonit'.
     *
     * @param herniPlan herní plán kde se postava nachází a může se v ní pohybovat
     * @param hra       instance aktuální hry
     */
    public PrikazZazvonit(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }

    /**
     * Při provedení příkazu se kontroluje, pokud je postava v prostoru 'kamaraduv_byt'.
     * Pokud postava není u kamarádova bytu, vypíše se, že postava nemá kde zazvonit.
     * Další kontrola je, zda nebyly napsané žádné parametry.
     *
     * @param parametry kontroluje zda nebyl zadán extra parametr
     * @return vrací výsledek příkazu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 && (herniPlan.getAktualniProstor().getNazev().equals("kamaraduv_byt"))) {
            hra.setKonecHry(true);
            return "Po zazvonění na byt kamaráda se zeptá postava jestli může za ním do bytu, jestli by si nemohli promluvit.\nKamarád řekne postavě, že určitě a popovídají si.\nPostava říká všechny její problémy a její kamarád se ji snaží vyslechnout a pomoct.\nKamarád pevně sevře postavu do náručí, s tím, že jí má rádo a zároveň také zmiňuje, že je spousta dalších lidí co má postavu také rádo.\nSpolečně kouknou na resty co má postava na svých ramenou a pomůže ji.\nPostava je v mírném šoku, ale získala zpět trošku sebevědomí a vrací se domů s tím, že má lidi kolem sebe na které se může spolehnout a věří, že budou lepší dny.";
        } else if (parametry.length >= 1 && (herniPlan.getAktualniProstor().getNazev().equals("kamaraduv_byt"))) {
            return "Prosím použijte příkaz bez parametrů, děkuji.";
        } else {
            return "Postava nemá kde zazvonit.";
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
