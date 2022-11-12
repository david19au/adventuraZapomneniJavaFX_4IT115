package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Class PrikazKoupit implementuje příkaz 'koupit'.
 * Pomocí tohoto příkazu si postava může koupit a zároveň použít drogy. Koupenýma drogama se ale předávkuje a umírá.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class PrikazKoupit implements IPrikaz {

    private static final String NAZEV = "koupit";
    private HerniPlan herniPlan;
    private Inventar inventar;
    private Hra hra;

    /**
     * Konstruktor pro příkaz 'koupit'.
     *
     * @param herniPlan herní plán kde se postava nachází a může se v ní pohybovat
     * @param inventar  inventář postavy/hráče
     * @param hra       instance aktuální hry
     */
    public PrikazKoupit(HerniPlan herniPlan, Inventar inventar, Hra hra) {
        this.herniPlan = herniPlan;
        this.inventar = inventar;
        this.hra = hra;
    }

    /**
     * Tato metoda provádí příkaz 'koupit'.
     * Kontroluje, zda není napsaný žádný další argument a zda je postava u drogového dealera. Zároveň kontroluje inventář,
     * zda postava má peníze u sebe.
     * Dále upozorňuje pozorovatele, že nastala změna v inventáři.
     *
     * @param parametry kontroluje zda hráč nenapsal žádný extra parametr.
     * @return vrací text při dané situaci.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 && (herniPlan.getAktualniProstor().getNazev().equals("drogovy_dealer")) && (inventar.obsahujeVInventari("penize"))) {
            inventar.odebraniVeciZInv("penize");
            inventar.upozorniPozorovatele();
            hra.setKonecHry(true);
            return "Postava si koupila drogy ze svých posledních úspor.\nDealer dal postavě opiody, které byly přimíchané fentanylem, o kterých ale nikdo nevěděl.\nPostava si vzala drogy a zanedlouho se jí udělalo špatně a chtělo se jí spát.\nNečekala ale, že tohle bude již věčný spánek, protože se jí zpomaloval tep a i dýchání.\nEfekty předávkování fentanylem jsou velmi rychlé a nikdo nestihl postavě pomoct.\nBrzy leží postava bezvládně na zemi, mrtvá, předávkovaná fentanylem.";
        } else if (parametry.length >= 1 && (herniPlan.getAktualniProstor().getNazev().equals("drogovy_dealer"))) {
            return "Prosím použijte příkaz bez parametrů, děkuji.";
        }
        if (parametry.length == 0 && (herniPlan.getAktualniProstor().getNazev().equals("drogovy_dealer")) && !(inventar.obsahujeVInventari("penize"))) {
            return "Postava nemá peníze, aby si mohla koupit drogy.";
        } else {
            return "Postava není u drogového dealera.";
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
