package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

import java.util.Objects;

/**
 * Class Vec je na věci ve hře.
 * Obsahuje jméno věci a booleanovou hodnotu, jestli je věc sebratelná.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class Vec {
    private final String nazev;
    private final boolean sebratelna;

    /**
     * Konstruktor pro věci - vytváří věci.
     *
     * @param nazev      název věci
     * @param sebratelna jestli se dá věc dát do inventáře či ne
     */
    public Vec(String nazev, boolean sebratelna) {
        this.nazev = nazev;
        this.sebratelna = sebratelna;
    }

    /**
     * Metoda, která vrací název věci.
     *
     * @return název věci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Metoda, která vrací zda je věc sebratelná (zda se dá věc dát do inventáře).
     *
     * @return boolean hodnota sebratelnosti - true pokud je sebratelná
     */
    public boolean isSebratelna() {
        return sebratelna;
    }

    /**
     * Metoda equals, která porovnává 2 věci, zda nemají stejný název.
     * Je překrytá pomocí @Override.
     *
     * @param o porovnávaný objekt
     * @return pokud jsou věci stejné, hodnota je true
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec vec = (Vec) o;
        return Objects.equals(nazev, vec.nazev);
    }

    /**
     * Metoda hashCode, pokud mají 2 věci stejný název tak mají stejný hashCode.
     *
     * @return vrací hashCode dané instance věci
     */
    @Override
    public int hashCode() {
        return Objects.hash(nazev);
    }
}
