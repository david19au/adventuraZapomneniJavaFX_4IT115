package cz.vse.java.luut02.adventura.adventurazapomnenijavafx.logika;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída SkocitTest testuje funkčnost příkazu 'skocit'.
 *
 * @author Trong Dat Luu
 * @version LS 2021/22
 */
public class SkocitTest {

    private Hra hraTest;

    @BeforeEach
    public void setUp() {
        hraTest = new Hra();
    }

    /**
     * Metoda 'testSkocitKarluvMost' kontroluje, že příkaz funguje pokud chce postava skočit z Karlova mostu.
     * Skok z míst, kde to není dovolené se nepodaří (zde prostor 'domov' nebo 'vaclavske_namesti').
     * Dále kontroluje, že hra se správně ukončí při povedeném skoku.
     */
    @Test
    public void testSkocitKarluvMost() {
        assertEquals("domov", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Nemám kde skočit odsud.", hraTest.zpracujPrikaz("skocit"));
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi ulice");
        hraTest.zpracujPrikaz("jdi vaclavske_namesti");
        hraTest.zpracujPrikaz("skocit");
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi karluv_most");
        hraTest.zpracujPrikaz("skocit");
        assertTrue(hraTest.konecHry());
    }

    /**
     * Metoda 'testSkocitStrecha' kontroluje, že příkaz funguje pokud chce postava skočit ze střechy ve škole.
     * Skok z míst, kde to není dovolené se nepodaří (zde prostor 'domov' anebo 'skola').
     * Dále kontroluje, že hra se správně ukončí při povedeném skoku.
     */
    @Test
    public void testSkocitStrecha() {
        assertEquals("domov", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Nemám kde skočit odsud.", hraTest.zpracujPrikaz("skocit"));
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi ulice");
        hraTest.zpracujPrikaz("jdi skola");
        hraTest.zpracujPrikaz("skocit");
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi strecha_skoly");
        hraTest.zpracujPrikaz("skocit");
        assertTrue(hraTest.konecHry());
    }

    /**
     * Metoda 'testSkocitKoleje' kontroluje, že příkaz funguje pokud chce postava skočit do kolejí na Hlavním nádraží.
     * Skok z míst, kde to není dovolené se nepodaří (zde prostor 'domov' anebo 'hlavni_nadrazi').
     * Dále kontroluje, že hra se správně ukončí při povedeném skoku.
     */
    @Test
    public void testSkocitKoleje() {
        assertEquals("domov", hraTest.getHerniPlan().getAktualniProstor().getNazev());
        assertEquals("Nemám kde skočit odsud.", hraTest.zpracujPrikaz("skocit"));
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi ulice");
        hraTest.zpracujPrikaz("jdi hlavni_nadrazi");
        hraTest.zpracujPrikaz("skocit");
        assertFalse(hraTest.konecHry());
        hraTest.zpracujPrikaz("jdi koleje");
        hraTest.zpracujPrikaz("skocit");
        assertTrue(hraTest.konecHry());
    }
}
