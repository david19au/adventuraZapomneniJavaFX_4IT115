package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main.Pozorovatel;
import cz.vse.java.luut02.adventura.adventurazapomnenijavafx.main.PredmetPozorovani;

import java.util.*;

/**
 * Pomocí třídy Inventar realizuje hra inventář postavy/hráče. V inventáři může postava nosit určité předměty.
 * Funkcionalita je zajištěna pomocí mapy s věcmi, která obsahuje to co má u sebe hráč. Postava/hráč může u sebe mít jenom
 * limitovaný počet věcí, nastavený v atributu "MAXIMALNI_KAPACITA" - v našem případě může nosit maximálně 2 věci u sebe.
 * Pokus o přidání 3. věci se nepodaří.
 *
 * @author Trong Dat Luu
 * @version ZS 2022/23
 */

public class Inventar implements PredmetPozorovani {

    private Map<String, Vec> mapaSVecmi;
    private static final int MAXIMALNI_KAPACITA = 2;
    private static int pocetVeci;
    private static Set<Pozorovatel> listOfPozorovateluInventar = new HashSet<>();

    /**
     * Konstruktor, který vytvoří nový inventář s hashMapou pro věci.
     */
    public Inventar() {
        mapaSVecmi = new HashMap<>();
    }

    /**
     * Metoda co nám napíše kolik věcí je v inventáři.
     *
     * @return napíše kolik věcí je v inventáři.
     */
    public int getPocetVeci() {
        return pocetVeci;
    }

    /**
     * Metoda co kontroluje zda má hráč/postava dost místa pro přidání další věci.
     *
     * @return true pokud je číslo s maximální kapacitou větší než počet věcí co má postava/hráč u sebe
     */
    public boolean dostMista() {
        return MAXIMALNI_KAPACITA > pocetVeci;
    }

    /**
     * Metoda vlozeniVeciDoInv dokáže vložit věc do inventáře, pokud má postava/hráč dost místa a pokud vložená věc není
     * null.
     *
     * @param vec věc co se má vložit do inventaře
     */
    public void vlozeniVeciDoInv(Vec vec) {
        if ((vec != null) && dostMista()) {
            pocetVeci++;
            mapaSVecmi.put(vec.getNazev(), vec);
            upozorniPozorovatele();
        }
    }

    /**
     * Metoda odebere věc z inventáře.
     *
     * @param jmenoVeci jméno věci co se má odebrat
     * @return vrací odebranou věc
     */

    public Vec odebraniVeciZInv(String jmenoVeci) {
        pocetVeci--;
        return mapaSVecmi.remove(jmenoVeci);
    }

    /**
     * Metoda vyprazdňuje inventář, aby v něm nic nebylo.
     * Nastavuje počet věcí na 0 a vyčistí mapu s věcmi.
     */
    public void vyprazdnitInventar(){
        pocetVeci=0;
        mapaSVecmi.clear();
    }

    /**
     * Metoda kontroluje, zda obsahuje hráčův inventář požadovanou věc či ne.
     *
     * @param jmenoVeci hledání věci podle jména
     * @return true pokud je inventáři nalezena daná věc
     */
    public boolean obsahujeVInventari(String jmenoVeci) {
        for (Vec vec : mapaSVecmi.values()) {
            if (vec.getNazev().equals(jmenoVeci)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metoda vytvoří novou kolekci z HashMapy, která si jsou vždycky rovny - mají stejný obsah.
     *
     * @return vrací kolekci s věcmi
     */
    public Collection<Vec> returnVeci() {
        Collection<Vec> veci = new HashSet<>(mapaSVecmi.values());
        return veci;
    }

    /**
     * Metoda vypíše, co má hráč v inventáři.
     * Pokud je počet věcí 0, napíše hráči, že v inventáři nic není.
     * Pokud je, použije se string builder, který pomocí for-loopu projde všechny věci v hashMapě, vezme si jejich K a
     * a oddělí je.
     * @return vrací co má hráč v inventáři
     */
    public String nazvyVeci() {

        if (getPocetVeci() == 0) {
            return "V inventáři nic není.";
        }
        StringBuilder nazvy = new StringBuilder("Věci v inventaři: ");
        for (String jmenoVeci : mapaSVecmi.keySet()) {
            nazvy.append(jmenoVeci).append(" || ");
        }
        return nazvy.toString();
    }

    /**
     * Metoda tzv. registruje pozorovatele, aby mohl vědět o změnách.
     * @param pozorovatel přidá pozorovatele do seznamu.
     */
    @Override
    public void registruj(Pozorovatel pozorovatel) {
        listOfPozorovateluInventar.add(pozorovatel);

    }

    /**
     * Metoda upozorňuje pozorovatele, že nastala nějaká změna a má se na ní podívat.
     */
    public void upozorniPozorovatele() {
        for (Pozorovatel pozorovatel : listOfPozorovateluInventar) {
            pozorovatel.update();
        }
    }
}
