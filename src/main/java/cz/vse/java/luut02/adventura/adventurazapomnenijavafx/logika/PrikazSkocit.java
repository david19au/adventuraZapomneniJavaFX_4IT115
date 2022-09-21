package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Class PrikazSkocit implementuje do hry příkaz 'skocit'.
 * Postava se pomocí tohoto příkazu může zabít skokem z Karlova mostu.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class PrikazSkocit implements IPrikaz {

    private static final String NAZEV = "skocit";

    private HerniPlan herniPlan;
    private Hra hra;

    /**
     * Konstruktor pro příkaz 'skocit'.
     *
     * @param herniPlan herní plán kde se postava nachází a může se v ní pohybovat
     * @param hra       instance aktuální hry
     */
    public PrikazSkocit(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }

    /**
     * Metoda provádí příkaz 'skocit'.
     * Ujistí se, zda se postava nachází v prostech kde se dá skočit a zároveň nebyl zadán žádný extra parametr.
     * Pokud ano, postava skočí z daného místa, zabije se a ukončí hru.
     * Pokud ne, napíše zprávu, že postava nemá odkud skočit.
     *
     * @param parametry kontroluje zda nebyl zadán extra parametr
     * @return vrací výsledek příkazu
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 && (herniPlan.getAktualniProstor().getNazev().equals("karluv_most"))) {
            hra.setKonecHry(true);
            return "S hlubokým nádechem se postava zvedla na okraj Karlova mostu.\nNejdříve si jenom sedla a koukala na velkolepou Vltavu, poté ale zavřela oči a volným pádem skočila ze zábradlí mostu do Vltavy.\nKolemjdoucí šokovaní z toho co se stalo se přihrnuli k místě skoku a marně hledají kam postava skočila.\nZavolali pomoc a v mžiku se hledalo tělo, ale bylo už moc pozdě - ani po nalezení záchranáři nedokázali oživit postavu.";
        } else if (parametry.length >= 1) {
            return "Není třeba specifikovat jak skočit, skok je skok. Použijte příkaz bez parametrů, děkuji.";
        } else if (herniPlan.getAktualniProstor().getNazev().equals("strecha_skoly")) {
            hra.setKonecHry(true);
            return "Pomalu se postava připlíží ke hraně střechy a kouká směrem dolů na ulici. Postavě se motá hlava, nedokáže udržet rovnováhu a padá střemhlav na zem.\nBezvládně dopadne na chodník a krvácí, kolemjdoucí se postavě snaží pomoct ale ani po příjezdu sanitky už nebylo jak pomoci...\nVšude po zprávách se šíří zpráva o sebevraždě vysokoškolského studenta a celá událost rozkmitá řadu smutečných reakcí od rodiny i od přátel, kterých měla postava více, než si myslela.";
        } else if (herniPlan.getAktualniProstor().getNazev().equals("koleje")) {
            hra.setKonecHry(true);
            return "Postava přichází na koleje a těsně před příjezdem vlaku skáče pod koleje, kde už není kudy úniku a ani záchrany - vlak totiž nedokáže tak rychle zabrzdit.\nKrev je všude po čelním skle vlaku a většina nádraží je paralyzována.\nVšude po zprávách se šíří zpráva o sebevraždě na hlavním nádraží a celá událost rozkmitá řadu smutečných reakcí od rodiny i od přátel, kterých měla postava více, než si myslela.";
        } else {
            return "Nemám kde skočit odsud.";
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
