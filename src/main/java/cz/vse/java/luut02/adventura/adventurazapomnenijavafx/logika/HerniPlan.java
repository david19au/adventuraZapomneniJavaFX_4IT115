package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * <p>
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Trong Dat Luu
 * @version LS 2021/22
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Prostor psychologProstor;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví domov.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví domov.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor domov = new Prostor("domov", "domov, ve kterém bydlí postava.\nV obývacím pokoji se nachází funkční pevná linka, přes kterou můžete zavolat svým rodičům a ve skříni máte lano.\nS lanem se můžete oběsit pomocí příkazu 'obesit' a rodičům můžete zavolat příkazem 'zavolat'.\nMůžete také jít ven na ulici se projít anebo se kouknout do kuchyně.\nDo místností se můžete pohybovat pomocí příkazu 'jdi <místnost>'");
        Prostor kuchyn = new Prostor("kuchyn", "kuchyň, kde má postava prázdnou lednici a na kuchyňské lince nůž.\nTaké zde nacházíte poslední úspory. Kdoví, možná se budou později hodit.\nVěci seberete příkazem 'seber <věc co chcete sebrat>'");

        Prostor ulice = new Prostor("ulice", "ulice bytu kde postava bydlí.\nMáte možnost jít zpět domů, ke kamarádovi domů, na hlavní nádraží, do školy anebo na Václavské náměstí..");

        Prostor kamaraduvByt = new Prostor("kamaraduv_byt", "vchod do kamarádova bytu.\nNejste si jistí jestli Vás vůbec má kamarád rád a jestli Vás bere jako kamaráda. Můžete se ale pokusit si promluvit s kamarádem anebo jít zpět na ulici.\nNa jeho byt zazvoníte příkazem 'zazvonit'");

        Prostor hlavniNadrazi = new Prostor("hlavni_nadrazi", "Hlavní nádraží s vlaky a turisty.\nNemáte dostatek peněz a ani náladu někam odcestovat, avšak stále Vás mrzí, že ostatní si to mohou dovolit.\nMůžete jim ukázat, jaké to je, nemoct odcestovat ale..");
        Prostor koleje = new Prostor("koleje", "koleje na hlavním nádraží.\nPodle tabulky příjezdů jste si našli, že nejbližší příjezd pokračujícího vlaku je na nástupišti 2.\nJe to vlak mířící do Drážďan na Vánoční trhy.\nPostava se rozhoduje, zda neskončit své trápení rovnou tu a teď, zároveň také ukázat i cestujícím, jaké to je být na dně...\nSkočit můžete příkazem 'skocit'");

        Prostor skola = new Prostor("skola", "vysoká škola kam chodí postava.\nPostava ví, že škola nabízí zdarma psychologickou pomoc, která ji může pomoct s momentální situací.\nNa druhou stranu ale nikdy u takové pomoci nebyla a bojí se, že na tom bude ještě hůř jak dříve a lidi kolem se ji budou navíc ještě vysmívat, že se s tím nedokáže vypořádat sama.\nVí také ale, že vstup na střechu není monitorovaný a může z ní skočit..");
        Prostor strechaSkoly = new Prostor("strecha_skoly", "střechy vysoké školy.\nPostava pomalu ale jistě vyšlapala schody a přes střešní okno se dostala na střechu školy.\nPorozhlédne se kolem sebe a popřemýšlí, jestli by nakonec nechtěla radši slézt dolů, že by existovalo lepší řešení...\nMůžete skočit příkazem 'skocit'");
        Prostor psycholog = new Prostor("psycholog", "čekárny u školního psychologa. \n");

        Prostor vaclavskeNamesti = new Prostor("vaclavske_namesti", "Václavské náměstí, kde se pohybuje tuna turistů.\nPostava se zde rozhoduje, zda by procházka dále mohla pomoct se uklidnit, či jestli by nebylo rychlejší navštívit drogového dealera, který postavě umožní se na chvíli vypnout");
        Prostor drogovyDealer = new Prostor("drogovy_dealer", "u drogového dealera, kde si postava může koupit dočasný ráj pro sebe.\nMůže si zde promluvit s dealerem, aby ji dal drogy.\nDrogy koupíte s příkazem 'koupit'");
        Prostor karluvMost = new Prostor("karluv_most", "Karlova mostu s lidmi kolem.\nPostava si po procházce uvědomí, že je už relativně pozdě a neměla by proto už chodit někam dále.\nPostava si ale zároveň uvědomí, že na ni čeká hrozně moc věcí, které prostě nezvládne a v hlavě má jen dvě možnosti – všechno ukončit hned teď anebo se vrátit a doufat, že se naskytne jiná možnost, která bude „lepší“.\nSkočit můžete pomocí příkazu 'skocit'");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        domov.setVychod(kuchyn);
        domov.setVychod(ulice);
        kuchyn.setVychod(domov);
        ulice.setVychod(domov);
        ulice.setVychod(kamaraduvByt);
        ulice.setVychod(vaclavskeNamesti);
        ulice.setVychod(hlavniNadrazi);
        ulice.setVychod(skola);
        hlavniNadrazi.setVychod(koleje);
        hlavniNadrazi.setVychod(ulice);
        koleje.setVychod(hlavniNadrazi);
        kamaraduvByt.setVychod(ulice);
        vaclavskeNamesti.setVychod(ulice);
        vaclavskeNamesti.setVychod(drogovyDealer);
        vaclavskeNamesti.setVychod(karluvMost);
        skola.setVychod(psycholog);
        skola.setVychod(strechaSkoly);
        skola.setVychod(ulice);
        strechaSkoly.setVychod(skola);
        karluvMost.setVychod(vaclavskeNamesti);
        drogovyDealer.setVychod(vaclavskeNamesti);


        aktualniProstor = domov; // nastavuje se počáteční prostor
        psychologProstor = psycholog; //u psychologa se může hra ukončit

        // vytvoření věcí a deklarování jména a zda jdou sebrat
        Vec nuz = new Vec("nuz", true);
        Vec penize = new Vec("penize", true);
        Vec pevnaLinka = new Vec("pevna_linka", false);
        Vec lano = new Vec("lano", false);
        Vec zvonek = new Vec("zvonek", false);

        //přiřazení věcí do prostorů
        kuchyn.pridejVec(nuz);
        kuchyn.pridejVec(penize);
        domov.pridejVec(pevnaLinka);
        domov.pridejVec(lano);
        kamaraduvByt.pridejVec(zvonek);
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     * Metoda kontroluje jestli se postava dostala do prostoru 'psycholog'.
     * V tomto případě se v class Hra v metodě zpracujPrikaz (input z terminálu) vypíše příslušný text a ukončí hra.
     *
     * @return true pokud je postava u psychologa
     */
    public boolean jeUPsychologa() {
        return aktualniProstor.equals(psychologProstor);
    }
}
