package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

/**
 * Třída Hra - třída představující logiku adventury.
 * <p>
 * Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 * a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 * Vypisuje uvítací a ukončovací text hry.
 * Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Trong Dat Luu
 * @version LS 2021/22
 */

public class Hra implements IHra {

    private Inventar inventar;
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        inventar = new Inventar();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZavolat(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazSkocit(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazObesit(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazZazvonit(herniPlan, this));
        platnePrikazy.vlozPrikaz(new PrikazBodnout(inventar, this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan, inventar));
        platnePrikazy.vlozPrikaz(new PrikazKoupit(herniPlan, inventar, this));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte ve hře Zapomnění!\n" +
                "\n" +
                "Je zima a píše se rok 2006. Jste student vysoké školy a žijete sami na bytě v Praze. Praha není vaším rodným městem a s rodiči nejste v kontaktu již dlouho kvůli rodinnému konfliktu. \n" +
                "V Praze nikoho moc neznáte a žijete samotářský život. Nedávno Vás vyhodili i z práce a ocitáte se v situaci, kdy jste bez příjmů a snažíte se ušetřit na každé koruně.  \n" +
                "Ve škole se Vám hromadí resty a nedokážete kvůli okolní situaci se soustředit na studium.  \n" +
                "Situaci přestáváte zvládat a nevíte co dál.  \n" +
                "V hlavě máte zafixováno, že si stejně ani nikdo nevšimne, pokud byste „zmizeli“...  \n" +
                "\n" +
                "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n\n--------------------------------------------\nTato hra obsahuje sebevražedné motivy. Pokud máte psychické problémy, nebo znáte někoho, kdo se potýká s obtížemi, existuje pomoc.\nLinka bezpečí: 116 111\nPražská linka důvěry: 222 580 697\n--------------------------------------------" +
                "\n" +
                herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Možná mohlo skončit všechno jinak...\nDíky, že jste si zahráli. Mějte se pěkně a někdy zase v budoucnu.\n";
    }

    /**
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     * <p>
     * Dále pokud je hodnota 'jeUPsychologa' true z HerniPlan, vypíše se zpráva a ukončí se hra.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani = " .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
            if (herniPlan.jeUPsychologa()) {
                textKVypsani = "Po nějaké chvilce si postavu vyzvedne a vyslechne psycholog, kde postava mu říká všechny svoje problémy.\nPsycholog postavu uklidní a navede ji aspoň částečně na jiné myšlenky jak chuť spáchat sebevraždu a pocit bezcennosti.\nDomluví se na pravidelných schůzkách, a že postava není jediná, kdo se takhle pociťuje a není to nic špatného, se občas takhle cítit a mít potřebu si o tom promluvit s někým.\nPomůže postavě dále si napsat seznam věcí, které musí udělat, kde se ukázalo, že se to dá všechno zvládnout.\nTouto cestou se postavě nic nestane a vrátí se domů s klidnější hlavou.";
                konecHry = true;
            }
        } else {
            textKVypsani = "Tento příkaz neznám. Zkontrolujte nápovědu, pokud si nevíte rady.";
        }
        return textKVypsani;
    }


    /**
     * Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     * mohou ji použít i další implementace rozhraní Prikaz.
     *
     * @param konecHry hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return odkaz na herní plán
     */
    public HerniPlan getHerniPlan() {
        return herniPlan;
    }

}

